package com.sabulous.contractRateManager.services;

import java.util.List;

public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T obj);

    void delete(Integer id);
}