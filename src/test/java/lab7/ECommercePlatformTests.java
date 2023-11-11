package lab7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makarov.lab7.ECommercePlatform;
import org.makarov.lab7.Product;
import org.makarov.lab7.User;
import org.makarov.lab7.exceptions.ProductNotFoundException;
import org.makarov.lab7.exceptions.UserNotFoundException;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ECommercePlatformTests {
    ECommercePlatform platform;
    static User[] users;
    static Product[] products;

    @BeforeEach
    void init() {
        platform = new ECommercePlatform();

        users = new User[]{
                platform.registerUser("User 1"),
                platform.registerUser("User 2"),
                platform.registerUser("User 3")
        };
        products = new Product[]{
                platform.registerProduct("Product 1", 0.50, 10),
                platform.registerProduct("Product 2", 19.99, 50),
                platform.registerProduct("Product 3", 50, 100)
        };
    }

    @Test
    void getUserById_ShouldReturnOptionalOfUser() {
        assertTrue(platform.getUserById(1).isPresent());
    }

    @Test
    void getUserById_ShouldReturnOptionalEmpty() {
        assertTrue(platform.getUserById(9999).isEmpty());
    }

    @Test
    void getProductById_ShouldReturnOptionalOfUser() {
        assertTrue(platform.getUserById(1).isPresent());
    }

    @Test
    void getProductById_ShouldReturnOptionalEmpty() {
        assertTrue(platform.getUserById(9999).isEmpty());
    }

    @Test
    void makeOrder_ShouldBeRegistered() {
        User user = platform.getUserById(1).get();

        user.addToCart(platform.getProductById(1).get(), 5);

        assertEquals(0, platform.makeListOfOrders().size());

        platform.makeOrder(user.getId());

        assertEquals(1, platform.makeListOfOrders().size());
    }

    @Test
    void makeOrder_ShouldThrowUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> platform.makeOrder(9999));
    }

    @Test
    void updateStock_ShouldChangeProduct() {
        assertEquals(10, platform.makeListOfProducts().get(0).getStock());

        platform.updateStock(1, 15);

        assertEquals(15, platform.makeListOfProducts().get(0).getStock());
    }

    @Test
    void updateStock_ShouldThrowProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> platform.updateStock(9999, 0));
    }

    @Test
    void makeRecommendations_ShouldReturnListOfMostPopularProducts() {
        User user = platform.getUserById(2).get();
        user.addToCart(platform.getProductById(1).get(), 1);
        user.addToCart(platform.getProductById(2).get(), 5);
        user.addToCart(platform.getProductById(3).get(), 3);

        platform.makeOrder(user.getId());

        List<Product> expected = Stream.of(products[1], products[2], products[0]).toList();

        assertEquals(expected, platform.makeRecommendations(user.getId()));
    }

    @Test
    void makeRecommendations_ShouldThrowUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> platform.makeRecommendations(9999));
    }
}
