package com.backend.lib.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="request_list")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reqid;
    private int bid;

    private String email;
    private String bname;
    private int numberOfDays;

    public RequestEntity() {
    }

    public RequestEntity(int bid, String email, String bname, int numberOfDays) {
        this.bid = bid;
        this.email = email;
        this.bname = bname;
        this.numberOfDays = numberOfDays;
    }

    public int getReqid() {
        return reqid;
    }

    public void setReqid(int reqid) {
        this.reqid = reqid;
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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
