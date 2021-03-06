#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Usage: $0 {net-alias}"
    echo "Starts one webapp container instance with the given net-alias in the
    used private bridged network."
    exit 1
fi

echo "Starting the container with net-alias $1"
docker run \
    -e "nodeid=$1" \
    -e "spring.datasource.url=jdbc:postgresql://postgredb/dockertestdb" \
    -e "spring.datasource.username=denarced" \
    -e "spring.datasource.password=protekto" \
    -d \
    --net=prinet \
    --net-alias=$1 \
    -t docker-ubuntu-java8-test
