package lab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makarov.lab3.Cart;
import org.makarov.lab3.Order;
import org.makarov.lab3.Product;
import org.makarov.lab3.Status;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests {
    @Mock
    private Product product1;
    @Mock
    private Product product2;
    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cart = new Cart();

        cart.add(product1);
        cart.add(product2);
    }

    @Test
    void testAddProductToCart() {
        assertEquals(2, cart.getProducts().size());
    }

    @Test
    void testRemoveProductFromCart() {
        cart.remove(product1);

        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void testMakeOrder() {
        Order order = cart.order();

        assertEquals(2, order.getProducts().size());
    }

    @Test
    void testOrderShipped() {
        Order order = cart.order();

        order.shipped();

        assertEquals(Status.Shipped, order.getStatus());
    }
}
