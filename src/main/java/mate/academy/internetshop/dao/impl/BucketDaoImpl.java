package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.data.Storage;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Bucket;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {
    private static long idGenerator = 0L;
    @Override
    public void create(Bucket bucket) {
        bucket.setId(++idGenerator);
        Storage.buckets.add(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Optional.ofNullable(Storage.buckets.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket by id - " + id)));
    }

    @Override
    public void update(Bucket bucket) {
        Optional<Bucket> currentItem = Optional.ofNullable(Storage.buckets.stream()
                .filter(s -> s.getId().equals(bucket.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket by id - " + bucket.getId())));
        Storage.buckets.set(Storage.buckets.indexOf(currentItem.get()), bucket);
    }

    @Override
    public boolean deleteById(Long id) {
        Bucket targetItem = Storage.buckets.stream().filter((s) -> s.getId().equals(id)).findFirst().get();
        return Storage.buckets.remove(targetItem);
    }

    @Override
    public boolean delete(Bucket item) {

        return Storage.buckets.remove(item);
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
