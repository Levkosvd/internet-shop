package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    public void create(Order entity)
            throws DataProcessingException {
        orderDao.create(entity);
    }

    @Override
    public Order get(Long id)
            throws DataProcessingException {
        return orderDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                + "order by this Order ID!"));
    }

    @Override
    public void update(Order entity)
            throws DataProcessingException {
        orderDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id)
            throws DataProcessingException {
        return orderDao.deleteById(id);
    }

    @Override
    public List<Order> getAll()
            throws DataProcessingException {
        return orderDao.getAll();
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId)
            throws DataProcessingException {
        Order newOrder = new Order();
        newOrder.setItems(items);
        newOrder.setIdUser(userId);
        newOrder.getTotalPrice();
        orderDao.create(newOrder);
        bucketService.clear(bucketService.getByUser(userId));
        return orderDao.get(newOrder.getId()).get();
    }

    @Override
    public List<Order> getUserOrders(Long userId)
            throws DataProcessingException {
        return orderDao.getAll().stream()
                .filter(s -> {
                    try {
                        return s.getIdUser().equals(userService.get(userId).getId());
                    } catch (DataProcessingException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
