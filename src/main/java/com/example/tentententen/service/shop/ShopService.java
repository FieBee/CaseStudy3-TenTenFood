package com.example.tentententen.service.shop;


import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopService implements IShopService {
    private static final String ADD_SHOP_SQL = "insert into shop(shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password,shop_image) values (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SELECT_SHOP_BY_ID = "select * from shop where shop_id = ?";
    private static final String SELECT_ALL_SHOP = "select * from shop where status = 1";
    private static final String DELETE_SHOP_SQL = "UPDATE shop SET status = 0 where shop_id =?";
    private static final String UPDATE_SHOP_SQL = "update shop set shop_code = ?, shop_name = ?, shop_email = ?, shop_phone = ?, shop_address = ?, shop_account = ?, shop_password = ? , shop_image=? where shop_id = ?";
    private static final String SELECT_SHOP_BY_NAME = "update shop set shop_code = ?, shop_name = ?, shop_email = ?, shop_phone = ?, shop_address = ?, shop_account = ?, shop_password = ? , shop_image=? where shop_name = ?";

    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public List<Shop> fillAll() {
        List<Shop> shops = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHOP)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("shop_id");
                String shop_code = rs.getString("shop_code");
                String shop_name = rs.getString("shop_name");
                String shop_email = rs.getString("shop_email");
                int shop_phone = rs.getInt("shop_phone");
                String shop_address = rs.getString("shop_address");
                String shop_account = rs.getString("shop_account");
                String shop_password = rs.getString("shop_password");
                String shop_image= rs.getString("shop_image");
                shops.add(new Shop(id, shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password, shop_image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }

    @Override
    public Shop findById(int id) {
        Shop shop = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHOP_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
//                int shop_id = rs.getInt("shop_id");
                String shop_code = rs.getString("shop_code");
                String shop_name = rs.getString("shop_name");
                String shop_email = rs.getString("shop_email");
                int shop_phone = rs.getInt("shop_phone");
                String shop_address = rs.getString("shop_address");
                String shop_account = rs.getString("shop_account");
                String shop_password = rs.getString("shop_password");
                String shop_image= rs.getString("shop_image");
                shop = new Shop(id, shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password,shop_image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public void insert(Shop shop) {
        System.out.println(ADD_SHOP_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SHOP_SQL)) {
            preparedStatement.setString(1, shop.getShop_code());
            preparedStatement.setString(2, shop.getShop_name());
            preparedStatement.setString(3, shop.getShop_email());
            preparedStatement.setInt(4, shop.getShop_phone());
            preparedStatement.setString(5, shop.getShop_address());
            preparedStatement.setString(6, shop.getShop_account());
            preparedStatement.setString(7, shop.getShop_password());
            preparedStatement.setString(8, shop.getShop_image());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SHOP_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(int id, Shop shop) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SHOP_SQL)) {
//            statement.setInt(1, shop.getShop_id());
            statement.setString(1, shop.getShop_code());
            statement.setString(2, shop.getShop_name());
            statement.setString(3, shop.getShop_email());
            statement.setInt(4, shop.getShop_phone());
            statement.setString(5, shop.getShop_address());
            statement.setString(6, shop.getShop_account());
            statement.setString(7, shop.getShop_password());
            statement.setString(8, shop.getShop_image());
            statement.setInt(9,id);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public Shop selectShopByName(String name) {
        Shop shop = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHOP_BY_NAME)) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int shop_id = rs.getInt("shop_id");
                String shop_code = rs.getString("shop_code");
                String shop_name = rs.getString("shop_name");
                String shop_email = rs.getString("shop_email");
                int shop_phone = rs.getInt("shop_phone");
                String shop_address = rs.getString("shop_address");
                String shop_account = rs.getString("shop_account");
                String shop_password = rs.getString("shop_password");
                String shop_image= rs.getString("shop_image");
                shop = new Shop(shop_id, shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password, shop_image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }


}
