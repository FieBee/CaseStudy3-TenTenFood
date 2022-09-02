package com.example.tentententen.service.category;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Category;
import com.example.tentententen.model.Item;
import com.example.tentententen.service.item.IItemService;
import com.example.tentententen.service.item.ItemService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{


    ItemService itemService = new ItemService();
//    ICategoryService categoryService = new CategoryService();
    Connection connection = ConnectionJDBC.getConnect();
    public static final String SELECT_ALL_CATEGORY = "select * from category;";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id=?";
    private static final String INSERT_CATEGORY ="INSERT INTO category (category_code, category_name, category_description) VALUE (?,?,?);";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category_id =? ;";
    private static final String UPDATE_CATEGORY = "UPDATE item SET category_code =?, category_name =?," +
            " category_description =?  WHERE category_id =?;";

    public static final String SELECT_CATEGORY_BY_ITEM_ID = "SELECT * FROM category JOIN item_category ic ON category_id = ic.category_id AND ic.book_id=?";
    @Override
    public List<Category> findAllByItemId(int item_id) {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement1 = connection.prepareStatement(SELECT_CATEGORY_BY_ITEM_ID);
            statement1.setInt(1, item_id);
            ResultSet resultSet = statement1.executeQuery();
//            ResultSet resultSet1 = statement1.getGeneratedKeys();
            while (resultSet.next()){
                int category_id = resultSet.getInt("category_id");
                String category_code = resultSet.getString("category_code");
                String category_name = resultSet.getString("category_name");
                String category_description = resultSet.getString("category_description");

                List<Item> items = itemService.findAllByCategoryId(category_id);
                Category category = new Category(category_id,category_code,category_name,category_description,items);

                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public List fillAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int category_id = resultSet.getInt("category_id");
                String category_code = resultSet.getString("category_code");
                String category_name = resultSet.getString("category_name");
                String category_description = resultSet.getString("category_description");

                List<Item> items = itemService.findAllByCategoryId(category_id);
                Category category = new Category(category_id,category_code,category_name,category_description,items);

                categories.add(category);

//                Category category = new Category(id, code, name, description);
//                categoryList.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
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
//                List<Item> itemList =

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
