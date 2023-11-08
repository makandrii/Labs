package org.makarov.lab7;

import java.util.List;

public class ECommerceDemo {
    public static final ECommercePlatform platform = new ECommercePlatform();

    public static void main(String[] args) {
        platform.addUser(
                new User("Andrii"),
                new User("Vlad"),
                new User("Egor"));

        platform.addProduct(
                new Product("Phone", 100, 1000),
                new Product("Keyboard", 39.99, 20),
                new Product("Monitor", 200, 500));

        // It's demo, so I skip check
        User user = platform.getUserById(2).get();

        user.addToCart(platform.getProductById(1).get(), 50);
        user.addToCart(platform.getProductById(2).get(), 20);

        platform.makeOrder(user.getId());

        printSortedProducts();
    }

    public static void printSortedProducts() {
        System.out.println("\t*** Sorted Products ***");
        System.out.println("\t1) By name:");
        platform.makeListOfProducts()
                .stream()
                .sorted(new ProductNameComparator())
                .forEach(System.out::println);

        System.out.println("\t2) By price:");
        platform.makeListOfProducts()
                .stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("\t3) By stock:");
        platform.makeListOfProducts()
                .stream()
                .sorted(new ProductStockComparator())
                .forEach(System.out::println);
    }
}
