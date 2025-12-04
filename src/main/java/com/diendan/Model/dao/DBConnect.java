package com.diendan.Model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/cnw_bt_nhom";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException{

        try{
        Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("KO TIM THAY DRIVE");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
