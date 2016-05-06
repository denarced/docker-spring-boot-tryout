#!/bin/bash

set -e

psql -v ON_ERROR_STOP=1 -d dockertestdb --username "$POSTGRES_USER" <<-EOSQL
    -- *********************************************************************
    -- Update Database Script
    -- *********************************************************************
    -- Change Log: changelog.xml
    -- Ran at: 5/6/16 1:38 PM
    -- Against: denarced@jdbc:postgresql://localhost/testdockertempdb
    -- Liquibase version: 3.5.0
    -- *********************************************************************

    -- Create Database Lock Table
    CREATE TABLE public.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

    -- Initialize Database Lock Table
    DELETE FROM public.databasechangeloglock;

    INSERT INTO public.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

    -- Lock Database
    UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = '172.18.0.1 (172.18.0.1)', LOCKGRANTED = '2016-05-06 13:38:52.583' WHERE ID = 1 AND LOCKED = FALSE;

    -- Create Database Change Log Table
    CREATE TABLE public.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

    -- Changeset changelog.xml::2016-05-05_1903::denarced
    -- Create the base table
    CREATE TABLE public.t_message (id BIGSERIAL NOT NULL, c_message VARCHAR(64) NOT NULL, CONSTRAINT PK_T_MESSAGE PRIMARY KEY (id));

    INSERT INTO public.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2016-05-05_1903', 'denarced', 'changelog.xml', NOW(), 1, '7:ee04d80bad3099ffaed5cc1cb2fcbe31', 'createTable tableName=t_message', 'Create the base table', 'EXECUTED', NULL, NULL, '3.5.0', '2531133134');

    -- Release Database Lock
    UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;
EOSQL

