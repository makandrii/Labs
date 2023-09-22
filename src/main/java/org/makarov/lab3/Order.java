package org.makarov.lab3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private String id;
    private List<Product> products;
    private String status;
}
