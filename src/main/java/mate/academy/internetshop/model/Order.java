package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<Item> items;
    private double totalPrice;

    public Order() {
        items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    private void totalSum() {
        totalPrice = items.stream().mapToDouble(Item::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", user=" + user +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
