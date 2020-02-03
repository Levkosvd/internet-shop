package internetshop.service;

import java.util.List;
import internetshop.exeptions.DataProcessingException;
import internetshop.model.Bucket;
import internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item) throws DataProcessingException;

    void deleteItem(Bucket bucket, Long id) throws DataProcessingException;

    void clear(Bucket bucket) throws DataProcessingException;

    Bucket getByUser(Long userId) throws DataProcessingException;

    List<Item> getAllItems(Bucket bucket);

}
