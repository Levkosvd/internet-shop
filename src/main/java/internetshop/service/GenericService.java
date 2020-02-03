package internetshop.service;

import java.util.List;
import internetshop.exeptions.DataProcessingException;

public interface GenericService<T, I> {

    void create(T entity) throws DataProcessingException;

    T get(I id) throws DataProcessingException;

    void update(T entity) throws DataProcessingException;

    boolean deleteById(I id) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
