package org.makarov.lab9.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    private int id;
    private String title;
    private int price;
    private String description;
    private Category category;
    private List<String> images;
}
