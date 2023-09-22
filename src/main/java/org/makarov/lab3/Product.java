package org.makarov.lab3;

import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private int price;

    public Product(String name, int price) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }
}
