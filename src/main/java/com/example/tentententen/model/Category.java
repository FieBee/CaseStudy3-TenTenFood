package com.example.tentententen.model;

import java.util.List;

public class Category {
    private int category_id;
    private String category_code;
    private String category_name;
    private String category_description;

    private List<Item> itemList;

    public Category() {
    }

    public Category(int category_id, String category_code, String category_name, String category_description, List<Item> itemList) {
        this.category_id = category_id;
        this.category_code = category_code;
        this.category_name = category_name;
        this.category_description = category_description;
        this.itemList = itemList;
    }

    public Category(int category_id, String category_code, String category_name, String category_description) {
        this.category_id = category_id;
        this.category_code = category_code;
        this.category_name = category_name;
        this.category_description = category_description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }
}
