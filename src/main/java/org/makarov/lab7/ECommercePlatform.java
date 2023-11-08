package org.makarov.lab7;

import java.util.*;

public class ECommercePlatform {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();

    public Optional<User> getUserById(Integer userId) {
        return users.containsKey(userId)? Optional.of(users.get(userId)) : Optional.empty();
    }

    public Optional<Product> getProductById(Integer productId) {
        return products.containsKey(productId)? Optional.of(products.get(productId)) : Optional.empty();
    }

    public Optional<Order> getOrderById(Integer orderId) {
        return orders.containsKey(orderId)? Optional.of(orders.get(orderId)) : Optional.empty();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addUser(User... users) {
        Arrays.stream(users).forEach(this::addUser);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void addProduct(Product... products) {
        Arrays.stream(products).forEach(this::addProduct);
    }

    public void makeOrder(Integer userId) {
        User user = users.get(userId);
        Order order = new Order(user.getId(), user.getCart());

        user.getCart().forEach((product, amount) -> user.removeFromCart(product));
        user.updateHistory(order);

        order.getOrderDetails().forEach((product, amount) ->
                updateStock(product.getId(), product.getStock() - amount));
        orders.put(order.getId(), order);
    }

    public List<Product> makeListOfAvailableProducts() {
        return products.values().stream().filter(product -> product.getStock() > 0).toList();
    }

    public List<Product> makeListOfProducts() {
        return products.values().stream().toList();
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
