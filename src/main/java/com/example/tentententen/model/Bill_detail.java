package com.example.tentententen.model;

public class Bill_detail {
    private int bill_detail_id;
    private int bill_id;
    private int item_id;
    private int quantity;
    private double price;

    public Bill_detail() {
    }

    public Bill_detail(int bill_detail_id, int bill_id, int item_id, int quantity, double price) {
        this.bill_detail_id = bill_detail_id;
        this.bill_id = bill_id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBill_detail_id() {
        return bill_detail_id;
    }

    public void setBill_detail_id(int bill_detail_id) {
        this.bill_detail_id = bill_detail_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
