package ro.store.inventory.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Connects Users, Stores, and multiple Products together into a transaction.
 */
public class Order {
    private int id;
    private User user;
    private Store store;
    private List<AbstractProduct> orderedProducts;
    private LocalDateTime orderDate;

    public Order(int id, User user, Store store, List<AbstractProduct> orderedProducts, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.store = store;
        this.orderedProducts = orderedProducts;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<AbstractProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<AbstractProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double calculateTotal() {
        return orderedProducts.stream().mapToDouble(AbstractProduct::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + orderDate + ", total=" + calculateTotal() + '}';
    }
}