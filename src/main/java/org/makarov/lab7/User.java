package org.makarov.lab7;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class User {
    private final Integer id;
    private final String username;
    private Map<Product, Integer> cart;
    private final Map<Product, Integer> history;
    private static Integer counter = 0;

    public User(@NotNull String name) {
        id = ++counter;
        username = name;
        cart = new HashMap<>();
        history = new HashMap<>();
    }

    public void addToCart(@NotNull Product product, @NotNull Integer amount) {
        cart.put(product, amount);
    }

    public void removeFromCart(@NotNull Product product) {
        cart.remove(product);
    }

    public void modifyInCart(@NotNull Product product, @NotNull Integer amount) {
        cart.merge(product, amount, Integer::sum);
    }

    public void clearCart() {
        cart = new HashMap<>();
    }

    public void updateHistory(@NotNull Order order) {
        order.getOrderDetails().forEach(((product, amount) -> history.merge(product, amount, Integer::sum)));
    }
}
