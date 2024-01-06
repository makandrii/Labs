package org.makarov.lab9.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.makarov.lab9.models.Category;
import org.makarov.lab9.models.Product;
import org.makarov.lab9.models.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Client {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String url = "https://api.escuelajs.co/api/v1";

    public static List<User> getUsers()
            throws URISyntaxException, IOException, InterruptedException {
        return mapper.readValue(getJSONRequest("/users"), new TypeReference<List<User>>() {});
    }

    public static List<Product> getProducts()
            throws URISyntaxException, IOException, InterruptedException {
        return mapper.readValue(getJSONRequest("/products"), new TypeReference<List<Product>>() {});
    }

    public static List<Category> getCategories()
            throws URISyntaxException, IOException, InterruptedException {
        return mapper.readValue(getJSONRequest("/categories"), new TypeReference<List<Category>>() {});
    }

    private static String getJSONRequest(String path)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url + path))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
