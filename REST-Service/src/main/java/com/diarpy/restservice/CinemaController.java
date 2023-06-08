package com.diarpy.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.11
 */

@RestController
public class CinemaController {

    @GetMapping("/seats")
    public Cinema getCinema() {
        return new Cinema(9, 9);
    }

}
