package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;


public interface GenericDao<T, ID> {

        void create(T entity);

        Optional<T> get(ID id);

        void update(T entity);

        boolean deleteById(ID id);

        boolean delete(T entity);

        List<T> getAll();
}
