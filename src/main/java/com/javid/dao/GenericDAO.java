package com.javid.dao;

import java.util.List;

public interface GenericDAO <T>{
    public boolean create(T obj);
    public T read(int id);
    public void update(T obj);
    public void delete(int id);
    public List<T> getAll();
}
