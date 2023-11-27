package org.makarov.lab8.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class City {
    @JsonProperty("address")
    private String name;
    private List<WeatherDay> days;
    private Map<String, WeatherStation> stations;
}
