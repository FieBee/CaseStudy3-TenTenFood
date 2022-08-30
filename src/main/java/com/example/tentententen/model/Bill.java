package com.example.tentententen.model;

import java.time.LocalDateTime;

public class Bill {
    private int bill_id;
    private String bill_code;
    private int status;
    private LocalDateTime bill_date;
    private double bill_totalCost;

//    private int customer_id;
//    private int shop_id;


    public Bill(int bill_id, String bill_code, int status, LocalDateTime bill_date, double bill_totalCost) {
        this.bill_id = bill_id;
        this.bill_code = bill_code;
        this.status = status;
        this.bill_date = bill_date;
        this.bill_totalCost = bill_totalCost;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getBill_date() {
        return bill_date;
    }

    public void setBill_date(LocalDateTime bill_date) {
        this.bill_date = bill_date;
    }

    public double getBill_totalCost() {
        return bill_totalCost;
    }

    public void setBill_totalCost(double bill_totalCost) {
        this.bill_totalCost = bill_totalCost;
    }
}
