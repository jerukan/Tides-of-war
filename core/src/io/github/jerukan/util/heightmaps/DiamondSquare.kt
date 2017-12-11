package io.github.jerukan.util.heightmaps

import com.badlogic.gdx.math.MathUtils
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position
import io.github.jerukan.util.Util

import java.util.ArrayList
import java.util.Random

object DiamondSquare {

    private var diamondsteps = 0
    private var squaresteps = 0

    var heights = Array(Constants.BOARD_WIDTH) { DoubleArray(Constants.BOARD_HEIGHT) }
    private val OFFSET_FACTOR_DEFAULT = 0.5
    private val OFFSET_MULTIPLIER = 0.7
    private var offsetFactor = OFFSET_FACTOR_DEFAULT

    private val DEFAULT_SEED: Long = 1
    private val random = Random(DEFAULT_SEED)

    fun setSeed(seed: Long) {
        random.setSeed(seed)
    }

    private fun initCorners() {
        heights[0][0] = random.nextDouble()
        heights[Constants.BOARD_WIDTH - 1][0] = random.nextDouble()
        heights[0][Constants.BOARD_HEIGHT - 1] = random.nextDouble()
        heights[Constants.BOARD_WIDTH - 1][Constants.BOARD_HEIGHT - 1] = random.nextDouble()
    }

    private fun reset() {
        offsetFactor = OFFSET_FACTOR_DEFAULT
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                heights[x][y] = 0.0
            }
        }
        initCorners()
    }

    //apparently this is actually a diamond step but whatever
    private fun squareStep(xpos: Int, ypos: Int, radius: Int): Double {
        val points = Position(xpos, ypos).getPositionsDistance(radius)
        val selectHeights = ArrayList<Double>()
        for (p in points) {
            if (heights[p.x][p.y] == 0.0) {
                return 0.0 //if any of the heights are 0 return no height
            }
            selectHeights.add(heights[p.x][p.y])
        }
        val offset = (2 * random.nextDouble() - 1) * offsetFactor
        squaresteps++
        return Util.averageList(selectHeights) + offset
    }

    //this is supposed to be the square step (?)
    private fun diamondStep(xpos: Int, ypos: Int, radius: Int): Double {
        val points = Position(xpos, ypos).getPositionsDiagonal(radius, radius)
        val selectHeights = ArrayList<Double>()
        for (p in points) {
            if (heights[p.x][p.y] == 0.0) {
                return 0.0 //if any of the heights are 0 return no height
            }
            selectHeights.add(heights[p.x][p.y])
        }
        val offset = (2 * random.nextDouble() - 1) * offsetFactor
        diamondsteps++
        return Util.averageList(selectHeights) + offset
    }

    fun generateHeights() {
        reset()
        for (radius in (Constants.BOARD_WIDTH - 1) / 2 downTo 1) {
            diamondsteps = 0
            squaresteps = 0
            for (x in 0 until Constants.BOARD_WIDTH) {
                for (y in 0 until Constants.BOARD_HEIGHT) {
                    if (heights[x][y] == 0.0) {
                        heights[x][y] = MathUtils.clamp(diamondStep(x, y, radius), 0.0, 1.0)
                    }
                }
            }
            for (x in 0 until Constants.BOARD_WIDTH) {
                for (y in 0 until Constants.BOARD_HEIGHT) {
                    if (heights[x][y] == 0.0) {
                        heights[x][y] = MathUtils.clamp(squareStep(x, y, radius), 0.0, 1.0)
                    }
                }
            }
            //            System.out.println("diamond steps: " + diamondsteps);
            //            System.out.println("square steps: " + squaresteps);
            offsetFactor *= OFFSET_MULTIPLIER
        }
    }
}
