package ru.lebedev.cronachecker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Dao<T>  {
    @Transactional
    void save(T t);
}
