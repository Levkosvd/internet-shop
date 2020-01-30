package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Item> items, Long userId) throws DataProcessingException;

    List<Order> getUserOrders(Long userId) throws DataProcessingException;

}
