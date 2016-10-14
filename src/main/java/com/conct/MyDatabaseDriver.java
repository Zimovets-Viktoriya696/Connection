package com.conct;

import java.sql.*;

/**
 * Created by Виктория on 29.09.16.
 */
public class MyDatabaseDriver {

    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;


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

    public void getMethod (){
        String query = "select Sample_TDate_1  from akhz1_data_50";
            try {
            rs = stmt.executeQuery(query);
                while (rs.next()) {
                String id = rs.getString("Sample_TDate_1");
                //  int name = rs.getInt("Sample_MSec_1");
                //  double author = rs.getDouble("Sample_Value_1");
                System.out.println(id);
                    }
                }
            catch (SQLException sqlEx){
        sqlEx.printStackTrace();
        }
    }
}
