package DAO;

import java.util.List;

public interface IDao<T> {
    List<T> getAll();

    T getById(Integer id);

    T create(T t);

    T update(T t);

    void delete(Integer id);

}
