package org.makarov.lab7;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private double price;
    private int stock;
}
