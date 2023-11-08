# Лабораторна робота №7

## Функціональність програми

**Реалізована спрощена система бек-енду для платформи електронної комерції, використовуючи Java Collections,
зосереджуючись на управлінні запасами, управлінні кошиком користувача та обробці замовлень.**

## Опис роботи

1. Створити класи [Product], [User] і [Order]
2. Додати функціональність для взаємодії з кошиком та для розрахунку вартості замовлення
3. Створити клас [ECommercePlatform]
4. Додати функціональність:
   - Додавання користувачів та товарів у систему
   - Створення замовлення
   - Отримання переліку користувачів, товарів та замовлень
   - Отримання переліку доступних товарів
   - Оновлення запасів товарів
5. Створити клас [ECommerceDemo], у якому продемонструвати функціональність класу [ECommercePlatform]
6. Реалізувати `Comparable` у класі [Product] для сортування за ціною
7. Створити класи [ProductNameComparator] та [ProductStockComparator] для сортування за ім'ям та запасами відповідно
8. Додати у клас [ECommerceDemo] функцію, яка демонструє сортування товарів
9. Додати у клас [ECommercePlatform] функцію, яка створює список рекомендованих товарів на основі історії покупок
10. Додати винятки [ProductNotFoundException] та [UserNotFoundException] для обробки відповідних ситуацій
11. Покрити реалізацію [тестами]

## Висновок

**Під час виконання лабораторної роботи покращив навички використання Java Collection.**

[Product]: Product.java
[User]: User.java
[Order]: Order.java
[ECommercePlatform]: ECommercePlatform.java
[ECommerceDemo]: ECommerceDemo.java
[ProductNameComparator]: ProductNameComparator.java
[ProductStockComparator]: ProductStockComparator.java
[ProductNotFoundException]: exceptions/ProductNotFoundException.java
[UserNotFoundException]: exceptions/UserNotFoundException.java
[тестами]: ../../../../../test/java/lab7/ECommercePlatformTests.java