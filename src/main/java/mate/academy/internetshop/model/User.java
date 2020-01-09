package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private double accountBalance;
    private List<Order> userOrdersList;

    public User(String name, double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        userOrdersList = new ArrayList<>();
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

    public List<Order> getUserOrdersList() {
        return userOrdersList;
    }

    public void setUserOrdersList(List<Order> userOrders) {
        this.userOrdersList = userOrders;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\''
                + ", accountBalance=" + accountBalance + ", userOrders=" + userOrdersList + '}';
    }
}
