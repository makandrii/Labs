package org.makarov.lab3;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private List<Product> products;
    @Getter
    private Status status;

    public Order(List<Product> products) {
        id = UUID.randomUUID().toString();
        this.products = products;
        status = Status.Pending;
    }
}
