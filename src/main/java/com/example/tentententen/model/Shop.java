package com.example.tentententen.model;

import sun.security.util.Password;

public class Shop {
    private int shop_id;
    private String shop_code;
    private String shop_name;
    private String shop_email;
    private int shop_phone;
    private String shop_address;
    private String shop_account;
    private String shop_password;

    public Shop() {
    }

    public Shop(int shop_id, String shop_code, String shop_name, String shop_email, int shop_phone, String shop_address, String shop_account, String shop_password) {
        this.shop_id = shop_id;
        this.shop_code = shop_code;
        this.shop_name = shop_name;
        this.shop_email = shop_email;
        this.shop_phone = shop_phone;
        this.shop_address = shop_address;
        this.shop_account = shop_account;
        this.shop_password = shop_password;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_email() {
        return shop_email;
    }

    public void setShop_email(String shop_email) {
        this.shop_email = shop_email;
    }

    public int getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(int shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_account() {
        return shop_account;
    }

    public void setShop_account(String shop_account) {
        this.shop_account = shop_account;
    }

    public String getShop_password() {
        return shop_password;
    }

    public void setShop_password(String shop_password) {
        this.shop_password = shop_password;
    }
}
