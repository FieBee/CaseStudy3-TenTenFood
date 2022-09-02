package com.example.tentententen.service.item;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Category;
import com.example.tentententen.model.Item;
import com.example.tentententen.service.category.CategoryService;
import com.example.tentententen.service.category.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemService{

    Connection connection = ConnectionJDBC.getConnect();
    ICategoryService categoryService = new CategoryService();

    private static final String SELECT_ALL_ITEM = "SELECT * FROM item;";
    private static final String SELECT_ITEM_BY_ID = "SELECT * FROM item WHERE item_id=?";
    private static final String SELECT_ITEM_BY_NAME = "SELECT * FROM item WHERE item_name like ?";

    private static final String INSERT_ITEM ="INSERT INTO item (item_code, shop_id, category_id, deal_id," +
            "item_name, item_price, item_description, item_image) VALUE (?,?,?,?,?,?,?,?);";

    private static final String DELETE_ITEM = "DELETE FROM item WHERE item_id =? ;";
    private static final String UPDATE_ITEM = "UPDATE item SET item_code =?, shop_id =?, category_id =?, deal_id =?," +
            "item_name = ?, item_price =?, item_description =?, item_image =? WHERE item_id =?;";

    public static final String INSERT_NEW_ITEM_CATEGORY = "INSERT INTO item_category (item_id, category_id) VALUE (?, ?);";



    public List<Item> findAllByCategoryId(int category_id) {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement1 = connection.prepareStatement(SELECT_ITEM_BY_ID);
            statement1.setInt(1, category_id);
            ResultSet resultSet = statement1.executeQuery();
//            ResultSet resultSet1 = statement1.getGeneratedKeys();
            while (resultSet.next()){
                int item_id = resultSet.getInt("item_id");
                String item_code = resultSet.getString("item_code");
                int shop_id = resultSet.getInt("shop_id");
                int category_id1 = resultSet.getInt("category_id");
                int deal_id = resultSet.getInt("deal_id");
                String item_name = resultSet.getString("item_name");
                double item_price = resultSet.getDouble("item_price");
                String item_description = resultSet.getString("item_description");
                String item_image = resultSet.getString("item_image");
                Item item = new Item(item_id,item_code,shop_id,category_id1,deal_id,item_name,item_price,item_description,item_image);
                items.add(item);

        //                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String description = resultSet.getString("description");
//                Category category = new Category(id, name, description);
//                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }
    @Override
    public List<Item> fillAll() {
        List<Item> itemList = new ArrayList<>();
        try (
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ITEM);) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id");
                String item_code = resultSet.getString("item_code");
                int shop_id = resultSet.getInt("shop_id");
                int category_id = resultSet.getInt("category_id");
                int deal_id = resultSet.getInt("deal_id");
                String item_name = resultSet.getString("item_name");
                double item_price = resultSet.getDouble("item_price");
                String item_description = resultSet.getString("item_description");
                String item_image = resultSet.getString("item_image");
                List<Category> categoryList = categoryService.findAllByItemId(item_id);

                itemList.add(new Item(item_id, item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image,categoryList));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;

    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ITEM_BY_ID);){
            statement.setInt(1,id);
            System.out.println(statement);
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id");
                String item_code = resultSet.getString("item_code");
                int shop_id = resultSet.getInt("shop_id");
                int category_id = resultSet.getInt("category_id");
                int deal_id = resultSet.getInt("deal_id");
                String item_name = resultSet.getString("item_name");
                double item_price = resultSet.getDouble("item_price");
                String item_description = resultSet.getString("item_description");
                String item_image = resultSet.getString("item_image");

                item = new Item(item_id, item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public void insert(Item item) {
        try{
        PreparedStatement statement = connection.prepareStatement(INSERT_ITEM);
        statement.setString(1,item.getItem_code());
        statement.setInt(2,item.getShop_id());
        statement.setInt(3,item.getCategory_id());
        statement.setInt(4,item.getDeal_id());
        statement.setString(5,item.getItem_name());
        statement.setDouble(6,item.getItem_price());
        statement.setString(7,item.getItem_description());
        statement.setString(8,item.getItem_image());
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
            PreparedStatement statement = connection.prepareStatement(DELETE_ITEM);){
            statement.setInt(1,id);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(int id, Item item) {
        boolean rowUpdated;
        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM);) {
            statement.setString(1, item.getItem_code());
            statement.setInt(2, item.getShop_id());
            statement.setInt(3, item.getCategory_id());
            statement.setInt(4, item.getDeal_id());
            statement.setString(5, item.getItem_name());
            statement.setDouble(6, item.getItem_price());
            statement.setString(7, item.getItem_description());
            statement.setString(8, item.getItem_image());
            statement.setInt(9, id);
            rowUpdated = statement.executeUpdate() > 0;
            
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    @Override
    public List<Item> selectItemByName(String item_name) {
        List<Item> items = new ArrayList<>();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_NAME);){
            preparedStatement.setString(1,"%"+item_name+"%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int item_id = resultSet.getInt("item_id");
                String item_code = resultSet.getString("item_code");
                int shop_id = resultSet.getInt("shop_id");
                int category_id = resultSet.getInt("category_id");
                int deal_id = resultSet.getInt("deal_id");
                double item_price = resultSet.getDouble("item_price");
                String item_description = resultSet.getString("item_description");
                String item_image = resultSet.getString("item_image");
                items.add(new Item(item_id, item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image)) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void save(Item item, int[] categories) {
        int item_id = 0;
        try{
//            connection.setAutoCommit(false);
            PreparedStatement statement =connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,item.getItem_code());
            statement.setInt(2,item.getShop_id());
            statement.setInt(3,item.getCategory_id());
            statement.setInt(4,item.getDeal_id());
            statement.setString(5,item.getItem_name());
            statement.setDouble(6,item.getItem_price());
            statement.setString(7,item.getItem_description());
            statement.setString(8,item.getItem_image());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                item_id = resultSet.getInt(1);
            }


            PreparedStatement statement1 = connection.prepareStatement(INSERT_NEW_ITEM_CATEGORY);
            for (int id_category:categories
                 ) {
                statement1.setInt(1,item_id);
                statement1.setInt(2,id_category);
                statement1.executeUpdate();
            }
//            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
