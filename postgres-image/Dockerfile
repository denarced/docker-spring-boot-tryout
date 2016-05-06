FROM postgres:9.5

RUN mkdir -p /docker-entrypoint-initdb.d
COPY init_1_database.sql init_2_schema.sh /docker-entrypoint-initdb.d/

