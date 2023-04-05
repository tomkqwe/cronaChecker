package ru.lebedev.cronachecker.dao;

import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

public interface Dao {
    void save(ExchangeMarketEntity exchangeMarket);
}
