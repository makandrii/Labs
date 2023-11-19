package org.makarov.lab8.services;

import org.makarov.lab8.models.Day;
import org.makarov.lab8.models.Station;
import org.makarov.lab8.models.WeatherData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAnalyzer {
    private static final Comparator<Day> byTemperatureComparator
            = Comparator.comparingDouble(Day::getTemperature);

    public static List<Station> findHottestStations(int count, WeatherData data) {
        return findStationsSortedBy(count, data, byTemperatureComparator.reversed());
    }

    public static List<Station> findColdestStations(int count, WeatherData data) {
        return findStationsSortedBy(count, data, byTemperatureComparator);
    }

    private static List<Station> findStationsSortedBy(int count, WeatherData data, Comparator<Day> comparator) {
        return data.getLocations().stream()
                .sorted(Comparator.comparing(
                        c -> c.getDays().get(c.getDays().size() - 1),
                        comparator))
                .filter(city -> city.getStations() != null)
                .flatMap(city -> city.getStations().values().stream())
                .limit(count)
                .collect(Collectors.toList());
    }
}
