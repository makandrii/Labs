package org.makarov.lab8.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.makarov.lab8.models.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeatherAPIClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String url
            = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timelinemulti";
    private static final String query = "?datestart=last7days" +
            "&unitGroup=metric" +
            String.format("&key=%s", System.getProperty("apiKey"));

    public static Optional<WeatherData> fetchDataFromAPI(String... cities)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url + query + String.format("&locations=%s", formatCities(cities))))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return Optional.empty();
        }

        ObjectMapper mapper
                = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WeatherData data = mapper.readValue(response.body(), WeatherData.class);

        return Optional.of(data);
    }

    private static String formatCities(String... cities) {
        return Arrays.stream(cities)
                .map(city -> city.replace(", ", "%2C"))
                .collect(Collectors.joining("%7C"));
    }
}
