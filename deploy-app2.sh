#!/usr/bin/env bash

#eval "$(docker-machine env default)"

# Export the active docker machine IP
export DOCKER_IP=$(docker-machine ip $(docker-machine active))

# docker-machine doesn't exist in Linux, assign default ip if it's not set
DOCKER_IP=${DOCKER_IP:-0.0.0.0}
echo DOCKER_IP
# Remove existing containers
docker-compose stop
docker-compose rm -f

# Start the config service first and wait for it to become available
docker-compose up -d cassandra

docker exec cassandra cqlsh -f ./merged.cql

# Start the other containers
#docker-compose up -d

# Attach to the log output of the cluster
#docker-compose logs