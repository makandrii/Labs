package org.makarov.lab8.models;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class WeatherStation {
    private String id;
    private double latitude;
    private double longitude;
}
