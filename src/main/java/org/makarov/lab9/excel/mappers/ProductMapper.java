package org.makarov.lab9.excel.mappers;

import org.makarov.lab9.excel.dto.ProductDTO;
import org.makarov.lab9.models.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory().getName())
                .build();
    }
}
