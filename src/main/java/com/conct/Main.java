package com.conct;

import com.mysql.fabric.jdbc.FabricMySQLDriver;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Виктория on 26.09.16.
 */
public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/export_mybase";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";



    public static void main(String[] args)  {

        MyDatabaseDriver myDatabaseDriver = new MyDatabaseDriver(URL, USERNAME, PASSWORD);
         Map<Long, Float> map = myDatabaseDriver.GetAkhz1();
       //myDatabaseDriver.compared(map);




    }
        // myDatabaseDriver.getData();*/
      //  myDatabaseDriver.close();
    }




