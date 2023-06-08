package com.diarpy.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CinemaController {
    private Cinema cinema;

    @GetMapping("/seats")
    public Cinema getCinema() {
        cinema = new Cinema(9, 9);
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> bookTicket(@RequestBody Seat seat) {
        String error1 = "The number of a row or a column is out of bounds!";
        String error2 = "The ticket has been already purchased!";
        if (cinema.isSeatWrong(seat)) {
            return ResponseEntity.badRequest()
                    .body(String.format("{\"error\": \"%s\"}", error1));
        } else if (cinema.isSeatTaken(seat)) {
            return ResponseEntity.badRequest()
                    .body(String.format("{\"error\": \"%s\"}", error2));
        }
        return ResponseEntity.ok(cinema.bookTicket(seat));
    }
}
