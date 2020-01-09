package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private double accountBalance;
    private List<Order> userOrders;

    public User(String name, double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        userOrders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountBalance=" + accountBalance +
                ", userOrders=" + userOrders +
                '}';
    }
}
