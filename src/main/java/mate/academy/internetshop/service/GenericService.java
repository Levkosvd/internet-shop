package mate.academy.internetshop.service;

import java.util.List;

public interface GenericService<T, I> {

    void create(T entity);

    T get(I id);

    void update(T entity);

    boolean deleteById(I id);

    List<T> getAll();
}
