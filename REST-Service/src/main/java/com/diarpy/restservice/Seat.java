package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private UUID token;
    private Ticket ticket;
}
