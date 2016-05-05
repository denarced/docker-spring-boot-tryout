#!/bin/bash

# create network if it doesn't already exist
output=`docker network ls -q -f 'name=prinet'`
# -z tests whether the string's length is zero and that would happen if the
# network doesn't exist
if [ -z $output ]
then
    docker network create prinet
else
    echo "Docker network _prinet_ already exists so skipping creation."
fi
