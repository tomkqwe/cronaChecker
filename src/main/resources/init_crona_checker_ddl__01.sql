-- CREATE DATABASE crona_checker;
-- CREATE SCHEMA exchange_market;


CREATE TABLE crona_repository
(
    id       SERIAL,
    country  TEXT    NOT NULL,
    currency TEXT    NOT NULL,
    amount   INTEGER NOT NULL,
    code     TEXT    NOT NULL,
    rate     NUMERIC NOT NULL
);

DROP TABLE crona_repository;
