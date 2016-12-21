#!/usr/bin/env bash
#eval "$(docker-machine env default)"

# Export the active docker machine IP
export DOCKER_IP=$(docker-machine ip $(docker-machine active))

# docker-machine doesn't exist in Linux, assign default ip if it's not set
DOCKER_IP=${DOCKER_IP:-0.0.0.0}
echo $DOCKER_IP
# Remove existing containers
docker-compose stop
docker-compose rm -f

# Start the config service first and wait for it to become available
docker-compose up -d adly-config-server



while [ -z ${CONFIG_SERVICE_READY} ]; do
  echo "Waiting for config service..."
  if [ "$(curl --silent $DOCKER_IP:8888/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      CONFIG_SERVICE_READY=true;
  fi
  sleep 2
done

# Start the discovery service next and wait
docker-compose up -d adly-eureka-service

while [ -z ${DISCOVERY_SERVICE_READY} ]; do
  echo "Waiting for discovery service..."
  if [ "$(curl --silent $DOCKER_IP:8761/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      DISCOVERY_SERVICE_READY=true;
  fi
  sleep 2
done

docker-compose up -d cassandra

echo "Initializing Cassandra"
while : ;do

  # Get the status of this machine from the Cassandra nodetool
  STATUS=`docker exec adlyio_cassandra_1 nodetool status | grep 'UN' | awk '{print $1}'`
          echo $STATUS
  # Once the status is Up and Normal, then we are ready
  if [ $STATUS = "UN" ]; then
        docker cp ./merged.cql adlyio_cassandra_1:/merged.cql
        docker exec adlyio_cassandra_1 cqlsh -f ./merged.cql
        break
  fi

  sleep 1;

done


echo "Initializing Cassandra - done"

sleep 2

# Start the other containers
docker-compose up -d

# Attach to the log output of the cluster
docker-compose logs