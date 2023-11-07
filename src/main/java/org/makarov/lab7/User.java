package org.makarov.lab7;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private Integer id;
    private String username;
    private Map<Product, Integer> cart;
}
