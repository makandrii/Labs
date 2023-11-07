package org.makarov.lab7;

import lombok.Data;

import java.util.Map;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public double calculateTotalPrice() {
        return totalPrice = orderDetails.entrySet().stream().reduce(0.0, (sum, entry) ->
                sum + entry.getKey().getPrice() * entry.getValue(), Double::sum);
    }
}
