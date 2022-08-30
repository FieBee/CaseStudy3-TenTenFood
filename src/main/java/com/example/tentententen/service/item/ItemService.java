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
    public static final String UPDATE_ITEM = "UPDATE item SET " +
            " item_code =?, shop_id=?, category_id =?, deal_id =?," +
            "item_name =?, item_price =?, item_description= ?, item_image =? WHERE item_id=?;";
    public static final String DELETE_ITEM = "DELETE FROM item WHERE item_id =? ;";



    @Override
    public List<Item> fillAll() {
        List<Item> itemList = new ArrayList<>();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEM);) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet =preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM);
//            preparedStatement.setInt(1,item.getItem_id());
            preparedStatement.setString(1,item.getItem_code());
            preparedStatement.setInt(2,item.getShop_id());
            preparedStatement.setInt(3,item.getCategory_id());
            preparedStatement.setInt(4,item.getDeal_id());
            preparedStatement.setString(5,item.getItem_name());
            preparedStatement.setDouble(6,item.getItem_price());
            preparedStatement.setString(7,item.getItem_description());
            preparedStatement.setString(8,item.getItem_image());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Item o) {

    }


    @Override
    public void save(Item p, int[] categories) {

    }
}
