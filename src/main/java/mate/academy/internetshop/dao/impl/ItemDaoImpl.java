package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.data.Storage;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    private static long idGenerator = 0L;

    @Override
    public void create(Item item) {
        item.setId(++idGenerator);
        Storage.items.add(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id - "
                        + id)));
    }

    @Override
    public void update(Item item) {
        Optional<Item> currentItem = Optional.ofNullable(Storage.items.stream()
                .filter(s -> s.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item by id - "
                        + item.getId())));

        Storage.items.set(Storage.items.indexOf(currentItem.get()), item);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Item> targetItem = Optional.ofNullable(Storage.items.stream()
                .filter((s) -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item by id - "
                        + id)));
        return Storage.items.remove(targetItem.get());
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
