package com.example.tentententen.service.order;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Item;
import com.example.tentententen.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderService implements IOrderService{

    Connection connection = ConnectionJDBC.getConnect();

    private static final String INSERT_ORDER ="INSERT INTO item (item_code, shop_id, category_id, deal_id," +
            "item_name, item_price, item_description, item_image) VALUE (?,?,?,?,?,?,?,?);";

//    private static final String DELETE_ORDER = "DELETE FROM item WHERE item_id =? ;";
    @Override
    public List fillAll() {
        return null;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public void insert(Order order) {
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER);
//          order infor


            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean edit(int id, Order t) throws SQLException {
        return false;
    }


}
