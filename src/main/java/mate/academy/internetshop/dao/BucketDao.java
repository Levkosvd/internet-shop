package mate.academy.internetshop.dao;

import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao extends GenericDao<Bucket, Long> {

    void addItemToDb(Bucket bucket, Item item) throws DataProcessingException;
}
