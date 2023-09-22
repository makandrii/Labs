package org.makarov.lab3;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public boolean add(Product product) {
        return products.add(product);
    }

    public boolean remove(Product product) {
        return products.remove(product);
    }

    public Order order() {
        var result = new Order(products);
        products = new ArrayList<>();
        return result;
    }
}
