package com.diarpy.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    @GetMapping("/seats")
    public Cinema getCinema() {
        return new Cinema(9, 9);
    }

}
