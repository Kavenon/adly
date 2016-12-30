#!/usr/bin/env bash

#VBoxManage controlvm default natpf1 delete "adlyedge"
#VBoxManage controlvm $(docker-machine active) natpf1 "adlyedge,tcp,,9999,,9999"

export DOCKER_IP=$(docker-machine ip $(docker-machine active))

DOCKER_IP=${DOCKER_IP:-0.0.0.0}

docker-compose stop
docker-compose rm -f

docker-compose up -d adly-config-server

while [ -z ${CONFIG_SERVICE_READY} ]; do
  echo "Waiting for config service..."
  if [ "$(curl --silent $DOCKER_IP:8888/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      CONFIG_SERVICE_READY=true;
  fi
  sleep 2
done

docker-compose up -d adly-eureka-service

while [ -z ${DISCOVERY_SERVICE_READY} ]; do
  echo "Waiting for discovery service..."
  if [ "$(curl --silent $DOCKER_IP:8761/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      DISCOVERY_SERVICE_READY=true;
  fi
  sleep 2
done

docker-compose up -d cassandra

echo "Initializing Cassandra (few stack traces are expected)"
while : ;do

  STATUS=`docker exec $(docker ps -aqf "name=cassandra") nodetool status | grep 'UN' | awk '{print $1}'`

  if [ $STATUS = "UN" ]; then
        docker cp ./merged.cql $(docker ps -aqf "name=cassandra"):/merged.cql
        docker exec $(docker ps -aqf "name=cassandra") cqlsh -f ./merged.cql
        break
  fi

  sleep 1;

done

docker-compose up -d adly-edge-service

echo "Initializing Cassandra - done"

docker-compose up -d

docker-compose logs -f