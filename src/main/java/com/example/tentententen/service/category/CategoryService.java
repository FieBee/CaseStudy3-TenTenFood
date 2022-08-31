package com.example.tentententen.service.category;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Category;
import com.example.tentententen.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    Connection connection = ConnectionJDBC.getConnect();
    public static final String SELECT_ALL_CATEGORY = "select * from category;";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id=?";
    private static final String INSERT_CATEGORY ="INSERT INTO category (category_code, category_name, category_description) VALUE (?,?,?);";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category_id =? ;";
    private static final String UPDATE_CATEGORY = "UPDATE item SET category_code =?, category_name =?," +
            " category_description =?  WHERE category_id =?;";

    @Override
    public List<Category> findAllByItemId(int item_id) {
        return null;
    }

    @Override
    public List fillAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
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
        Category category = null;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);){
            statement.setInt(1,id);
            System.out.println(statement);
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()) {
                String category_code = resultSet.getString("category_code");
                String category_name = resultSet.getString("category_name");
                String category_description = resultSet.getString("category_description");

                category = new Category(category_code,category_name,category_description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }


    @Override
    public void insert(Category category) {
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);

            statement.setString(1,category.getCategory_code());
            statement.setString(2,category.getCategory_name());
            statement.setString(3,category.getCategory_description());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {

        boolean rowDeleted;
        try(
                PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY);){
            statement.setInt(1,id);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(int id, Category category) throws SQLException {
        boolean rowUpdated;
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);) {
            statement.setString(1, category.getCategory_code());
            statement.setString(2, category.getCategory_name());
            statement.setString(3, category.getCategory_description());
            statement.setInt(4, id);
            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }


}
