package org.makarov.lab6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Cinema {
    public int[][][] cinema;

    public Cinema() {
        cinema = new int[5][10][20];
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        if (isBooked(hallNumber, row, seats)) {
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

    public void printSeatingArrangement(int hallNumber) {
        final String RESET = "\u001B[0m";
        final String BLACK_BG = "\u001B[40m\u001B[32m";
        final String YELLOW_BG = "\u001B[43m\u001B[31m";

        String header = "\t\t1\t2\t3\t4\t5\t6\t7\t8\t9  10  11  12  13  14  15  16  17  18  19  20\t\t\n";
        StringBuilder output = new StringBuilder(header);
        for (int row = 0; row < 10; row++) {
            output.append((row + 1 >= 10) ? " " : "  ").append(String.format("%d |%s", row + 1, BLACK_BG));
            for (var seat : cinema[hallNumber][row]) {
                output.append(seat == 0? String.format("\t%s0", BLACK_BG)
                        : String.format("%s\t1", YELLOW_BG));
            }
            output.append(String.format("\t%s| %d  \n", RESET, row + 1));
        }
        output.append(header);
        System.out.println(output);
    }

    private boolean isBooked(int hallNumber, int row, int[] seats) {
        return Arrays.stream(seats)
                .anyMatch(seat -> cinema[hallNumber][row][seat] == 1);
    }
}