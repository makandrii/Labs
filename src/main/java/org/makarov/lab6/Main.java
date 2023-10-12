package org.makarov.lab6;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.bookSeats(0, 4, new int[]{6, 7, 8, 9});
        cinema.printSeatingArrangement(0);
    }
}
