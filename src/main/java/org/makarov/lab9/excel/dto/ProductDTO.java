package org.makarov.lab9.excel.dto;

import lombok.Builder;

@Builder
public class ProductDTO {
    private int id;
    private String title;
    private int price;
    private String description;
    private String category;
}
