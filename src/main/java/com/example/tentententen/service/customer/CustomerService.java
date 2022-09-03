package com.example.tentententen.service.customer;

import com.example.tentententen.connection.ConnectionJDBC;
import com.example.tentententen.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    Connection connection= ConnectionJDBC.getConnect();
    private static final String SELECT_ALL_CUSTOMER="SELECT * FROM CUSTOMER";
    private static final String FIND_CUSTOMER_BY_ID="SELECT * FROM customer WHERE customer_id=?";
    private static final String INSERT_CUSTOMER="INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values(?,?,?,?,?,?,?);";
    private static final String DELETE_CUSTOMER="DELETE FROM customer where customer_id=?";
    private static final String EDIT_CUSTOMER="UPDATE CUSTOMER SET CUSTOMER_CODE=?,CUSTOMER_NAME=?,CUSTOMER_PHONE=?,CUSTOMER_ADDRESS=?,CUSTOMER_EMAIL=?,CUSTOMER_ACCOUNT=?,CUSTOMER_PASSWORD=? WHERE customer_id=?;";

    private static final String QUERY_FIND_BY_CUSTOMER = "SELECT ID FROM USERS WHERE customer_account = ? AND customer_password = ?";
    @Override
    public List<Customer> fillAll() {
        List<Customer> customerList= new ArrayList<>() ;
        try( PreparedStatement ptmt= connection.prepareStatement(SELECT_ALL_CUSTOMER)){
            ResultSet rs=ptmt.executeQuery();
            while (rs.next()){
                int id=rs.getInt("customer_id");
                String code= rs.getString("customer_code");
                String name= rs.getString("customer_name");
                String phone= rs.getString("customer_phone");
                String address= rs.getString("customer_address");
                String email=rs.getString("customer_email");
                String account= rs.getString("customer_account");
                String password= rs.getString("customer_password");
                customerList.add(new Customer(id,code,name,phone,address,email,account,password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        Customer customer=null;
        try( PreparedStatement ptmt= connection.prepareStatement(FIND_CUSTOMER_BY_ID)){
            ptmt.setInt(1,id);
            System.out.println(ptmt);
            ResultSet rs= ptmt.executeQuery();
            while (rs.next()){
                String code= rs.getString(2);
                String name= rs.getString(3);
                String phone= rs.getString(4);
                String address= rs.getString(5);
                String email= rs.getString(6);
                String account= rs.getString(7);
                String password= rs.getString(8);
                customer= new Customer(id,code,name,phone,address,email,account,password);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public void insert(Customer p) {
        try(PreparedStatement ptmt= connection.prepareStatement(INSERT_CUSTOMER)){
            ptmt.setString(1, p.getCustomer_code());
            ptmt.setString(2,p.getCustomer_name());
            ptmt.setString(3,p.getCustomer_phone());
            ptmt.setString(4,p.getCustomer_address());
            ptmt.setString(5,p.getCustomer_email());
            ptmt.setString(6,p.getCustomer_account());
            ptmt.setString(7,p.getCustomer_password());
            System.out.println(ptmt);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        boolean stt;
        try(PreparedStatement ptmt= connection.prepareStatement(DELETE_CUSTOMER)){
            ptmt.setInt(1,id);
            stt=ptmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stt;
    }

    @Override
    public boolean edit(int id, Customer t) {
            boolean stt;
        try(PreparedStatement ptmt= connection.prepareStatement(EDIT_CUSTOMER)) {
            ptmt.setString(1,t.getCustomer_code());
            ptmt.setString(2,t.getCustomer_name());
            ptmt.setString(3,t.getCustomer_phone());
            ptmt.setString(4,t.getCustomer_address());
            ptmt.setString(5,t.getCustomer_email());
            ptmt.setString(6,t.getCustomer_account());
            ptmt.setString(7,t.getCustomer_password());
            ptmt.setInt(8,id);
            stt=ptmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stt;
    }
    public int findByUser(Customer customer) {
        int id = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BY_CUSTOMER);
            statement.setString(1, customer.getCustomer_account());
            statement.setString(2, customer.getCustomer_password());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

}