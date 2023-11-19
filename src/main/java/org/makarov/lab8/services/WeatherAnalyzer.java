package org.makarov.lab8.services;

import org.makarov.lab8.models.City;
import org.makarov.lab8.models.Day;
import org.makarov.lab8.models.Station;
import org.makarov.lab8.models.WeatherData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class WeatherAnalyzer {
    private static final Comparator<Day> byTemperatureComparator = Comparator.comparingDouble(Day::getTemperature);
    private static final Comparator<Day> byHumidityComparator = Comparator.comparingDouble(Day::getHumidity);

    public static List<Station> findHottest(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byTemperatureComparator.reversed());
    }

    public static List<Station> findColdest(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byTemperatureComparator);
    }

    public static List<Station> findHighestHumidity(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byHumidityComparator.reversed());
    }

    public static List<Station> findWithPrecipitation(int countOfDays, WeatherData data) {
        return normalizeData(data)
                .filter(city -> {
                    List<Day> days = city.getDays();
                    first: for (int firstPtr = 0; firstPtr < days.size() - countOfDays + 1; firstPtr++) {
                        for (int secondPtr = 0; secondPtr < countOfDays; secondPtr++) {
                            if (days.get(firstPtr + secondPtr).getPrecipitation() == 0) {
                                continue first;
                            }
                        }
                        return true;
                    }
                    return false;
                })
                .flatMap(city -> city.getStations().values().stream())
                .toList();
    }

    public static List<Station> findWithIncreasedTemperature(int delta, int countOfDays, WeatherData data) {
        return normalizeData(data)
                .filter(city -> {
                    List<Day> days = city.getDays();
                    for (int firstPtr = 0; firstPtr < days.size() - countOfDays + 1; firstPtr++) {
                        for (int secondPtr = 0; secondPtr < countOfDays; secondPtr++) {
                            if (days.get(secondPtr).getTemperature() - days.get(firstPtr).getTemperature() >= delta) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .flatMap(city -> city.getStations().values().stream())
                .toList();
    }

    private static List<Station> sortStationsBy(int maxCount, WeatherData data, Comparator<Day> comparator) {
        return normalizeData(data)
                .sorted(Comparator.comparing(
                        c -> c.getDays().get(c.getDays().size() - 1),
                        comparator))
                .flatMap(city -> city.getStations().values().stream())
                .limit(maxCount)
                .toList();
    }

    private static Stream<City> normalizeData(WeatherData data) {
        return data.getLocations().stream()
                .filter(city -> city.getStations() != null);
    }
}
