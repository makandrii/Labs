package org.makarov.lab8;

import org.makarov.lab8.models.WeatherMonth;
import org.makarov.lab8.models.WeatherStation;
import org.makarov.lab8.table.Row;
import org.makarov.lab8.controllers.WeatherAPIClient;
import org.makarov.lab8.models.WeatherData;
import org.makarov.lab8.services.WeatherAnalyzer;
import org.makarov.lab8.services.WeatherStatistic;
import org.makarov.lab8.table.Table;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class WeatherDemo {
    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException {
        String[] cities = new String[]{
                "Lviv, UA",
                "Dnipro, UA",
                "Kharkiv, UA",
                "Odesa, UA",
                "Kyiv, UA",
                "Vinnytsia, UA",
                "Uzhhorod, UA",
                "Chernivtsi, UA",
                "Lutsk, UA",
                "Kropyvnytskyi, UA"
        };

        WeatherData data = WeatherAPIClient.fetchDataFromAPI("last6days", cities).orElse(null);

        var hottest = WeatherAnalyzer.findHottest(10, data);
        var coldest = WeatherAnalyzer.findColdest(10, data);
        var humidity = WeatherAnalyzer.findHighestHumidity(10, data);
        var precipitations = WeatherAnalyzer.findWithPrecipitation(7, data);
        var increased = WeatherAnalyzer.findWithIncreasedTemperature(5, 5, data);
        var statistic = WeatherStatistic.makeMonthStatistic(WeatherAPIClient.fetchYearDataFromAPI("Lviv, UA").get());
        var windSpeed = WeatherAnalyzer.findHighestWindSpeed(statistic);

        printData(hottest, WeatherStation.class);
        printData(coldest, WeatherStation.class);
        printData(humidity, WeatherStation.class);
        printData(precipitations, WeatherStation.class);
        printData(increased, WeatherStation.class);
        printData(statistic, WeatherMonth.class);
        printData(List.of(windSpeed), WeatherMonth.class);
    }

    private static <T> void printData(List<T> list, Class<T> type) {
        Field[] fields = type.getDeclaredFields();

        Table table = new Table();
        Row header = table.createRow();
        Arrays.stream(fields).forEach(field -> header.createCell(field.getName()));

        list.forEach(data -> {
            Row row = table.createRow();
            Arrays.stream(fields).forEach(field -> {
                try {
                    field.setAccessible(true);
                    Object value = field.get(data);
                    field.setAccessible(false);
                    if (field.getType() == String.class) {
                        row.createCell((String) value);
                    } else if (field.getType() == double.class) {
                        row.createCell((double) value);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        table.print();
    }
}
