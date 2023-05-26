package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import ru.lebedev.cronachecker.entity.ExchangeMarketEntity;

@Repository
public class DaoExchangeMarketImpl implements Dao<ExchangeMarketEntity> {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(ExchangeMarketEntity t) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
