package cinema;

/**
 * @author Mack_TB
 * @since 4/05/2023
 * @version 1.0.2
 */

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ticketPrice, fhTicketPrice, lhTicketPrice, totalIncome;
        System.out.print("Enter the number of rows:\n> ");
        int rows = sc.nextInt(); // number of rows
        System.out.print("Enter the number of seats in each row:\n> ");
        int seatsInRow = sc.nextInt(); // number of seats in each row
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

        System.out.printf("Total income: %n$%d", totalIncome);
    }

    private static void firstStage() {
        System.out.println("Cinema:");
        System.out.println("""
                  1 2 3 4 5 6 7 8
                1 S S S S S S S S
                2 S S S S S S S S
                3 S S S S S S S S
                4 S S S S S S S S
                5 S S S S S S S S
                6 S S S S S S S S
                7 S S S S S S S S""");
    }
}