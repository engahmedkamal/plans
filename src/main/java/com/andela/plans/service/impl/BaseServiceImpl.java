package com.andela.plans.service.impl;

import com.andela.plans.repository.BaseRepository;
import com.andela.plans.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseRepository<T> baseRepository;

    @Override
    public T findOne(Integer id) {
        return baseRepository.findById(id).get();
    }

    @Override
    public void save(T entity) {
        baseRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        baseRepository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }
}
