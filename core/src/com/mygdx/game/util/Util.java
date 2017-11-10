package com.mygdx.game.util;

import java.util.ArrayList;

public class Util {

    public static boolean positionInArray(Position position, ArrayList<Position> list) {
        for(Position pos : list) {
            if(position.equals(pos)) {
                return true;
            }
        }
        return false;
    }
}
