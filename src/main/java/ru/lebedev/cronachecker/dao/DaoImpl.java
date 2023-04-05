package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

@Repository
public class DaoImpl implements Dao {
    private EntityManager entityManager;

    @Autowired
    public DaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(ExchangeMarketEntity exchangeMarket) {
        entityManager.persist(exchangeMarket);

    }
}
