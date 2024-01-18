--liquibase formatted sql

--changeset lumme1:6
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username text
);

--change lumme2:3
INSERT INTO "user" (username) VALUES ('admin');