package com.conct;

import java.util.Comparator;

/**
 * Created by Виктория on 17.10.16.
 */
public class Point {
    private long _time;
    public long getTime() { return _time; }
    private float _value;
    public float getValue() { return _value; }

    public Point(long time, float value)
    {
        _time = time;
        _value = value;
    }

    public Point(){}

    @Override
    public String toString()
    {
        return String.format("{time=%d, value=%f}", _time, _value);
    }
}

class TimeComparator implements Comparator<Point>
{
    @Override
    public int compare(Point a, Point b)
    {
        return a.getTime() < b.getTime() ? -1 : a.getTime() == b.getTime() ? 0 : 1;
    }
}
