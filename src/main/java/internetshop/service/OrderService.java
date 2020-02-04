package internetshop.service;

import internetshop.exeptions.DataProcessingException;
import internetshop.model.Item;
import internetshop.model.Order;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Item> items, Long userId) throws DataProcessingException;

    List<Order> getUserOrders(Long userId) throws DataProcessingException;

}
