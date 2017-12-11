package io.github.jerukan.util

import com.badlogic.gdx.graphics.Color

import java.util.ArrayList

/** A class with nifty functions  */
object Util {

    /** Changes the alpha value of a color and returns it
     * @param color the desired color to change the alpha of
     * @param alpha a float 0-1
     * @return the new color with the modified alpha
     */
    fun colorNewAlpha(color: Color, alpha: Float): Color {
        val out = Color(color)
        out.set(out.r, out.g, out.b, alpha)
        return out
    }

    /** Maps a value n in the range of x1-x2 to a value of x3-x4
     * @param n
     * @param x1
     * @param x2
     * @param x3
     * @param x4
     * @return
     */
    fun map(n: Float, x1: Float, x2: Float, x3: Float, x4: Float): Float {
        return (n - x1) / (x2 - x1) * (x4 - x3) + x3
    }

    fun averageList(num: ArrayList<Double>): Double {
        var out = 0.0
        for (n in num) {
            out += n
        }
        return out / num.size
    }
}
