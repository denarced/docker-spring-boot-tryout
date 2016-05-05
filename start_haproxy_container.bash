#!/bin/bash

echo "Starting docker-haproxy container."
docker run -d -p 80:80 --net=prinet -t docker-haproxy
