package com.conct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Виктория on 29.09.16.
 */
public class MyDatabaseDriver {

    private static Connection con;
    private static Statement stmt;


    public MyDatabaseDriver(String url, String username, String password){

        try {
            con = DriverManager.getConnection(url, username, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

public

}
