# docker-spring-boot-tryout
Just trying out Docker, Spring Boot, and haproxy in a very simple setup. Something to get started with. The goal is to have

- a single new Docker network to be used by the launched containers with only
  one container exposed to the host
- one container running haproxy and exposed to the host on port 80. It'll pass
  all traffic to the two webapps that are not directly exposed to the host at
  all.
- two containers running two webapps
- one container running PostgreSQL database
- three images: one for haproxy, one for webapp, and one for PostgreSQL

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
2. build the images. One for haproxy, one for PostgreSQL, and one for the
   webapp built in previous step.

After setting things up, `start_containers.bash` can be used to start all of the
containers. Port 80 has to be available on host for this to work. After the
containers have been launched they can be seen by running `docker ps`. And the
built Docker images can be seen by running `docker images`. Finally the webapps
can be accessed through haproxy on http://localhost.

# Webapp HTTP Interfaces

The created PostgreSQL database contains one table that only contains
"messages": simple strings for which the webapps provide RESTful CRUD interfaces:

- POST   /message creates a new message
- GET    /message/{id} reads a specific message. The one with {id} in URL.
- GET    /messages reads all messages
- POST   /message/{id} edits an existing message
- DELETE /message/{id} deletes an existing message

JSON is used throughout. For creating, reading, and updating the messages. Upon creating and editing, the message in question is returned.

# Webapp Examples

Calling GET /messages results in the following, pretty printed here:
```
[
    {
        "id": 1,
        "message": "henna"
    },
    {
        "id": 2,
        "message": "henna"
    },
    {
        "id": 3,
        "message": "kaija"
    }
]
```

To create a message with curl, the id attribute can be left out of the JSON:
```
curl -X POST -d '{"message": "a value"}' -H 'Content-Type: application/json' http://localhost/message
```
If this is the first message in the database, the returned JSON would be:
```
{
  "id": 1,
  "message": "a value"
}
```
The same holds true for editing a message because the ID of the message is in the URL:
```
curl -X POST -d '{"message": "other value"}' -H 'Content-Type: application/json' http://localhost/message/1
```
And the returned JSON would be:
```
{
  "id": 1,
  "message": "other value"
}
```
Deleting a message with it's ID:
```
curl -X DELETE http://localhost/message/1
```
