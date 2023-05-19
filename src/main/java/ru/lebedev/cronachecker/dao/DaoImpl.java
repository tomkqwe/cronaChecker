package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

@Repository
public class DaoImpl implements Dao {
    @PersistenceContext
    private final EntityManager entityManager;


    public DaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(ExchangeMarketEntity exchangeMarket) {
        entityManager.persist(exchangeMarket);
    }
}
