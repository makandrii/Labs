package org.makarov.lab7;

import lombok.Data;

import java.util.Map;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;
}
