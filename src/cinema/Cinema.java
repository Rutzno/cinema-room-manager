package cinema;

/**
 * @author Mack_TB
 * @since 4/05/2023
 * @version 1.0.5
 */


import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static char[][] room;
    static int numOfBoughtTickets = 0;
    static int currentIncome = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initRoom();
        int option;
        while (true) {
            menu();
            System.out.print("> ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> displayRoom();
                case 2 -> buyaTicket();
                case 3 -> statistics();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown command.");
            }
        }
    }

    private static void buyaTicket() {
        int x, y;
        while (true) {
            System.out.print("\nEnter a row number:\n> ");
            x = sc.nextInt();
            System.out.print("Enter a seat number in that row:\n> ");
            y = sc.nextInt();
            if (x <= 0 || x > room.length || y <= 0 || y > room.length) {
                System.out.println("\nWrong input!");
            } else if (isSeatTaken(x, y)){
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                break;
            }
        }
        room[x - 1][y-1] = 'B';
        System.out.printf("Ticket price: $%d%n", getTicketPrice(x));
        numOfBoughtTickets++;
        currentIncome += getTicketPrice(x);
    }

    private static boolean isSeatTaken(int x, int y) {
        return room[x - 1][y - 1] == 'B';
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

    private static void initRoom() {
        System.out.print("Enter the number of rows:\n> ");
        int rows = sc.nextInt(); // number of rows
        System.out.print("Enter the number of seats in each row:\n> ");
        int seatsInRow = sc.nextInt(); // number of seats in each row
        room = new char[rows][seatsInRow];
        for (char[] chars : room) {
            Arrays.fill(chars, 'S');
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

    private static void statistics() {
        int totalSeats = room.length * room[0].length;
        float p = 100.0f * numOfBoughtTickets / totalSeats;
        System.out.printf("%nNumber of purchased tickets: %d%n", numOfBoughtTickets);
        System.out.printf("Percentage: %.2f%%%n", p);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", getTotalIncome());
    }

    private static void menu() {
        System.out.println("""
               \n1. Show the seats
               2. Buy a ticket
               3. Statistics
               0. Exit""");
    }
}