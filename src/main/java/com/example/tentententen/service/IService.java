package com.example.tentententen.service;

import java.util.List;

public interface IService<T> {
    List<T> fillAll();
    T findById(int id);
    void insert(T p);
    void delete(int id);
    void edit(int id,T t);

}
