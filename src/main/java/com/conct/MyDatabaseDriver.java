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
         Map<Long, Double> akhz1 = new HashMap<Long, Double>();

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



    public ArrayList<Long> GetDown(Map<Long,Double> map)
    {
        int count=1;
        ArrayList<Point> akhz1 = new ArrayList<Point>();
        //  FillTable(akhz1, "akhz1_data", 50, 61);

        for(Map.Entry<Long, Double> pair : map.entrySet())
        {
            double value = pair.getValue();
            System.out.println();
        }

        ArrayList<Long> down = new ArrayList<Long>();

        for (int i = 0; i < akhz1.size()-2; i++) {
            double temp=akhz1.get(i).getValue();
            double  delta_old=  akhz1.get(i+count).getValue()-akhz1.get(i).getValue();
            double delta_new = akhz1.get(i+count+count).getValue()-akhz1.get(i+count).getValue();
            if(
                    ((temp>50.96 && (delta_new < 0 && delta_old<0))))

                down.add(akhz1.get(i).getTime());

            else if ((temp<-522.8 &&(delta_old <0  )))

                down.add(akhz1.get(i).getTime());}


        return  down;
    }




    public void close (){
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
