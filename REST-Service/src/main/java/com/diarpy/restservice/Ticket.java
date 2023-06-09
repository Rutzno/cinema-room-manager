package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private int row;
    private int column;
    private int price;
}
