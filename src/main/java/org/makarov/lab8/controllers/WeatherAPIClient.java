package org.makarov.lab8.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.makarov.lab8.models.City;
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
    private static final ObjectMapper mapper
            = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String url
            = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timelinemulti";
    private static final String query = "?unitGroup=metric" + "&key=EVXMPFYC4PUX74TBRL36ZW7EP";

    public static Optional<WeatherData> fetchDataFromAPI(String timePeriod, String... cities)
            throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response
                = client.send(makeRequest(timePeriod, cities), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return Optional.empty();
        }

        return Optional.of(mapper.readValue(response.body(), WeatherData.class));
    }

    public static Optional<City> fetchYearDataFromAPI(String city)
            throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response
                = client.send(makeRequest("yeartodate", city), HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return Optional.empty();
        }

        return Optional.of(mapper.readValue(response.body(), City.class));
    }

    private static String formatCities(String... cities) {
        return Arrays.stream(cities)
                .map(city -> city.replace(", ", "%2C"))
                .collect(Collectors.joining("%7C"));
    }

    private static HttpRequest makeRequest(String timePeriod, String... cities) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(url + query + String.format("&datestart=%s&locations=%s",
                        timePeriod,
                        formatCities(cities))))
                .build();
    }
}
