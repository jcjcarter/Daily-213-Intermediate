package com.company;

/**
 * Created by tz6ysq on 10/6/2015.
 */
public abstract class aCoord implements iCoord{

    public int x, y;

    public aCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int distance(Coord other) {
        int dx = Math.abs(x - other.x);
        int dy = Math.abs(y - other.y);
        return dx + dy;
    }

    public String toString(){
        return"";
    }
}
