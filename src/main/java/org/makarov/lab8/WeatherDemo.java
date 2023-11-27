package org.makarov.lab8;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.makarov.lab8.controllers.WeatherAPIClient;
import org.makarov.lab8.models.WeatherData;
import org.makarov.lab8.models.WeatherMonth;
import org.makarov.lab8.models.WeatherStation;
import org.makarov.lab8.services.WeatherAnalyzer;
import org.makarov.lab8.services.WeatherStatistic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;

public class WeatherDemo {
    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException, IllegalAccessException {
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

        System.setProperty(
                "-Dlog4j2.loggerContextFactory", "org.apache.logging.log4j.simple.SimpleLoggerContextFactory");

        Workbook book = new XSSFWorkbook();

        makeSheet(book, "Найгарячіші станції", hottest, WeatherStation.class);
        makeSheet(book, "Найхолодніші станції", coldest, WeatherStation.class);
        makeSheet(book, "Найвологіші станції", humidity, WeatherStation.class);
        makeSheet(book, "Станції з опадами за останні 7 днів", precipitations, WeatherStation.class);
        makeSheet(book, "Станції, на яких температура зросла на 5 градусів за 5 днів", increased, WeatherStation.class);
        makeSheet(book, "Річна статистика для міста Львів", statistic, WeatherMonth.class);
        makeSheet(book, "Місяць з найбільшою швидкістю вітру", List.of(windSpeed), WeatherMonth.class);

        try (FileOutputStream fileOut = new FileOutputStream("Аналізовані дані.xlsx")) {
            book.write(fileOut);
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void makeSheet(Workbook book, String name, @NotNull List<T> data, Class<T> type)
            throws IllegalAccessException {
        Sheet sheet = book.createSheet(name);
        Field[] fields = type.getDeclaredFields();
        Row header = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            header.createCell(i).setCellValue(fields[i].getName());
        }

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Object value = fields[j].get(data.get(i));
                if (fields[j].getType().equals(String.class)) {
                    row.createCell(j).setCellValue((String) value);
                } else if (fields[j].getType().equals(double.class)) {
                    row.createCell(j).setCellValue((double) value);
                }
                fields[j].setAccessible(false);
            }
        }
    }
}
