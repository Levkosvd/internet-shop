package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Long id);

    void clear(Bucket bucket);

    Bucket getByUser(Long userId);

    List<Item> getAllItems(Bucket bucket);

}
