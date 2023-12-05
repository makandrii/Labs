# Лабораторна робота №8

## Функціональність програми

**Програма звертається до API, отримує прогнози погоди, аналізує їх та зберігає проаналізовані дані в Excel таблиці.**

## Опис роботи

1. Створити моделі для збереження даних
    - [WeatherData]
    - [City]
    - [WeatherDay]
    - [WeatherStation]
2. Створити клієнтський клас, який буде звертатися до API
    - [WeatherAPIClient]
3. Створити клас, що буде аналізувати дані
    - [WeatherAnalyzer]
4. Додати модель, що буде зберігати середні значення за місяць
    - [WeatherMonth]
5. Додати сервісний клас, що вираховує середні значення за кожен місяць у році
    - [WeatherStatistic]
6. Додати пакет table, який зберігає реалізацію створення таблиць та їх виведення:
   - [Cell]
   - [StringCell]
   - [DoubleCell]
   - [Row]
   - [Column]
   - [Table]
7. Створити демонстраційний клас, який продемонструє функціональність програми та виведе усі дані у формі таблиць
    - [WeatherDemo]

## Висновок

**Під час лабораторної роботи я навчився робити http запити, обробляти json відповідь та записувати дані в Excel таблицю**

[WeatherData]: models/WeatherData.java
[City]: models/City.java
[WeatherDay]: models/WeatherDay.java
[WeatherStation]: models/WeatherStation.java
[WeatherAPIClient]: controllers/WeatherAPIClient.java
[WeatherAnalyzer]: services/WeatherAnalyzer.java
[WeatherMonth]: models/WeatherMonth.java
[WeatherStatistic]: services/WeatherStatistic.java
[Cell]: table/Cell.java
[StringCell]: table/StringCell.java
[DoubleCell]: table/DoubleCell.java
[Row]: table/Row.java
[Column]: table/Column.java
[Table]: table/Table.java
[WeatherDemo]: WeatherDemo.java