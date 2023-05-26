package ru.lebedev.cronachecker.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.lebedev.cronachecker.entity.HistoryExcangeMarket;

@Repository
@AllArgsConstructor
public class DaoHistoryImpl implements Dao<HistoryExcangeMarket> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(HistoryExcangeMarket t) {
        entityManager.persist(t);
    }
}
