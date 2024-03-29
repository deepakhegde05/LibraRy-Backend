package com.backend.lib.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user_history")
@Data
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyid;
    private int bid;
    private String bname;
    private int noOfDays;
    private String borrowedDate;
    private String returnDate;
    private int fine;
    @ManyToOne
    private User user;
}
