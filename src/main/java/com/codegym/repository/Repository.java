package com.codegym.repository;

import java.util.List;

public interface Repository <T> {
    List<T> findAll();

    void save(T t);

    List<T> findByName(String name);

}
