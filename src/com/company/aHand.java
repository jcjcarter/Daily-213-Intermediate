package com.company;

/**
 * Created by tz6ysq on 10/6/2015.
 */
public abstract class aHand implements iHand {

    public Coord coord;
    private String name;

    public aHand(int startingX, int startingY, String name) {
        this.coord = new Coord(startingX,startingY);
        this.name = name;
    }

    @Override
    public void moveTo(int newX, int newY) {
        coord.x = newX;
        coord.y = newY;
    }

    @Override
    public void moveTo(Coord newCoord) {

        this.coord = newCoord;
    }

    public String toString() {
        return name + " hand";
    }
}
