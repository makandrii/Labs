package org.makarov.lab8.services;

import org.makarov.lab8.models.Day;
import org.makarov.lab8.models.Station;
import org.makarov.lab8.models.WeatherData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
                .sorted((c1, c2) -> {
                    List<Day> d1 = c1.getDays();
                    List<Day> d2 = c2.getDays();
                    return comparator.compare(d1.get(d1.size() - 1), d2.get(d2.size() - 1));
                })
                .flatMap(city -> city.getStations().values().stream())
                .limit(count)
                .reduce(new ArrayList<>(), (list, station) -> {
                    list.add(station);
                    return list;
                }, (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                });
    }
}
