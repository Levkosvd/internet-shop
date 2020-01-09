package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

import java.util.List;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket); //remove all items from bucket

    List<Item> getAllItems(Bucket bucket);

}
