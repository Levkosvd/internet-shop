package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {
    void create(T entity);

    Optional<T> get(I id);

    void update(T entity);

    boolean deleteById(I id);

    List<T> getAll();
}
