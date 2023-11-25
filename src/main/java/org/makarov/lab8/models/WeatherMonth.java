package org.makarov.lab8.models;

import java.time.Month;

public record WeatherMonth(Month name, double temperature, double humidity, double precipitation, double windSpeed) {}
