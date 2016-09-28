package com.conct;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Виктория on 26.09.16.
 */
public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/export_mybase";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";



    public static void main(String[] args) {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);}
        catch (Exception e) {
            System.out.println("Не удалось загрузить драйвер");}

        try {
            Connection connection  = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            java.sql.Statement statement =  connection.createStatement();
            statement.getConnection();
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

