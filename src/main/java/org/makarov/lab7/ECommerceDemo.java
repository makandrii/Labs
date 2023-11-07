package org.makarov.lab7;

public class ECommerceDemo {
    public static final ECommercePlatform platform = new ECommercePlatform();

    public static void main(String[] args) {
        platform.addUser(
                new User("Andrii"),
                new User("Vlad"),
                new User("Egor"));

        platform.addProduct(
                new Product("Phone", 600, 1000),
                new Product("Keyboard", 39.99, 20),
                new Product("Monitor", 200, 500));

        // It's demo, so I skip check
        User user = platform.getUserById(2).get();

        user.addToCart(platform.getProductById(1).get(), 50);
        user.addToCart(platform.getProductById(2).get(), 20);

        platform.makeOrder(user.getId());

        System.out.println("List of users:");
        platform.makeListOfUsers().forEach(System.out::println);

        System.out.println("List of available products:");
        platform.makeListOfAvailableProducts().forEach(System.out::println);

        System.out.println("List of orders:");
        platform.makeListOfOrders().forEach(System.out::println);
    }
}
