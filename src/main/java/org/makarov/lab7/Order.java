package org.makarov.lab7;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
@ToString
public class Order {
    private final Integer id;
    private final Integer userId;
    private final Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(@NotNull Integer id, @NotNull Integer userId, @NotNull Map<Product, Integer> orderDetails) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = orderDetails;
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = orderDetails.entrySet().stream().reduce(0.0, (sum, entry) ->
                sum + entry.getKey().getPrice() * entry.getValue(), Double::sum);
    }
}
