package com.revature.auction.services;

import java.util.List;

public interface CrudService<T>
{
    List<T> getAll();
    T findById(int id);
    T update(int id);
    T add(T object);
    int delete(int id);
}
