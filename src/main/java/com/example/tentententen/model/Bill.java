package com.example.tentententen.model;

import java.time.LocalDateTime;

public class Bill {
    private int bill_id;
    private String bill_code;
    private boolean status;
    private LocalDateTime bill_date;
    private double bill_totalCost;

    private int customer_id;

    private int shop_id;

    public Bill(String bill_code, boolean status, LocalDateTime bill_date, double bill_totalCost, int customer_id, int shop_id) {
        this.bill_code = bill_code;
        this.status = status;
        this.bill_date = bill_date;
        this.bill_totalCost = bill_totalCost;
        this.customer_id = customer_id;
        this.shop_id = shop_id;
    }

    public boolean isStatus() {
        return status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public Bill(int bill_id, String bill_code, boolean status, LocalDateTime bill_date, double bill_totalCost, int customer_id, int shop_id) {
        this.bill_id = bill_id;
        this.bill_code = bill_code;
        this.status = status;
        this.bill_date = bill_date;
        this.bill_totalCost = bill_totalCost;
        this.customer_id = customer_id;
        this.shop_id = shop_id;
    }

    public Bill(int bill_id, String bill_code, boolean status, String bill_date, double bill_totalCost) {
        this.bill_id = bill_id;
        this.bill_code = bill_code;
        this.status = status;
        this.bill_date = LocalDateTime.parse(bill_date);
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBill_date() {
        return (String.valueOf(bill_date));
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
