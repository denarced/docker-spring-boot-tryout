#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Usage: $0 {port}"
    echo "       port is for the spring webapp"
    echo "Starts one container instance and exposes the Spring Boot webapp to
         the host with the defined port."
    exit 1
fi

echo "Starting the container with port $1 exposed on host"
echo "URL http://localhost:$1"
# hostPort:containerPort
portdef="$1:8080"
docker run -p $portdef -t docker-ubuntu-java8-test

