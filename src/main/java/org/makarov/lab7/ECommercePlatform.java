package org.makarov.lab7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECommercePlatform {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void makeOrder(Integer userId) {
        User user = users.get(userId);
        Order order = new Order(user.getId(), user.getCart());
        orders.put(order.getId(), order);
    }

    public List<Product> makeListOfAvailableProducts() {
        return products.values().stream().filter(product -> product.getStock() > 0).toList();
    }

    public List<User> makeListOfUsers() {
        return users.values().stream().toList();
    }

    public List<Order> makeListOfOrders() {
        return orders.values().stream().toList();
    }

    public void updateStock(Integer productId, int newStock) {
        Product product = products.get(productId);
        product.setStock(newStock);
    }
}
