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
}
