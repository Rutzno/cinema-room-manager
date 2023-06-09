package com.diarpy.restservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    private Cinema cinema;

    @GetMapping("/seats")
    public Cinema getCinema() {
        cinema = new Cinema(9, 9);
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> bookTicket(@RequestBody Ticket ticket) {
        String error1 = "The number of a row or a column is out of bounds!";
        String error2 = "The ticket has been already purchased!";
        if (cinema.isSeatWrong(ticket)) {
            return ResponseEntity.badRequest()
                    .body(String.format("{\"error\": \"%s\"}", error1));
        } else if (cinema.isSeatTaken(ticket)) {
            return ResponseEntity.badRequest()
                    .body(String.format("{\"error\": \"%s\"}", error2));
        }
        return ResponseEntity.ok(cinema.bookTicket(ticket));
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundTicket(@RequestBody Seat seat) {
        String error3 = "Wrong token!";
        Seat result = cinema.refundTicket(seat.getToken().toString());
        if (result != null) {
            return ResponseEntity.ok()
                    .body(Map.of("returned_ticket", result.getTicket()));
        } else {
            return ResponseEntity.badRequest()
                    .body(String.format("{\"error\": \"%s\"}", error3));
        }
    }
}
