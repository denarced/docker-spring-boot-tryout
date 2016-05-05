#!/bin/bash

for each in webapp{1..2}
do
    ./start_webapp_container.bash $each
done
./start_haproxy_container.bash
