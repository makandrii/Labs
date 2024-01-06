package org.makarov.lab9.excel.mappers;

import org.makarov.lab9.excel.dto.CategoryDTO;
import org.makarov.lab9.models.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
