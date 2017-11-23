package io.github.jerukan.util;

import java.util.ArrayList;

/** A class with nifty functions */
public class Util {

    /** A working comparator because the ArrayList one doesn't do the job properly
     * @param position the xy position being searched for within the list
     * @param list the ArrayList being searched
     * @return true if the position exists within the list */
    public static boolean arrayContainsPosition(Position position, ArrayList<Position> list) {
        for(Position pos : list) {
            if(position.equals(pos)) {
                return true;
            }
        }
        return false;
    }
}
