package org.makarov.lab7;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
public class Product implements Comparable<Product> {
    private final Integer id;
    private final String name;
    @Setter
    private double price;
    @Setter
    private int stock;

    public Product(@NotNull Integer id, @NotNull String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(price, o.price);
    }
}
