package org.makarov.lab6;

import java.util.Arrays;

public class Cinema {
    public int[][][] cinema;

    public Cinema() {
        cinema = new int[5][10][20];
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        if (!isBooked(hallNumber, row, seats)) {
            System.err.println("One or more of these seats are already booked");
            return;
        }

        Arrays.stream(seats).forEach(seat -> cinema[hallNumber][row][seat] = 1);
    }

    private boolean isBooked(int hallNumber, int row, int[] seats) {
        return Arrays.stream(seats)
                .anyMatch(seat -> cinema[hallNumber][row][seat] == 1);
    }
}