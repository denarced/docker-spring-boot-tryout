#!/bin/bash

# Build the docker-tryout maven project and copy the Spring Boot jar back into
# the project root directory, ready to be included into the docker image once it
# is built with build_docker_image.bash.

cd docker-tryout/
mvn clean install
cd ..
cp docker-tryout/target/docker-tryout-0.0.1-SNAPSHOT.jar .
