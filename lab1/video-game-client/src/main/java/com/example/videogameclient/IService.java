package com.example.videogameclient;

import java.util.List;

public interface IService<T> {
    T Add(T item);
    void DeleteById(Integer id);
    T Update(T item);
    List<T> GetAll();
    T GetById(Integer id);
}
