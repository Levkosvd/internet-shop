package mate.academy.internetshop.service.Impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

import java.util.List;
@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public void create(Bucket entity) {
        bucketDao.create(entity);
    }

    @Override
    public Bucket get(Long aLong) {
        return bucketDao.get(aLong).get();
    }

    @Override
    public void update(Bucket entity) {
        bucketDao.update(entity);
    }

    @Override
    public boolean deleteById(Long aLong) {
        return bucketDao.deleteById(aLong);
    }

    @Override
    public boolean delete(Bucket entity) {
        return bucketDao.delete(entity);
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucket.getBucketItems().add(item);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucket.getBucketItems().remove(item);
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.getBucketItems().clear();
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getBucketItems();
    }
}
