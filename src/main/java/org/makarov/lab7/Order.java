package org.makarov.lab7;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class Order {
    private final Integer id;
    private final Integer userId;
    private final Map<Product, Integer> orderDetails;
    private double totalPrice;
    private static Integer counter = 0;

    public Order(Integer userId, Map<Product, Integer> orderDetails) {
        id = ++counter;
        this.userId = userId;
        this.orderDetails = orderDetails;
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = orderDetails.entrySet().stream().reduce(0.0, (sum, entry) ->
                sum + entry.getKey().getPrice() * entry.getValue(), Double::sum);
    }
}
