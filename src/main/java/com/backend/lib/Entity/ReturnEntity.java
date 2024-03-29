package com.backend.lib.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="return_list")
public class ReturnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnid;
    private int bid;
    private String email;
    private String bname;
    private String borrowedDate;
    private String returnDate;
    private int fine;

    public ReturnEntity() {
    }

    public ReturnEntity(int returnid, int bid, String email, String bname, String borrowedDate, String returnDate, int fine) {
        this.returnid = returnid;
        this.bid = bid;
        this.email = email;
        this.bname = bname;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public int getReturnid() {
        return returnid;
    }

    public void setReturnid(int returnid) {
        this.returnid = returnid;
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


    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
