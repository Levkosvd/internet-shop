package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Order;


public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List items, Long userId);

    List<Order> getUserOrders(Long userId);

}
