package internetshop.service;

import java.util.List;
import internetshop.exeptions.DataProcessingException;
import internetshop.model.Item;
import internetshop.model.Order;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Item> items, Long userId) throws DataProcessingException;

    List<Order> getUserOrders(Long userId) throws DataProcessingException;

}
