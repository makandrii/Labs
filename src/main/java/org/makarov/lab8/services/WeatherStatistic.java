package org.makarov.lab8.services;

import org.makarov.lab8.models.City;
import org.makarov.lab8.models.WeatherDay;
import org.makarov.lab8.models.WeatherMonth;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherStatistic {
    public static List<WeatherMonth> makeMonthStatistic(City city) {
        List<WeatherMonth> result = new ArrayList<>();
        groupDaysByMonth(city.getDays()).forEach(((month, days) -> {
            double avrTemperature = 0, avrHumidity = 0, avrPrecipitation = 0, avrWindSpeed = 0;
            int size = days.size();
            for (WeatherDay day : days) {
                avrTemperature += day.getTemperature();
                avrHumidity += day.getHumidity();
                avrPrecipitation += day.getPrecipitation();
                avrWindSpeed += day.getWindSpeed();
            }

            result.add(new WeatherMonth(
                    month,
                    avrTemperature / size,
                    avrHumidity / size,
                    avrPrecipitation / size,
                    avrWindSpeed / size));
        }));

        return result;
    }

    private static Map<Month, List<WeatherDay>> groupDaysByMonth(List<WeatherDay> days) {
        Map<Month, List<WeatherDay>> result = new HashMap<>();
        days.forEach(day -> {
            List<WeatherDay> list = new ArrayList<>();
            list.add(day);
            result.merge(day.getDate().getMonth(), list, (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            });
        });
        return result;
    }
}
