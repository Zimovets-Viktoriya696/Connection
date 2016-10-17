package com.conct;

import java.util.ArrayList;

/**
 * Created by Виктория on 15.10.16.
 */
public class Flow extends Point{

    private ArrayList<Point> _points;

    public Flow(){
    }

    public Flow(ArrayList<Point> _points){
        _points = new ArrayList<Point>();
    }

    public void  AddPoint (Point p){
        _points.add(p);
    }



}
