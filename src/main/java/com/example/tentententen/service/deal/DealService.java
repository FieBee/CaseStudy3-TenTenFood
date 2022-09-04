package com.example.tentententen.service.deal;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Deal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DealService implements IDealService{
    private static final String FIND_ALL_DEAL = "SELECT deal_id,deal_code,deal_name,deal_description,deal_image FROM DEAL ;";
    private static final String FIND_BY_ID = "SELECT * FROM DEAL WHERE deal_id=?";
    private static final String INSERT_DEAL = "INSERT INTO deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) VALUE (?,?,?,?,?,?)";
    private static final String DELETE_DEAL = "DELETE FROM deal WHERE deal_id=?";
    private static final String UPDATE_DEAL = "UPDATE DEAL SET deal_code=?,deal_name=?,deal_startDate=?,deal_endDate=?,deal_description=?,deal_image=?";
    Connection connection= ConnectionJDBC.getConnect();
    @Override
    public List<Deal> fillAll() {
        List<Deal> dealList= new ArrayList<>();
        try(PreparedStatement ptmt= connection.prepareStatement(FIND_ALL_DEAL)){
            System.out.println(ptmt);
            ResultSet rs= ptmt.executeQuery();
            while (rs.next()){
                int id= rs.getInt(1);
                String code= rs.getString(2);
                String name= rs.getString(3);
                String description= rs.getString(4);
                String image= rs.getString(5);
                dealList.add(new Deal(id,code,name,description,image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dealList;
    }

    @Override
    public Deal findById(int id) {
        Deal deal=null;
        try(PreparedStatement ptmt= connection.prepareStatement(FIND_BY_ID)){
            ptmt.setInt(1,id);
            System.out.println(ptmt);
            ResultSet rs= ptmt.executeQuery();
            while (rs.next()){
                String code= rs.getString(2);
                String name= rs.getString(3);
                String startDate= rs.getString(4);
                String endDate= rs.getString(5);
                String description= rs.getString(6);
                String image= rs.getString(7);
                deal= new Deal(id,code,name,startDate,endDate,description,image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deal;
    }

    @Override
    public void insert(Deal p) {
            Deal deal=null;
            try(PreparedStatement ptmt= connection.prepareStatement(INSERT_DEAL)){
                ptmt.setString(1,p.getDeal_code());
                ptmt.setString(2,p.getDeal_name());
                ptmt.setString(3,p.getDeal_startDate());
                ptmt.setString(4,p.getDeal_endDate());
                ptmt.setString(5,p.getDeal_description());
                ptmt.setString(6,p.getDeal_image());
                System.out.println(ptmt);
                ptmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public boolean delete(int id) {
        boolean update;
        try(PreparedStatement ptmt= connection.prepareStatement(DELETE_DEAL)){
            System.out.println(ptmt);
            ptmt.setInt(1,id);
            update= ptmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    @Override
    public boolean edit(int id, Deal t) {
        boolean update;
        try(PreparedStatement ptmt= connection.prepareStatement(UPDATE_DEAL)){
            System.out.println(ptmt);
            ptmt.setString(1,t.getDeal_code());
            ptmt.setString(2,t.getDeal_name());
            ptmt.setString(3,t.getDeal_startDate());
            ptmt.setString(4,t.getDeal_endDate());
            ptmt.setString(5,t.getDeal_description());
            ptmt.setString(6,t.getDeal_image());
            update= ptmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }
}
