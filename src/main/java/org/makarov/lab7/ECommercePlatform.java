package org.makarov.lab7;

import lombok.Data;

import java.util.Map;

@Data
public class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;
}
