package org.makarov.lab7;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private Integer id;
    private String username;
    private Map<Product, Integer> cart;

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
