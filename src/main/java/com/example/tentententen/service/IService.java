package com.example.tentententen.service;

import java.util.List;

public interface IService<E> {
    List<E> fillAll();
    E findById(int id);
    void insert(E p);
    boolean delete(int id);
    boolean edit(int id,E t);

}
