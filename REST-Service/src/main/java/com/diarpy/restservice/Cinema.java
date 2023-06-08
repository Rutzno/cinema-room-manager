package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int totalRows;
    private int totalColumns;
    private ArrayList<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        int ticketPrice;
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                ticketPrice = i + 1 <= 4 ? 10 : 8; // determination of ticket price
                availableSeats.add(new Seat(i + 1, j + 1, ticketPrice));
            }
        }
    }

    public boolean isSeatWrong(Seat seat) {
        return seat.getRow() <= 0 || seat.getRow() > totalRows ||
                seat.getColumn() <= 0 || seat.getColumn() > totalColumns;
    }

    public boolean isSeatTaken(Seat seat) {
        for(Seat s: availableSeats) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                return false;
            }
        }
        return true;
    }

    public Seat bookTicket(Seat seat) {
        Seat result = null;
        for(Seat s: availableSeats) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                result = s;
            }
        }
        availableSeats.remove(result);
        return result;
    }
}
