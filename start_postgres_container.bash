#!/bin/bash

echo "Start docker-postgres container."
docker run \
    -d \
    -e POSTGRES_PASSWORD=protekto \
    -e POSTGRES_USER=denarced \
    --net=prinet \
    --net-alias=postgredb \
    docker-postgres
