package com.andela.plans.service;

import java.util.List;

public interface BaseService<T> {
    T findOne(Integer id);

    void save(T entity);

    void delete(Integer id);

    List<T> findAll();
}
