#!/bin/bash

# Build the docker image that can then be ran as many times as necessary. This
# script should only be executed once unless Dockerfile or docker-tryout
# project has changed.

docker build -t docker-ubuntu-java8-test webapp-docker-image
