-- CREATE DATABASE crona_checker;
-- CREATE SCHEMA exchange_market;


DROP TABLE IF EXISTS exchange_market.crona_repository;
CREATE TABLE exchange_market.crona_repository
(
id       SERIAL,
country  TEXT    NOT NULL,
currency TEXT    NOT NULL,
amount   INTEGER NOT NULL,
code     TEXT    NOT NULL,
rate     NUMERIC NOT NULL,
currency_date DATE NOT NULL,
insert_timestamp TIMESTAMP NOT NULL
);


