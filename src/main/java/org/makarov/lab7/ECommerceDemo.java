package org.makarov.lab7;

public class ECommerceDemo {
    public static final ECommercePlatform platform = new ECommercePlatform();

    public static void main(String[] args) {
        platform.registerUser("Andrii");
        platform.registerUser("Vlad");
        platform.registerUser("Egor");

        platform.registerProduct("Phone", 100, 1000);
        platform.registerProduct("Keyboard", 39.99, 20);
        platform.registerProduct("Monitor", 200, 500);

        // It's demo, so I skip checks
        User user = platform.getUserById(2).get();

        user.addToCart(platform.getProductById(2).get(), 20);
        user.addToCart(platform.getProductById(1).get(), 50);
        user.modifyInCart(platform.getProductById(1).get(), 50);

        platform.makeOrder(user.getId());

        user.addToCart(platform.makeRecommendations(user.getId()).get(0), 10);

        platform.makeOrder(user.getId());

        System.out.println("List of users:");
        platform.makeListOfUsers().forEach(System.out::println);

        System.out.println("List of products:");
        platform.makeListOfProducts().forEach(System.out::println);

        System.out.println("List of orders:");
        platform.makeListOfOrders().forEach(System.out::println);
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
