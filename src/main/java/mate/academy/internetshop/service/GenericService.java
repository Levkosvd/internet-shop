package mate.academy.internetshop.service;

import java.util.List;


public interface GenericService<T, ID> {

    void create(T entity);

    T get(ID id);

    void update(T entity);

    boolean deleteById(ID id);

    boolean delete(T entity);

    List<T> getAll();
}
