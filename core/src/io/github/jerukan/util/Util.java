package io.github.jerukan.util;

import com.badlogic.gdx.graphics.Color;

/** A class with nifty functions */
public class Util {

    /** Changes the alpha value of a color and returns it
     * @param color the desired color to change the alpha of
     * @param alpha a float 0-1
     * @return the new color with the modified alpha */
    public static Color colorNewAlpha(Color color, float alpha) {
        Color out = new Color(color);
        out.set(out.r, out.g, out.b, alpha);
        return out;
    }

    /** Maps a value in the range of x1-x2 to a value of x3-x4
     * @param val
     * @param x1
     * @param x2
     * @param x3
     * @param x4
     * @return
     */
    public static float map(float val, float x1, float x2, float x3, float x4) {
        return ((val - x1) / (x2-x1)) * (x4-x3) + x3;
    }
}
