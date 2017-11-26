package io.github.jerukan.util;

import com.badlogic.gdx.graphics.Color;

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

    /** Changes the alpha value of a color and returns it
     * @param color the desired color to change the alpha of
     * @param alpha a float 0-1
     * @return the new color with the modified alpha */
    public static Color colorNewAlpha(Color color, float alpha) {
        Color out = new Color(color);
        out.set(out.r, out.g, out.b, alpha);
        return out;
    }
}
