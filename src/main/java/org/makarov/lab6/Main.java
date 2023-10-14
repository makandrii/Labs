package org.makarov.lab6;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(3, 10, 20);
        cinema.autoBook(0, 4);
        cinema.printSeatingArrangement(0);
    }
}
