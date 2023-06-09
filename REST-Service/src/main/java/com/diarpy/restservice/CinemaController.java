package com.diarpy.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.44
 */

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
        return result != null ?
                ResponseEntity.ok().body(Map.of("returned_ticket", result.getTicket())) :
                ResponseEntity.badRequest().body(Map.of("error", error3));
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> statistics(@RequestParam(required = false) String password) {
        String error4 = "The password is wrong!";
        if (password != null && password.equals("super_secret")) {
            return ResponseEntity.ok(cinema.getStatistics());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", error4));
        }
    }
}
