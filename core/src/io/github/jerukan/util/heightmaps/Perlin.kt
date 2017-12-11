package io.github.jerukan.util.heightmaps

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Util

/** NOT FUNCTIONAL BECAUSE I HAVE NO IDEA WHAT IS HAPPENING  */

object Perlin {

    var vectors = Array<Array<Vector2>>(Constants.BOARD_WIDTH) { Array(Constants.BOARD_HEIGHT) {Vector2()} }
    var max = 0f
    var min = 0f

    fun zeroExtrema() {
        max = 0f
        min = 0f
    }

    fun randomizeVectors() {
        for (x in vectors.indices) {
            for (y in 0 until vectors[0].size) {
                vectors[x][y] = Vector2()
                vectors[x][y].set((2 * Math.random() - 1).toFloat(), (2 * Math.random() - 1).toFloat())
            }
        }
    }

    fun dotGradient(x1: Int, y1: Int, x2: Float, y2: Float): Float {
        var x1 = x1
        var y1 = y1
        val dx = x2 - x1
        val dy = y2 - y1
        x1 = MathUtils.clamp(x1, 0, Constants.BOARD_WIDTH - 1)
        y1 = MathUtils.clamp(y1, 0, Constants.BOARD_HEIGHT - 1)

        return vectors[x1][y1].x * dx + vectors[x1][y1].y * dy
    }

    fun getNoise(x: Float, y: Float): Float {
        //i hav eno idea what i am doing
        //WHAT THE FUCK AM I DOINGNGG
        val intx = Math.floor(x.toDouble()).toInt()
        val inty = Math.floor(x.toDouble()).toInt()
        val x1 = intx + 1
        val y1 = inty + 1

        val weightx = x - intx
        val weighty = y - inty
        val n0 = dotGradient(intx, inty, x, y)
        val n1 = dotGradient(x1, inty, x, y)
        val ix1 = MathUtils.lerp(n0, n1, weightx)

        val n2 = dotGradient(intx, inty, x, y)
        val n3 = dotGradient(x1, y1, x, y)
        val ix2 = MathUtils.lerp(n2, n3, weightx)

        val out = MathUtils.lerp(ix1, ix2, weighty)
        if (out > max) {
            max = out
        } else if (out < min) {
            min = out
        }
        return out
    }
}
