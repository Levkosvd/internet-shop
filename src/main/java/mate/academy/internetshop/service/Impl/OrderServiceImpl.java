package mate.academy.internetshop.service.Impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Override
    public void create(Order entity) {
        orderDao.create(entity);
    }

    @Override
    public Order get(Long aLong) {
        return orderDao.get(aLong).get();
    }

    @Override
    public void update(Order entity) {
        orderDao.update(entity);
    }

    @Override
    public boolean deleteById(Long aLong) {
        return orderDao.deleteById(aLong);
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
    public Order completeOrder(List items, User user) {
        Order newOrder = new Order();
        newOrder.setItems(items);
        user.getUserOrders().add(newOrder);
        orderDao.create(newOrder);
        return orderDao.get(newOrder.getId()).get();
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return user.getUserOrders();
    }
}
