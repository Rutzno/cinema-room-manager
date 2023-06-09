package com.diarpy.restservice;;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int totalRows;
    private int totalColumns;
    private ArrayList<Seat> availableSeats;

    @JsonIgnore
    private ArrayList<Seat> takenSeats;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        takenSeats = new ArrayList<>();
        int ticketPrice;
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                ticketPrice = i + 1 <= 4 ? 10 : 8; // determination of ticket price
                Seat ticket = new Seat(i + 1, j + 1, ticketPrice);
                availableSeats.add(ticket);
            }
        }
    }

    public boolean isSeatWrong(Seat ticket) {
        return ticket.getRow() <= 0 || ticket.getRow() > totalRows ||
                ticket.getColumn() <= 0 || ticket.getColumn() > totalColumns;
    }

    public boolean isSeatTaken(Seat ticket) {
        for(Seat t: availableSeats) {
            if (t.getRow() == ticket.getRow() && t.getColumn() == ticket.getColumn()) {
                return false;
            }
        }
        return true;
    }

    public Seat bookTicket(Seat ticket) {
        Seat result;
        for(Seat t: availableSeats) {
            if (t.getRow() == ticket.getRow() && t.getColumn() == ticket.getColumn()) {
                availableSeats.remove(t);
                result = new Seat(UUID.randomUUID(), t);
                takenSeats.add(result);
                return result;
            }
        }
        return null;
    }

    public Seat refundTicket(String token) {
        Seat result = null;
        for (Seat s: takenSeats) {
            if (s.getToken().toString().equals(token)) {
                availableSeats.add(s.getTicket());
                result = s;
                break;
            }
        }
        if (result != null) {
            takenSeats.remove(result);
        }
        return result;
    }
}
