package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

import java.util.List;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List items, User user);

    List<Order> getUserOrders(User user);

}
