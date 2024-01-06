package org.makarov.lab9;

import org.makarov.lab9.controllers.Client;
import org.makarov.lab9.excel.dto.CategoryDTO;
import org.makarov.lab9.excel.dto.ProductDTO;
import org.makarov.lab9.excel.dto.UserDTO;
import org.makarov.lab9.excel.mappers.CategoryMapper;
import org.makarov.lab9.excel.mappers.ProductMapper;
import org.makarov.lab9.excel.mappers.UserMapper;
import org.makarov.lab9.models.Category;
import org.makarov.lab9.models.Product;
import org.makarov.lab9.models.User;
import org.makarov.lab9.excel.ExcelWriter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Demo {
    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException, IllegalAccessException {
        List<User> users = Client.getUsers();
        List<Product> products = Client.getProducts();
        List<Category> categories = Client.getCategories();

        ExcelWriter.createSheet("Users", users.stream()
                .map(UserMapper::toDTO).toList(), UserDTO.class);
        ExcelWriter.createSheet("Products", products.stream()
                .map(ProductMapper::toDTO).toList(), ProductDTO.class);
        ExcelWriter.createSheet("Categories", categories.stream()
                .map(CategoryMapper::toDTO).toList(), CategoryDTO.class);

        ExcelWriter.write("Data");
    }
}
