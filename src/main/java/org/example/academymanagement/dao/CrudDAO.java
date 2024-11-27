package org.example.academymanagement.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean add(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    void delete(String id);

    T search(T entity) throws Exception;

    List<T> getAll() throws Exception;

    T exist(String id) throws Exception;
}
