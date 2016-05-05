#!/bin/bash

./build_webapp.bash \
    && ./build_images.bash \
    && ./create_network.bash
