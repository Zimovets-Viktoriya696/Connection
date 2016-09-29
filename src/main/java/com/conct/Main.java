package com.conct;

import com.mysql.fabric.jdbc.FabricMySQLDriver;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Виктория on 26.09.16.
 */
public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/export_mybase";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {
        String query = "select Sample_TDate_1  from akhz1_data_50";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                String id = rs.getString("Sample_TDate_1");
              //  int name = rs.getInt("Sample_MSec_1");
              //  double author = rs.getDouble("Sample_Value_1");
                System.out.println(id);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}

