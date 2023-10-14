package org.makarov.lab6;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(5, 10, 20);
        cinema.bookSeats(0, 3, new int[]{5, 6, 7, 8});
        cinema.printSeatingArrangement(0);
    }
}
