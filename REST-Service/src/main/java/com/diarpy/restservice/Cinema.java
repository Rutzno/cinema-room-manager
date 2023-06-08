package com.diarpy.restservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author Mack_TB
 * @since 1/06/2023
 * @version 1.0.11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private int totalRows;
    private int totalColumns;
    private ArrayList<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                availableSeats.add(new Seat(i + 1, j + 1));
            }
        }
    }
}
