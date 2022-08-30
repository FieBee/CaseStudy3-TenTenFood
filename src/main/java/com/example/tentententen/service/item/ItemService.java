package com.example.tentententen.service.item;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemService{

    Connection connection = ConnectionJDBC.getConnect();

    public static final String SELECT_ALL_ITEM = "SELECT * FROM item;";
    public static final String SELECT_ITEM_BY_ID = "SELECT * FROM item " +
            "JOIN item_category ON category.id = item.category_id and item.item_id=?";
    public static final String INSERT_ITEM ="INSERT INTO item (item_code, shop_id, category_id, deal_id," +
            "item_name, item_price, item_description, item_image) VALUE (?,?,?,?,?,?,?,?);";
    public static final String DELETE_ITEM = "DELETE FROM item WHERE item_id =? ;";
    public static final String UPDATE_ITEM = "UPDATE item SET item_code =?, shop_id =?, category_id =?, deal_id =?," +
            "item_name = ?, item_price =?, item_description =?, item_image =? WHERE item_id =?;";



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

                itemList.add(new Item(item_id, item_code, shop_id, category_id,deal_id,item_name,item_price,item_description,item_image));
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
            statement.setInt(5, item.getDeal_id());
            statement.setString(6, item.getItem_name());
            statement.setDouble(7, item.getItem_price());
            statement.setString(8, item.getItem_description());
            statement.setString(9, item.getItem_image());
            rowUpdated = statement.executeUpdate() > 0;
            
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;

    }


    @Override
    public void save(Item p, int[] categories) {
    }
}
