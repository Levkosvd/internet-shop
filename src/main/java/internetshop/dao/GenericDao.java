package internetshop.dao;

import internetshop.exeptions.DataProcessingException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {
    void create(T entity) throws DataProcessingException;

    Optional<T> get(I id) throws DataProcessingException;

    void update(T entity) throws DataProcessingException;

    boolean deleteById(I id) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
