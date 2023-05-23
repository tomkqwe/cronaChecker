-- CREATE DATABASE crona_checker;
-- CREATE SCHEMA exchange_market;


DROP TABLE IF EXISTS exchange_market.history_crona_repository;
CREATE TABLE exchange_market.history_crona_repository
(
    id               SERIAL,
    currency_date    DATE      NOT NULL,

    aud              NUMERIC,
    bgn              NUMERIC,
    brl              NUMERIC,
    cad              NUMERIC,
    chf              NUMERIC,
    cny              NUMERIC,
    dkk              NUMERIC,
    eur              NUMERIC,
    gbp              NUMERIC,
    hkd              NUMERIC,
    hrk              NUMERIC,
    huf              NUMERIC,
    idr              NUMERIC,
    ils              NUMERIC,
    inr              NUMERIC,
    isk              NUMERIC,
    jpy              NUMERIC,
    krw              NUMERIC,
    mxn              NUMERIC,
    myr              NUMERIC,
    nok              NUMERIC,
    nzd              NUMERIC,
    php              NUMERIC,
    pln              NUMERIC,
    ron              NUMERIC,
    rub              NUMERIC,
    sek              NUMERIC,
    sgd              NUMERIC,
    thb              NUMERIC,
    try              NUMERIC,
    usd              NUMERIC,
    xdr              NUMERIC,
    zar              NUMERIC,

    currency_year    TEXT      NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL
);





