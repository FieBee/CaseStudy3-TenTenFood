package com.example.tentententen.service.category;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    Connection connection = ConnectionJDBC.getConnect();
    public static final String SELECT_ALL_ITEM = "select * from category;";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT category_id, category_code, category_name,category_description from category" +
            " JOIN item_category  ON category.id = item.category_id and item.item_id=?";



    @Override
    public List<Category> findAllByItemId(int item_id) {
        return null;
    }

    @Override
    public List fillAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ITEM);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("category_id");
                String code = resultSet.getString("category_code");
                String name = resultSet.getString("category_name");
                String description = resultSet.getString("category_description");
                Category category = new Category(id, code, name, description);
                categoryList.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category findById(int id) {
        return null;
    }


    @Override
    public void insert(Category p) {

    }

    @Override
    public boolean delete(int id) {

        return false;
    }

    @Override
    public boolean edit(int id, Category t) throws SQLException {
        return false;
    }


}
