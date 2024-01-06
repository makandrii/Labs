package org.makarov.lab9.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.makarov.lab9.models.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelWriter {
    private static final Workbook book = new XSSFWorkbook();

    public static <T> void createSheet(String name, List<T> objects, Class<T> type) throws IllegalAccessException {
        Sheet sheet = book.createSheet(name);

        Field[] fields = type.getDeclaredFields();

        Row header = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            header.createCell(i).setCellValue(fields[i].getName());
        }

        for (int i = 0; i < objects.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Object value = fields[j].get(objects.get(i));
                if (value instanceof String) {
                    row.createCell(j).setCellValue((String) value);
                } else if (value instanceof Number) {
                    row.createCell(j).setCellValue((int) value);
                } else if (value instanceof Category) {
                    row.createCell(j).setCellValue(((Category) value).getName());
                }
                fields[j].setAccessible(false);
            }
        }
    }

    public static void write(String fileName) {
        try (FileOutputStream file = new FileOutputStream(fileName + ".xlsx")) {
            book.write(file);
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
