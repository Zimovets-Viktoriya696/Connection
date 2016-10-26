package com.conct;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Виктория on 29.09.16.
 */
public class MyDatabaseDriver {

    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;
    String query = "select Sample_TDate_1  from akhz1_data_50";


    public MyDatabaseDriver(String url, String username, String password){
        try {
            con = DriverManager.getConnection(url, username, password);
            // getting Statement object to execute query
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Long, Double> getData (){
        Map<Long, Double> map = new HashMap<Long, Double>();
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    long dateInMs = rs.getDate("Sample_TDate_1").getTime();
                    long timeInMs = rs.getTime("Sample_TDate_1").getTime();
                    long ms = rs.getInt("Sample_MSec_1");

                    long time = dateInMs + timeInMs + ms;
                    double value = rs.getDouble("Sample_Value_1");

                System.out.println();
                map.put(time, value);
                    }
                }
            catch (SQLException sqlEx){
        sqlEx.printStackTrace();
        }
        return map;
    }




    public void close (){
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
