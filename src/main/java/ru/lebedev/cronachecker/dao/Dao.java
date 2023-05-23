package ru.lebedev.cronachecker.dao;

import org.springframework.transaction.annotation.Transactional;

public interface Dao<T> {
    @Transactional
    void save(T t);
}
