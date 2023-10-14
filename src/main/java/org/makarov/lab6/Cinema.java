package org.makarov.lab6;

import java.util.Arrays;
import java.util.Optional;
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
        return IntStream.range(0, cinema[screen].length)
                .allMatch(row -> IntStream.range(0, cinema[screen][row].length - numSeats)
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
        for (int row = 0; row < cinema[hallNumber].length; row++) {
            output.append((row + 1 >= 10) ? " " : "  ").append(String.format("%d |%s", row + 1, BLACK_BG));
            for (var seat : cinema[hallNumber][row]) {
                output.append(seat == 0 ? String.format("\t%s0", BLACK_BG)
                        : String.format("%s\t1", YELLOW_BG));
            }
            output.append(String.format("\t%s| %d  \n", RESET, row + 1));
        }
        output.append(header);
        System.out.println(output);
    }

    public Optional<int[]> findBestAvailable(int hallNumber, int numSeats) {
        if (numSeats > cinema[hallNumber][0].length) {
            return Optional.empty();
        }

        Optional<Integer>[][] costs = buildArrayOfCosts(hallNumber);
        for (int row = 0; row < cinema[hallNumber].length; row++) {
            for (int seat = 0; seat < cinema[hallNumber][row].length; seat++) {
                if (cinema[hallNumber][row][seat] == 1) {
                    costs[row][seat] = Optional.empty();
                }
            }
        }

        int[] result = new int[0];
        int maxSum = 0;
        for (int row = 0; row < cinema[hallNumber].length; row++) {
            for (int firstPtr = 0; firstPtr < cinema[hallNumber][row].length - numSeats; firstPtr++) {
                int currentSum = 0;
                for (int secondPtr = 0, i = 0; secondPtr < numSeats; secondPtr++) {
                    if (costs[row][firstPtr + secondPtr].isPresent()) {
                        currentSum += costs[row][firstPtr + secondPtr].get();
                    } else {
                        firstPtr += secondPtr;
                        break;
                    }
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    result = new int[2];
                    result[0] = row;
                    result[1] = firstPtr;
                }
            }
        }

        if (result.length == 0) {
            return Optional.empty();
        } else {
            return Optional.of(result);
        }
    }

    public void autoBook(int hallNumber, int numSeats) {
        Optional<int[]> bestSeats = findBestAvailable(hallNumber, numSeats);
        if (bestSeats.isPresent()) {
            int[] seats = bestSeats.get();
            bookSeats(hallNumber, seats[0], IntStream.range(seats[1], seats[1] + numSeats).toArray());
        } else {
            System.err.println("There are no available seats in this hall.");
        }
    }

    private boolean isBooked(int hallNumber, int row, int[] seats) {
        return Arrays.stream(seats)
                .anyMatch(seat -> cinema[hallNumber][row][seat] == 1);
    }

    private Optional<Integer>[][] buildArrayOfCosts(int hallNumber) {
        Optional<Integer>[][] result = new Optional[cinema[hallNumber].length][cinema[hallNumber][0].length];
        for (int row = 0; row < Math.ceil(cinema[hallNumber].length / 2.0); row++) {
            for (int seat = 0, cost = row; seat < Math.ceil(result[row].length / 2.0); seat++, cost++) {
                result[row][seat]
                        = result[row][result[row].length - 1 - seat]
                        = result[result.length - 1 - row][seat]
                        = result[result.length - 1 - row][result[row].length - 1 - seat]
                        = Optional.of(cost);
            }
        }
        return result;
    }
}