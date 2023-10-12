package org.makarov.lab6;

import java.util.Arrays;
import java.util.stream.IntStream;

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

    public void cancelBooking(int hallNumber, int row, int[] seats) {
        Arrays.stream(seats).forEach(seat -> cinema[hallNumber][row][seat] = 0);
    }

    public boolean checkAvailability(int screen, int numSeats) {
        return IntStream.range(0, 10)
                .allMatch(row -> IntStream.range(0, 20 - numSeats)
                        .noneMatch(firstPtr -> IntStream.range(0, numSeats)
                                .anyMatch(secondPtr -> cinema[screen][row][firstPtr + secondPtr] == 1)
                        )
                );
    }

    private boolean isBooked(int hallNumber, int row, int[] seats) {
        return Arrays.stream(seats)
                .anyMatch(seat -> cinema[hallNumber][row][seat] == 1);
    }
}