package org.makarov.lab8.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WeatherDay {
    private LocalDate date;
    @JsonProperty("temp")
    private double temperature;
    private double humidity;
    @JsonProperty("precip")
    private double precipitation;
    @JsonProperty("windspeed")
    private double windSpeed;

    @JsonProperty("datetime")
    public void parseDate(String datetime) {
        date = LocalDate.parse(datetime);
    }
}