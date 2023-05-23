package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.lebedev.cronachecker.entity.HistoryExcangeMarket;

@Repository
public class DaoHistoryImpl implements Dao<HistoryExcangeMarket> {
    @PersistenceContext
    EntityManager entityManager;

    public DaoHistoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(HistoryExcangeMarket t) {
        entityManager.persist(t);
    }
}
