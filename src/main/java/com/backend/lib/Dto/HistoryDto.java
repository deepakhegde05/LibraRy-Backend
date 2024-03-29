package com.backend.lib.Dto;

import lombok.Data;

@Data
public class HistoryDto {
    private int bid;
    private String email;
    private String bname;
    private int noOfDays;
    private String borrowedDate;
    private String returnDate;
    private int fine;
}
