package com.conct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            stmt = con.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getData (){
        List<String> list = new ArrayList<String>();
            try {
            rs = stmt.executeQuery(query);
                while (rs.next()) {
                String id = rs.getString("Sample_TDate_1");
                //  int name = rs.getInt("Sample_MSec_1");
                //  double author = rs.getDouble("Sample_Value_1");
                System.out.println(id);
                list.add(id);
                    }
                }
            catch (SQLException sqlEx){
        sqlEx.printStackTrace();
        }
        return list;
    }

    public void close (){
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
