package mate.academy.internetshop.service.impl;

import java.util.List;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
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
    public void create(Bucket entity) {
        bucketDao.create(entity);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                        + "bucket by this Bucket ID!"));
    }

    @Override
    public void update(Bucket entity) {
        bucketDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        return bucketDao.deleteById(id);
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
    public void deleteItem(Bucket bucket, Long id) {
        bucket.getBucketItems().remove(itemDao.get(id).get());
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.getBucketItems().clear();
    }

    @Override
    public Bucket getByUser(Long userId) {
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
