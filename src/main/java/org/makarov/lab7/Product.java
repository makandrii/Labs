package org.makarov.lab7;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Product {
    private final Integer id;
    private final String name;
    @Setter
    private double price;
    @Setter
    private int stock;
    private static Integer counter = 0;

    public Product(String name, double price, int stock) {
        id = ++counter;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
