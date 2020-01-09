package mate.academy.internetshop.service.Impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public void create(Item entity) {
        itemDao.create(entity);
    }

    @Override
    public Item get(Long aLong) {
        return itemDao.get(aLong).get();
    }

    @Override
    public void update(Item entity) {
        itemDao.update(entity);
    }

    @Override
    public boolean deleteById(Long aLong) {
        return itemDao.deleteById(aLong);
    }

    @Override
    public boolean delete(Item entity) {
        return itemDao.delete(entity);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public List getAllItems() {
        return itemDao.getAll();
    }
}
