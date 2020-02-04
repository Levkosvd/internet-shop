package internetshop.service;

import internetshop.exeptions.DataProcessingException;
import java.util.List;

public interface GenericService<T, I> {

    void create(T entity) throws DataProcessingException;

    T get(I id) throws DataProcessingException;

    void update(T entity) throws DataProcessingException;

    boolean deleteById(I id) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
