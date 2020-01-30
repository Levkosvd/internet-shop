package mate.academy.internetshop.service.impl;

import java.util.List;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public void create(Bucket entity)
            throws DataProcessingException {
        bucketDao.create(entity);
    }

    @Override
    public Bucket get(Long id)
            throws DataProcessingException {
        return bucketDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                        + "bucket by this Bucket ID!"));
    }

    @Override
    public void update(Bucket entity)
            throws DataProcessingException {
        bucketDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id)
            throws DataProcessingException {
        return bucketDao.deleteById(id);
    }

    @Override
    public List<Bucket> getAll()
            throws DataProcessingException {
        return bucketDao.getAll();
    }

    @Override
    public void addItem(Bucket bucket, Item item)
            throws DataProcessingException {
        bucket.getBucketItems().add(item);
        bucketDao.addItemToDb(bucket, item);
    }

    @Override
    public void deleteItem(Bucket bucket, Long itemId)
            throws DataProcessingException {
        bucket.getBucketItems().remove(itemDao.get(itemId).get());
        bucketDao.update(bucket);
    }

    @Override
    public void clear(Bucket bucket)
            throws DataProcessingException {
        bucket.getBucketItems().clear();
        bucketDao.update(bucket);
    }

    @Override
    public Bucket getByUser(Long userId)
            throws DataProcessingException {
        return bucketDao.getAll().stream()
            .filter((bucket) -> bucket.getIdUser().equals(userId))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Cant find bucket by this ID of User!"));
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getBucketItems();
    }
}
