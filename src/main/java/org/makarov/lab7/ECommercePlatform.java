package org.makarov.lab7;

import org.jetbrains.annotations.NotNull;
import org.makarov.lab7.exceptions.*;

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

    public void addUser(@NotNull User user) {
        users.put(user.getId(), user);
    }

    public void addUser(@NotNull User... users) {
        Arrays.stream(users).forEach(this::addUser);
    }

    public void addProduct(@NotNull Product product) {
        products.put(product.getId(), product);
    }

    public void addProduct(@NotNull Product... products) {
        Arrays.stream(products).forEach(this::addProduct);
    }

    public void makeOrder(Integer userId) throws UserNotFoundException {
        User user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Order order = new Order(user.getId(), user.getCart());

        user.clearCart();
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

    public void updateStock(Integer productId, int newStock) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }

        product.setStock(newStock);
    }

    public List<Product> makeRecommendations(Integer userId) throws UserNotFoundException {
        User user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        return user.getHistory().entrySet()
                .stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }
}
