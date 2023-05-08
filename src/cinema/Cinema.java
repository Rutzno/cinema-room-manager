package cinema;

/**
 * @author Mack_TB
 * @since 4/05/2023
 * @version 1.0.2
 */

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static char[][] room;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows:\n> ");
        int rows = sc.nextInt(); // number of rows
        System.out.print("Enter the number of seats in each row:\n> ");
        int seatsInRow = sc.nextInt(); // number of seats in each row

        initRoom(rows, seatsInRow);
        displayRoom();

        System.out.print("\nEnter a row number:\n> ");
        int x = sc.nextInt();
        System.out.print("Enter a seat number in that row:\n> ");
        int y = sc.nextInt();
        room[x-1][y-1] = 'B';
        System.out.printf("%nTicket price: $%d%n", getTicketPrice(x));
        displayRoom();
    }

    private static int getTicketPrice(int x) {
        int result;
        int totalSeats = room.length * room[0].length;
        if (totalSeats < 60) {
            result = 10;
        } else {
            if (x <= room.length/2) {
                result = 10;
            } else {
                result = 8;
            }
        }
        return result;
    }

    private static int getTotalIncome() {
        int ticketPrice, fhTicketPrice, lhTicketPrice, totalIncome;
        int rows = room.length, seatsInRow = room[0].length;
        int totalSeats = rows * seatsInRow;

        if (totalSeats < 60) {
            ticketPrice = 10;
            totalIncome = totalSeats * ticketPrice;
        } else {
            fhTicketPrice = 10;
            lhTicketPrice = 8;
            if (rows % 2 == 0) {
                totalIncome = (fhTicketPrice + lhTicketPrice) * seatsInRow * (rows/2);
            } else { // the number of rows is odd
                totalIncome = (fhTicketPrice*(rows/2) + lhTicketPrice*(1+rows/2)) * seatsInRow;
            }
        }
        return totalIncome;
    }

    private static void initRoom(int rows, int seatsInRow) {
        room = new char[rows][seatsInRow];
        for (int i = 0; i < room.length; i++) {
            Arrays.fill(room[i], 'S');
        }
    }

    private static void displayRoom() {
        System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 0; i < room[0].length; i++) {
            System.out.print(" " + (i+1));
        }
        System.out.println();
        for (int i = 0; i < room.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }
}