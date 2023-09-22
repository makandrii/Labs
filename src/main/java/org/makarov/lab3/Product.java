package org.makarov.lab3;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
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
