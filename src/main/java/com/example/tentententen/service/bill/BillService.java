package com.example.tentententen.service.bill;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Bill;
import com.example.tentententen.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillService implements IBillService{

    Connection connection = ConnectionJDBC.getConnect();

    public static final String SELECT_ALL_BILL = "SELECT * FROM bill;";
    public static final String SELECT_BILL_BY_ID = "SELECT * FROM bill WHERE bill_id=?";
    public static final String INSERT_BILL ="INSERT INTO bill (bill_code, status, bill_date, bill_totalCost) VALUE (?,?,?,?);";
    public static final String DELETE_BILL = "DELETE FROM bill WHERE bill_id =? ;";
    public static final String UPDATE_BILL = "UPDATE bill SET bill_code = ?, status = ?, bill_date =?, bill_totalCost =?,customer_id=?,shop_id =? WHERE item_id =?;";
    @Override
    public List<Bill> fillAll() {
        List<Bill> billList = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BILL);) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bill_id = resultSet.getInt("bill_id");
                String bill_code = resultSet.getString("bill_code");
                boolean status = resultSet.getBoolean("status");
                String bill_date = resultSet.getString("bill_date");
                int bill_totalCost = resultSet.getInt("bill_totalCost");

                billList.add(new Bill(bill_id,bill_code,status,bill_date,bill_totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public Bill findById(int id) {
        return null;
        //        Chắc ko dùng
    }

    @Override
    public void insert(Bill bill) {
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_BILL);
            statement.setString(1, bill.getBill_code());
            statement.setBoolean(2,bill.getStatus());
            statement.setString(3,bill.getBill_date());
            statement.setDouble(4,bill.getBill_totalCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        boolean rowDeleted;
        try(
                PreparedStatement statement = connection.prepareStatement(DELETE_BILL);){
            statement.setInt(1,id);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(int id, Bill bill) throws SQLException {
        boolean rowUpdate;
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_BILL);
            statement.setString(1,bill.getBill_code());
            statement.setBoolean(2,bill.getStatus());
            statement.setString(3,bill.getBill_date());
            statement.setDouble(4,bill.getBill_totalCost());
            statement.setInt(5,bill.getCustomer_id());
            statement.setInt(6,bill.getShop_id());
            statement.setInt(7,id);
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }
}
