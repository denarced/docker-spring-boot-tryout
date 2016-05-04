# docker-spring-boot-tryout
Just trying out Docker and Spring Boot in a very simple setup. Something to get started with. The Docker image is based on Ubuntu 16.04.

# Dependencies

This little example has been developed using:

- Apache Maven 3.3.3
- Java version: 1.8.0_66, vendor: Oracle Corporation
- Docker version 1.11.1, build 5604cbe
- Ubuntu 15.10

It'll probably work with any Maven 3.x version, any Java 8 version, and on various 64 bit Linux distros. I'm not sure about the Docker versions as I'm just getting started with it.

# Quick Start

Execute the following:

```
  build_docker-tryout.bash && \
  build_docker_image.bash && \
  start_container.bash 15001
```

This'll:

1. Build the Spring Boot project to be included into the Docker image
2. Build the Docker image
3. Run the Docker in a container and expose the Spring Boot webapp on host port 15001

Naturally port 15001 has to be available for this to work. After the first container has been launched it can be seen by running `docker ps`. And the built Docker image can be seen by running `docker images`.
