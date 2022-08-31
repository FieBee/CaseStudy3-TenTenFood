package com.example.tentententen.service.deal;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Deal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealService implements IDealService{
    private static final String FIND_ALL_DEAL = "SELECT * FROM DEAL ";
    Connection connection= ConnectionJDBC.getConnect();
    @Override
    public List<Deal> fillAll() {
        List<Deal> dealList= new ArrayList<>();
        try(PreparedStatement ptmt= connection.prepareStatement(FIND_ALL_DEAL)){
            System.out.println(ptmt);
            ResultSet rs= ptmt.executeQuery();
            while (rs.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Deal findById(int id) {
        return null;
    }

    @Override
    public void insert(Deal p) {

    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean edit(int id, Deal t) throws SQLException {
        return false;
    }
}
