#!/bin/bash

# build haproxy image if it doesn't exist already
docker images | egrep -q '^docker-haproxy'
if [ $? -ne 0 ]
then
    ./build_docker_haproxy_image.bash
else
    echo "The image _docker-haproxy_ already exists so skipping build."
fi

# build webapp image if it doesn't exist already
docker images | egrep -q '^docker-ubuntu-java8-test'
if [ $? -ne 0 ]
then
    ./build_docker_webapp_image.bash
else
    echo "The image _docker-ubuntu-java8-test_ already exists so skipping
    build."
fi

# build database image if it doesn't exist already
docker images | egrep -q '^docker-postgres'
if [ $? -ne 0 ]
then
    ./build_docker_postgres_image.bash
else
    echo "The image _docker-postgres_ already exists so skipping build."
fi
