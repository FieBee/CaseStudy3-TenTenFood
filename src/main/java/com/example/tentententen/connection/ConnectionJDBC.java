package com.example.tentententen.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static Connection connection;
    private ConnectionJDBC() {
    }
    public static final String URL = "jdbc:mysql://localhost:3306/databaseeee_name";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static Connection getConnect(){
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );
                System.out.println("Successfully connected!!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Connecting unsuccessful!!");
                e.printStackTrace();
            }
        }

        return  connection;
    }
}
