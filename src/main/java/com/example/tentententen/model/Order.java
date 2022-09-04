package com.example.tentententen.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Customer customer;
    private List<Item> items;
    private int status;



    public Order() {
        this.status = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
