package com.codegym.service;

public interface GeneralService<T> {

    Iterable<T> findAll();

    void save(T t);

    Iterable<T> findByName(String name);

    void deleteById(Long id);

    T findById(Long id);
}
