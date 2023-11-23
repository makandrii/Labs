package org.makarov.lab8.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Day {
    private LocalDate date;
    @JsonProperty("temp")
    private double temperature;
    private double humidity;
    @JsonProperty("precip")
    private double precipitation;
    @JsonProperty("wspd")
    private double windSpeed;
}