package internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import internetshop.dao.ItemDao;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public void create(Item entity)
            throws DataProcessingException {
        itemDao.create(entity);
    }

    @Override
    public Item get(Long id)
            throws DataProcessingException {
        return itemDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                        + "item by this Item ID!"));
    }

    @Override
    public void update(Item entity)
            throws DataProcessingException {
        itemDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id)
            throws DataProcessingException {
        return itemDao.deleteById(id);
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        return itemDao.getAll();
    }
}
