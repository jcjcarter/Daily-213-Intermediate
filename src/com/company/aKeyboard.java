package com.company;

import java.util.HashMap;

/**
 * Created by tz6ysq on 10/6/2015.
 */
public class aKeyboard implements iKeyboard {

    public HashMap<Character, Coord> kb = new HashMap<Character, Coord>();
    public aKeyboard(String[] keyRows) {

        for (int i = 0; i < keyRows.length; i++) {
            for (int j = 0; j < keyRows[i].length(); j++) {
                kb.put(keyRows[i].charAt(j), new Coord(j,i));
            }
        }

    }

    @Override
    public Coord getClosestCoord(Hand hand, char ch) {

        int dx = 0;
        ch = Character.toLowerCase(ch);
        Coord charCoord = kb.get(ch);
        if (ch == ' '){
            ch = '#';
            int[] distances = new int[4];
            charCoord = kb.get(ch);

            for (int i = 0; i < distances.length; i++) {
                Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);

                distances[i] = hand.coord.distance(tempCoord);

                if (i != 0){
                    dx = distances[i] < distances[i - 1] ? i : dx;
                }
            }
        }else {
            int[] distances = new int[10];
            for (int i = 0; i < distances.length; i+= 9) {
                Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);
                distances[i] = hand.coord.distance(tempCoord);
                if (i != 0){
                    dx = distances[i] < distances[i - 9] ? i : dx;
                }
            }
        }
        return new Coord(charCoord.x - dx, charCoord.y);
    }

    @Override
    public int getEffort(Hand hand, char ch) {

        ch = Character.toLowerCase(ch);
        ch = ch == ' ' ? '#' : ch;

        Coord charCoord = kb.get(ch);
        int distance = Integer.MAX_VALUE;

        if (ch == '#'){
            for (int i = 0; i < 4; i++) {
                Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);
                distance = Math.min(distance, hand.coord.distance(tempCoord));
            }
        }else if (ch == '^'){
            distance = Math.min(hand.coord.distance(charCoord), hand.coord
            .distance(new Coord(charCoord.x - 9, charCoord.y)));
        }else{
            distance = hand.coord.distance(charCoord);
        }
        return distance;

    }
}
