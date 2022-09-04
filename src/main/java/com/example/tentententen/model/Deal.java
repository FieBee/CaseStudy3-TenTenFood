package com.example.tentententen.model;

import java.time.LocalDateTime;

public class Deal {
    private int deal_id;
    private String deal_code;
    private String deal_name;
    private LocalDateTime deal_startDate;
    private LocalDateTime deal_endDate;
    private String deal_description;
    private String deal_image;

    public Deal() {
    }

    public Deal(int deal_id, String deal_code, String deal_name, String deal_description, String deal_image) {
        this.deal_id = deal_id;
        this.deal_code = deal_code;
        this.deal_name = deal_name;
        this.deal_description = deal_description;
        this.deal_image = deal_image;
    }

    public Deal(int deal_id, String deal_code, String deal_name, String deal_startDate, String deal_endDate, String deal_description, String deal_image) {
        this.deal_id = deal_id;
        this.deal_code = deal_code;
        this.deal_name = deal_name;
        this.deal_startDate = LocalDateTime.parse(deal_startDate);
        this.deal_endDate = LocalDateTime.parse(deal_endDate);
        this.deal_description = deal_description;
        this.deal_image = deal_image;
    }

    public Deal(String deal_code, String deal_name, String deal_startDate, String deal_endDate, String deal_description, String deal_image) {
        this.deal_code = deal_code;
        this.deal_name = deal_name;
        this.deal_startDate = LocalDateTime.parse(deal_startDate);
        this.deal_endDate = LocalDateTime.parse(deal_endDate);
        this.deal_description = deal_description;
        this.deal_image = deal_image;
    }

    public int getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(int deal_id) {
        this.deal_id = deal_id;
    }

    public String getDeal_code() {
        return deal_code;
    }

    public void setDeal_code(String deal_code) {
        this.deal_code = deal_code;
    }

    public String getDeal_name() {
        return deal_name;
    }

    public void setDeal_name(String deal_name) {
        this.deal_name = deal_name;
    }

    public String getDeal_startDate() {
        return String.valueOf(deal_startDate);
    }

    public void setDeal_startDate(LocalDateTime deal_startDate) {
        this.deal_startDate = deal_startDate;
    }

    public String getDeal_endDate() {
        return String.valueOf(deal_endDate);
    }

    public void setDeal_endDate(LocalDateTime deal_endDate) {
        this.deal_endDate = deal_endDate;
    }

    public String getDeal_description() {
        return deal_description;
    }

    public void setDeal_description(String deal_description) {
        this.deal_description = deal_description;
    }

    public String getDeal_image() {
        return deal_image;
    }

    public void setDeal_image(String deal_image) {
        this.deal_image = deal_image;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "deal_id=" + deal_id +
                ", deal_code='" + deal_code + '\'' +
                ", deal_name='" + deal_name + '\'' +
                ", deal_startDate=" + deal_startDate +
                ", deal_endDate=" + deal_endDate +
                ", deal_description='" + deal_description + '\'' +
                ", deal_image='" + deal_image + '\'' +
                '}';
    }
}
