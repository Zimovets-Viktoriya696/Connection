package com.conct;

import java.sql.*;
import java.util.*;

/**
 * Created by Виктория on 29.09.16.
 */
public class MyDatabaseDriver  {

    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;
    private static final int NUMBER_OF_VALUES_IN_TABLE = 36;

    // String query = "SELECT Sample_TDate_%d, Sample_MSec_%d, Sample_Value_%d FROM %s_%d WHERE Signal_Index=1";


    public MyDatabaseDriver(String url, String username, String password){
        try {
            con = DriverManager.getConnection(url, username, password);
            // getting Statement object to execute query
        }
        catch (SQLException e) {
            e.printStackTrace();
    }
}



    private void FillTable(Map<Long, Double> map, String tableNamePrefix, int firstTablePostfix, int lastTablePostfix)
    {
        for(int currentTablePostfix = firstTablePostfix; currentTablePostfix <= lastTablePostfix; currentTablePostfix++)
        {
            for(int currentNumberOfValues = 1; currentNumberOfValues <= NUMBER_OF_VALUES_IN_TABLE; currentNumberOfValues++)
            {
                String query = String.format("SELECT Sample_TDate_%d, Sample_MSec_%d, Sample_Value_%d FROM %s_%d WHERE Signal_Index=1",
                        currentNumberOfValues, currentNumberOfValues, currentNumberOfValues, tableNamePrefix, currentTablePostfix);

                getData(query, map);
            }
        }
    }

    public  Map<Long, Double> GetAkhz1()
    {
        final int firstTablePostfix = 52;
        final int lastTablePostfix = 61;

     //   ArrayList<Point> akhz1 = new ArrayList<Point>();
         Map<Long, Double> akhz1 = new LinkedHashMap <Long,Double>();

        FillTable(akhz1, "akhz1_data", firstTablePostfix, lastTablePostfix);

       // akhz1.sort(new TimeComparator());

        return akhz1;
    }


    public Map<Long, Double> getData (String query, Map<Long, Double> map){
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


  /*  public  void compare(LinkedHashMap<Long,Double> map) {
        final Iterator<Map.Entry<Long, Double>> iterator = map.entrySet().iterator();
        Map.Entry<Long,Double> prev = null;
        while (iterator.hasNext()) {
            if (prev == null) {
                prev = iterator.next();
            } else {
                final Map.Entry<Long, Double> next = iterator.next();
                prev.equals(next);
                prev = next;
            }
        }

    }*/
    public Map<Long, Double> compared(Map<Long,Double> map) {

        Map<Long, Double> SortingMap = new TreeMap<Long, Double>(map);
        System.out.println("After Sorting:");
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            System.out.print(me2.getKey() + ": ");
            System.out.println(me2.getValue());


        }
        return SortingMap;
    }







    public void GetDown(Map<Long,Double> map)
    {
        int count=1;
        ArrayList<Long> akhz1 = new ArrayList<Long>();
        //  FillTable(akhz1, "akhz1_data", 50, 61);

        for(Map.Entry<Long, Double> pair : map.entrySet())
        {

    }
    }




    public void close (){
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }


class SortedByPrice {

    public Map<Long, Double> compared(LinkedHashMap<Long,Double> map) {

        Map<Long, Double> SortingMap = new TreeMap<Long, Double>(map);
        System.out.println("After Sorting:");
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            System.out.print(me2.getKey() + ": ");
            System.out.println(me2.getValue());


    }
        return SortingMap;
}
}