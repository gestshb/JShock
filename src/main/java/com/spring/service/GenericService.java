package com.spring.service;


import java.util.List;


public interface GenericService<T> {

    T save(T entity);


    default void delete(T entity){};

    void delete(Long id);

    default void deleteInBatch(List<T> entities) {
    }


    T find(Long id);


    List<T> findAll();
}
