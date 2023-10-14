package lab6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makarov.lab6.Cinema;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaTests {
    private Cinema cinema;

    @BeforeEach
    public void init() {
        cinema = new Cinema(1, 1, 5);
    }

    @Test
    public void testBookSeatsTrue() {
        assertTrue(cinema.bookSeats(0, 0, new int[]{0, 1, 2, 3, 4}));
    }

    @Test
    public void testBookSeatsFalse() {
        assertFalse(cinema.bookSeats(1, 1, new int[]{5}));
        cinema.bookSeats(0, 0, new int[]{0});
        assertFalse(cinema.bookSeats(0, 0, new int[]{0}));
    }

    @Test
    public void testCancelBookingTrue() {
        assertTrue(cinema.cancelBooking(0, 0, new int[]{0}));
    }

    @Test
    public void testCancelBookingFalse() {
        assertFalse(cinema.cancelBooking(1, 1, new int[]{5}));
    }

    @Test
    public void testCheckAvailabilityTrue() {
        assertTrue(cinema.checkAvailability(0, 5));
    }

    @Test
    public void testCheckAvailabilityFalse() {
        assertFalse(cinema.checkAvailability(1, 6));
        cinema.bookSeats(0, 0, new int[]{0});
        assertFalse(cinema.checkAvailability(0, 5));
    }

    @Test
    public void testFindBestAvailableTrue() {
        assertArrayEquals(new int[]{0, 2}, cinema.findBestAvailable(0, 1).get());
        assertArrayEquals(new int[]{0, 1}, cinema.findBestAvailable(0, 3).get());
    }

    @Test
    public void testFindBestAvailableFalse() {
        assertTrue(cinema.findBestAvailable(1, 6).isEmpty());
        cinema.bookSeats(0, 0, new int[]{2});
        assertTrue(cinema.findBestAvailable(0, 3).isEmpty());
    }

    @Test
    public void testAutoBookTrue() {
        assertTrue(cinema.autoBook(0, 3));
    }

    @Test
    public void testAutoBookFalse() {
        assertFalse(cinema.autoBook(1, 6));
        cinema.bookSeats(0, 0, new int[]{2});
        assertFalse(cinema.autoBook(0, 3));
    }
}
