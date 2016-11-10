package com.conct;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Виктория on 29.09.16.
 */
 class MyDatabaseDriver {

    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;

    private static final int DATE_COLUMN  = 1;
    private static final int MSEC_COLUMN  = 2;
    private static final int VALUE_COLUMN = 3;
    private static final int NUMBER_OF_VALUES_IN_TABLE = 36;

    public MyDatabaseDriver(String url, String username, String password){
        try {
            con = DriverManager.getConnection(url, username, password);
            // getting Statement object to execute query
        }
        catch (SQLException e) {
            e.printStackTrace();
    }
}



    private void FillTable(Map<Long, Float> toList, String tableNamePrefix, int firstTablePostfix, int lastTablePostfix)
    {
        for(int currentTablePostfix = firstTablePostfix; currentTablePostfix <= lastTablePostfix; currentTablePostfix++)
        {
            for(int currentNumberOfValues = 1; currentNumberOfValues <= NUMBER_OF_VALUES_IN_TABLE; currentNumberOfValues++)
            {
                String query = String.format("SELECT Sample_TDate_%d, Sample_MSec_%d, Sample_Value_%d FROM %s_%d WHERE Signal_Index=1",
                        currentNumberOfValues, currentNumberOfValues, currentNumberOfValues, tableNamePrefix, currentTablePostfix);

                getData(query, toList);
            }
        }
    }

    public  Map<Long, Float> GetAkhz1()
    {
        final int firstTablePostfix = 52;
        final int lastTablePostfix = 61;

        Map<Long, Float> akhz1 = new HashMap <Long,Float>();
        FillTable( akhz1, "akhz1_data", firstTablePostfix, lastTablePostfix);


        Map<Long, Float> akhz2 =  longIntegerMapSorter(akhz1);
        for(Map.Entry<Long, Float> pair : akhz2.entrySet())
        {
            System.out.println(pair.getKey() + " ==> " + pair.getValue()); }
        return akhz2;
    }


    public static Map<Long, Float> longIntegerMapSorter(Map<Long, Float> inputMap){
        Comparator<Long> longComparator = new LongComparator();
        Map<Long, Float> sortedMap = new TreeMap<>(longComparator);
        sortedMap.putAll(inputMap);
        return sortedMap;
    }

    public void getData (String query, Map<Long, Float> map)
    {
         try
         {
              stmt = con.createStatement();
              rs = stmt.executeQuery(query);

              while (rs.next())
              {
                   try
                   {
                        long dateInMs = rs.getDate(DATE_COLUMN).getTime();
                        long timeInMs = rs.getTime(DATE_COLUMN).getTime();
                        long ms = rs.getInt(MSEC_COLUMN);

                        long time = dateInMs + timeInMs + ms;
                        float value = rs.getFloat(VALUE_COLUMN);
                        map.put(time, value);
                   }
                   catch (NullPointerException exs){exs.printStackTrace();}
                   }
              }


            catch (SQLException sqlEx){ sqlEx.printStackTrace();}
            finally
            {
                try { con.close();  } catch(SQLException se) { }
                try { stmt.close(); } catch(SQLException se) { }
                try { rs.close();   } catch(SQLException se) { }
            }
    }

    public Map<Long, Double> compared(Map<Long,Double> map) {

        TreeMap<Long, Double> sorted = new TreeMap<>(map);
            Set<Map.Entry<Long, Double>> mappings = sorted.entrySet();
                System.out.println("HashMap after sorting by keys in ascending order ");
        for(Map.Entry<Long, Double> mapping : mappings){
            System.out.println(mapping.getKey() + " ==> " + mapping.getValue()); }
        return sorted;

    }

    public void close (){
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

 class LongComparator implements Comparator<Long> {

    @Override
    public int compare(Long o1, Long o2) {
        if (o1 > o2){
            return 1;
        }
        if (o2 > o1){
            return  -1;
        }
        return 0;
    }
}