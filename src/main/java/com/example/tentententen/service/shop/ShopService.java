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
    private static final String ADD_SHOP_SQL = "insert into shop(shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SHOP_BY_ID = "select shop_id, shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password from shop where id = ?";
    private static final String SELECT_ALL_SHOP = "select * from shop";
    private static final String DELETE_SHOP_SQL = "delete from shop where id = ?";
    private static final String UPDATE_SHOP_SQL = "update shop set shop_code = ?, shop_name = ?, shop_email = ?, shop_phone = ?, shop_address = ?, shop_account = ?, shop_password = ? where id = ?";

    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public void addShop(Shop shop) throws SQLException {
        System.out.println(ADD_SHOP_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SHOP_SQL)) {
            preparedStatement.setString(1, shop.getShop_code());
            preparedStatement.setString(2, shop.getShop_name());
            preparedStatement.setString(3, shop.getShop_email());
            preparedStatement.setInt(4, shop.getShop_phone());
            preparedStatement.setString(5, shop.getShop_address());
            preparedStatement.setString(6, shop.getShop_account());
            preparedStatement.setString(7, shop.getShop_password());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shop selectShop(int id) {
        Shop shop = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHOP_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String shop_code = rs.getString("shop_code");
                String shop_name = rs.getString("shop_name");
                String shop_email = rs.getString("shop_email");
                int shop_phone = rs.getInt("shop_phone");
                String shop_address = rs.getString("shop_address");
                String shop_account = rs.getString("shop_account");
                String shop_password = rs.getString("shop_password");
                shop = new Shop(id, shop_code, shop_name, shop_email, shop_email, shop_phone, shop_address, shop_account, shop_password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public List<Shop> selectAllShops() {
        List<Shop> shops = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHOP)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String shop_code = rs.getString("shop_code");
                String shop_name = rs.getString("shop_name");
                String shop_email = rs.getString("shop_email");
                int shop_phone = rs.getInt("shop_phone");
                String shop_address = rs.getString("shop_address");
                String shop_account = rs.getString("shop_account");
                String shop_password = rs.getString("shop_password");
                shops.add(new Shop(id, shop_code, shop_name, shop_email, shop_phone, shop_address, shop_account, shop_password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }

    @Override
    public boolean deleteShop(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateShop(Shop shop) throws SQLException {
        return false;
    }
}
