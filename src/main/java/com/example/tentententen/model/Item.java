package com.example.tentententen.model;

import java.util.List;

public class Item {
    private int item_id;
    private String item_code;
    private int shop_id;
    private int category_id;
    private int deal_id;
    private String item_name;
    private double item_price;
    private String item_description;
    private String item_image;
    private List<Category> categoryList;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(int item_id, String item_code, int shop_id, int category_id, int deal_id, String item_name, double item_price, String item_description, String item_image) {
        this.item_id = item_id;
        this.item_code = item_code;
        this.shop_id = shop_id;
        this.category_id = category_id;
        this.deal_id = deal_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.item_image = item_image;
    }

    public Item(int item_id, String item_code, int shop_id, int category_id, int deal_id, String item_name, double item_price, String item_description, String item_image, List<Category> categoryList) {
        this.item_id = item_id;
        this.item_code = item_code;
        this.shop_id = shop_id;
        this.category_id = category_id;
        this.deal_id = deal_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.item_image = item_image;
        this.categoryList = categoryList;
    }

    public Item(String item_code, int shop_id, int category_id, int deal_id, String item_name, double item_price, String item_description, String item_image) {
        this.item_code = item_code;
        this.shop_id = shop_id;
        this.category_id = category_id;
        this.deal_id = deal_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.item_image = item_image;
    }

    public Item( String item_code, int shop_id, int deal_id, String item_name, double item_price, String item_description, String item_image) {
        this.item_id = item_id;
        this.item_code = item_code;
        this.shop_id = shop_id;
        this.deal_id = deal_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.item_image = item_image;
    }

    public Item() {
    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(int deal_id) {
        this.deal_id = deal_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
