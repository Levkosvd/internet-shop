package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

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
