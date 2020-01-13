package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;

import mate.academy.internetshop.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserService userService;

    @Override
    public void create(Order entity) {
        orderDao.create(entity);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
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
    public boolean delete(Order entity) {
        return orderDao.delete(entity);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        Order newOrder = new Order();
        newOrder.getItems().addAll(items);
        newOrder.setIdUser(userId);
        newOrder.getTotalPrice();
        orderDao.create(newOrder);
        return orderDao.get(newOrder.getId()).get();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getAll().stream()
                .filter(s -> s.getIdUser().equals(userService.get(userId).getId()))
                .collect(Collectors.toList());
    }
}
