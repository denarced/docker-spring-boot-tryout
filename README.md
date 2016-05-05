# docker-spring-boot-tryout
Just trying out Docker, Spring Boot, and haproxy in a very simple setup. Something to get started with. The goal is to have

- a single new Docker network to be used by the launched containers with only
  one container exposed to the host
- one container running haproxy and exposed to the host on port 80. It'll pass
  all traffic to the two webapps that are not directly exposed to the host at
  all.
- two containers running two webapps
- two images: one for haproxy and another for webapp

# Dependencies

This little example has been developed using:

- Apache Maven 3.3.3
- Java version: 1.8.0_66, vendor: Oracle Corporation
- Docker version 1.11.1, build 5604cbe
- Ubuntu 15.10

It'll probably work with any Maven 3.x version, any Java 8 version, and on various 64 bit Linux distros. I'm not sure about the Docker versions as I'm just getting started with it.

# Quick Start

Execute `setup.bash`. It'll

1. build the webapp
2. built the images. One for haproxy and another for the webapp built in
   previous step.

After setting things up, `start_containers.bash` can be used to start the
containers. One for haproxy and two for webapps. Port 80 has to be available on
host for this to work. After the containers have been launched they can be seen
by running `docker ps`. And the built Docker images can be seen by running
`docker images`. Finally the webapps can be accessed through haproxy on
http://localhost.
