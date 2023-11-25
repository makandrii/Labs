package org.makarov.lab8.services;

import org.jetbrains.annotations.NotNull;
import org.makarov.lab8.models.*;

import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class WeatherAnalyzer {
    private static final Comparator<WeatherDay> byTemperatureComparator = Comparator.comparingDouble(WeatherDay::getTemperature);
    private static final Comparator<WeatherDay> byHumidityComparator = Comparator.comparingDouble(WeatherDay::getHumidity);

    public static List<WeatherStation> findHottest(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byTemperatureComparator.reversed());
    }

    public static List<WeatherStation> findColdest(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byTemperatureComparator);
    }

    public static List<WeatherStation> findHighestHumidity(int maxCount, WeatherData data) {
        return sortStationsBy(maxCount, data, byHumidityComparator.reversed());
    }

    public static List<WeatherStation> findWithPrecipitation(int countOfDays, WeatherData data) {
        return normalizeData(data)
                .filter(city -> {
                    List<WeatherDay> weatherDays = city.getDays();
                    first: for (int firstPtr = 0; firstPtr < weatherDays.size() - countOfDays + 1; firstPtr++) {
                        for (int secondPtr = 0; secondPtr < countOfDays; secondPtr++) {
                            if (weatherDays.get(firstPtr + secondPtr).getPrecipitation() == 0) {
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

    public static List<WeatherStation> findWithIncreasedTemperature(int delta, int countOfDays, WeatherData data) {
        return normalizeData(data)
                .filter(city -> {
                    List<WeatherDay> weatherDays = city.getDays();
                    for (int firstPtr = 0; firstPtr < weatherDays.size() - countOfDays + 1; firstPtr++) {
                        for (int secondPtr = 0; secondPtr < countOfDays; secondPtr++) {
                            if (weatherDays.get(secondPtr).getTemperature() - weatherDays.get(firstPtr).getTemperature() >= delta) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .flatMap(city -> city.getStations().values().stream())
                .toList();
    }

    public static WeatherMonth findHighestWindSpeed(List<WeatherMonth> months) {
        return months.stream().max(Comparator.comparingDouble(WeatherMonth::windSpeed)).orElse(null);
    }

    private static List<WeatherStation> sortStationsBy(int maxCount, WeatherData data, Comparator<WeatherDay> comparator) {
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
