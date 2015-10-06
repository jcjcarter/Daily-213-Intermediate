package com.company;

/**
 * Created by tz6ysq on 10/6/2015.
 */
public interface iKeyboard {

    Coord getClosestCoord(Hand hand, char ch);

    int getEffort(Hand hand, char ch);
}
