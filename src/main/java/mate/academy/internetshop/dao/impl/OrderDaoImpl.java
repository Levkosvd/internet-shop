package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.data.Storage;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static long idGenerator = 0L;

    @Override
    public void create(Order order) {
        order.setId(++idGenerator);
        Storage.orders.add(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return Optional.ofNullable(Storage.orders
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id - "
                        + id)));
    }

    @Override
    public void update(Order order) {
        Optional<Order> currentOrder = Optional.ofNullable(Storage.orders.stream()
                .filter(s -> s.getId().equals(order.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order by id - "
                        + order.getId())));

        Storage.orders.set(Storage.orders.indexOf(currentOrder.get()), order);
    }

    @Override
    public boolean deleteById(Long id) {
        Order targetItem = Storage.orders.stream()
                .filter((s) -> s.getId().equals(id))
                .findFirst().get();
        return Storage.orders.remove(targetItem);
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
