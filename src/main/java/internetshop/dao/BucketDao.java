package internetshop.dao;

import internetshop.exeptions.DataProcessingException;
import internetshop.model.Bucket;
import internetshop.model.Item;

public interface BucketDao extends GenericDao<Bucket, Long> {

    void addItemToDb(Bucket bucket, Item item) throws DataProcessingException;
}
