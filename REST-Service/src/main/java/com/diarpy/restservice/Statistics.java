package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;
}
