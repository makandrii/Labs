package org.makarov.lab7;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class User {
    private final Integer id;
    private final String username;
    private final Map<Product, Integer> cart;
    private static Integer counter = 0;

    public User(String name) {
        id = ++counter;
        username = name;
        cart = new HashMap<>();
    }

    public void addToCart(Product product, Integer amount) {
        cart.put(product, amount);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public void modifyInCart(Product product, Integer amount) {
        cart.merge(product, amount, Integer::sum);
    }
}
