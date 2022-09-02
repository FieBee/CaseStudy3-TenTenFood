package com.example.tentententen.model;

public class Customer {
    private int customer_id;
    private String customer_code;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String customer_email;
    private String customer_account;
    private String customer_password;

    public Customer(int customer_id, String customer_code, String customer_name, String customer_phone, String customer_address, String customer_email, String customer_account, String customer_password) {
        this.customer_id = customer_id;
        this.customer_code = customer_code;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_email = customer_email;
        this.customer_account = customer_account;
        this.customer_password = customer_password;
    }

    public Customer(String customer_code, String customer_name, String customer_phone, String customer_address, String customer_email, String customer_account, String customer_password) {
        this.customer_code = customer_code;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_email = customer_email;
        this.customer_account = customer_account;
        this.customer_password = customer_password;
    }

    public Customer(String customer_account, String customer_password) {
        this.customer_account = customer_account;
        this.customer_password = customer_password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer_account(String customer_account) {
        this.customer_account = customer_account;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_code='" + customer_code + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_account='" + customer_account + '\'' +
                ", customer_password='" + customer_password + '\'' +
                '}';
    }
}