package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int row;
    private int column;
    private int price;
}
