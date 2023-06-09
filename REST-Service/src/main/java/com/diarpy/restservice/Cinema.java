package com.diarpy.restservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int totalRows;
    private int totalColumns;
    private ArrayList<Ticket> availableSeats;

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
                Ticket ticket = new Ticket(i + 1, j + 1, ticketPrice);
                availableSeats.add(ticket);
            }
        }
    }

    public boolean isSeatWrong(Ticket ticket) {
        return ticket.getRow() <= 0 || ticket.getRow() > totalRows ||
                ticket.getColumn() <= 0 || ticket.getColumn() > totalColumns;
    }

    public boolean isSeatTaken(Ticket ticket) {
        for(Ticket t: availableSeats) {
            if (t.getRow() == ticket.getRow() && t.getColumn() == ticket.getColumn()) {
                return false;
            }
        }
        return true;
    }

    public Seat bookTicket(Ticket ticket) {
        Seat result;
        for(Ticket t: availableSeats) {
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


    public Statistics getStatistics() {
        Statistics stats = new Statistics();
        stats.setNumberOfAvailableSeats(availableSeats.size());
        stats.setNumberOfPurchasedTickets(takenSeats.size());
        int count = 0;
        for(Seat s: takenSeats) {
            count += s.getTicket().getPrice();
        }
        stats.setCurrentIncome(count);
        return stats;
    }
}
