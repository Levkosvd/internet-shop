package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
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
    public void create(Order entity) {
        orderDao.create(entity);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find "
                + "order by this Order ID!"));
    }

    @Override
    public void update(Order entity) {
        orderDao.update(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order newOrder = new Order();
        newOrder.setItems(items);
        newOrder.setIdUser(userId);
        newOrder.getTotalPrice();
        orderDao.create(newOrder);
        bucketService.clear(bucketService.getByUser(userId));
        return orderDao.get(newOrder.getId()).get();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getAll().stream()
                .filter(s -> s.getIdUser().equals(userService.get(userId).getId()))
                .collect(Collectors.toList());
    }
}
