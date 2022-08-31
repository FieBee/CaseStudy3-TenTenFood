package com.example.tentententen.service.bill_detail;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Bill_detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bill_DetailService implements IBill_DetailService{
    private static final String ADD_BILL_DETAIL_SQL = "insert into bill_detail(bill_id, item_id, quantity, price) values (?, ?, ?, ?)";
    private static final String SELECT_BILL_DETAIL_BY_ID = "select bill_id, item_id, quantity, price from bill_detail where bill_detail_id = ?";
    private static final String SELECT_ALL_BILL_DETAIL = "select * from bill_detail";
    private static final String DELETE_BILL_DETAIL_SQL = "delete from bill_detail where bill_detail_id = ?";
    private static final String UPDATE_BILL_DETAIL_SQL = "update bill_detail set bill_id = ?, item_id = ?, quantity = ?, price = ? where bill_detail_id = ?";

    Connection connection = ConnectionJDBC.getConnect();

    public List<Bill_detail> fillAll() {
        List<Bill_detail> bill_details = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BILL_DETAIL)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int bill_detail_id = rs.getInt("id");
                int bill_id = rs.getInt("bill_id");
                int item_id = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                bill_details.add(new Bill_detail(bill_detail_id, bill_id, item_id, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill_details;
    }

    @Override
    public Bill_detail findById(int id) {
        Bill_detail bill_detail = null;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_BILL_DETAIL_BY_ID)) {
            statement.setInt(1,id);
            System.out.println(statement);
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()) {
                int bill_detail_id = resultSet.getInt("bill_detail_id");
                int bill_id = resultSet.getInt("bill_id");
                int item_id = resultSet.getInt("item_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                bill_detail = new Bill_detail(bill_detail_id, bill_id, item_id, quantity, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bill_detail;
    }


    @Override
    public void insert(Bill_detail bill_detail) {
        System.out.println(ADD_BILL_DETAIL_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_BILL_DETAIL_SQL)) {
            preparedStatement.setInt(1, bill_detail.getBill_detail_id());
            preparedStatement.setInt(2, bill_detail.getBill_id());
            preparedStatement.setInt(3, bill_detail.getItem_id());
            preparedStatement.setInt(4, bill_detail.getQuantity());
            preparedStatement.setDouble(5, bill_detail.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BILL_DETAIL_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(int id, Bill_detail bill_detail) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BILL_DETAIL_SQL)) {
            statement.setInt(1, bill_detail.getBill_detail_id());
            statement.setInt(2, bill_detail.getBill_id());
            statement.setInt(3, bill_detail.getItem_id());
            statement.setInt(4, bill_detail.getQuantity());
            statement.setDouble(5, bill_detail.getPrice());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
