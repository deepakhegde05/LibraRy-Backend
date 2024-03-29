package com.backend.lib.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="borrow_list")
public class BorrowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowid;
    private int bid;
    private String email;
    private String bname;
    private int noOfDays;
    private String borrowedDate;

    public BorrowEntity() {
    }

    public BorrowEntity(int borrowid, int bid, String email, String bname, int noOfDays, String borrowedDate) {
        this.borrowid = borrowid;
        this.bid = bid;
        this.email = email;
        this.bname = bname;
        this.noOfDays = noOfDays;
        this.borrowedDate = borrowedDate;
    }

    public int getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(int borrowid) {
        this.borrowid = borrowid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
}
