package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

@Repository
@AllArgsConstructor
public class DaoExchangeMarketImpl implements Dao<ExchangeMarketEntity> {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void save(ExchangeMarketEntity t) {
        entityManager.persist(t);
    }

}
