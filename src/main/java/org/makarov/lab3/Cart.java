package org.makarov.lab3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }
}
