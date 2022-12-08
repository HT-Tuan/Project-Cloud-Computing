package com.app.model.dao;

public interface IAbstracDao<T> {
    public boolean insert(T entity);

    public boolean update(T entity);

    public boolean delete(T entity);
}
