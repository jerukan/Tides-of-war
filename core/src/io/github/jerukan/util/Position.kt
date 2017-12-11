package io.github.jerukan.util

import java.util.ArrayList

/** Generic class for a typical x y position  */
class Position {

    var x: Int = 0
    var y: Int = 0

    val isValid: Boolean
        get() = x >= 0 && y >= 0 && x < Constants.BOARD_WIDTH && y < Constants.BOARD_HEIGHT

    val adjacentPositions: ArrayList<Position>
        get() {
            val out = ArrayList<Position>()

            if (isValid(x + 1, y)) {
                out.add(Position(x + 1, y))
            }
            if (isValid(x - 1, y)) {
                out.add(Position(x - 1, y))
            }
            if (isValid(x, y + 1)) {
                out.add(Position(x, y + 1))
            }
            if (isValid(x, y - 1)) {
                out.add(Position(x, y - 1))
            }
            return out
        }

    val pos: IntArray
        get() = intArrayOf(x, y)

    /** Initializes a position that shouldn't be on the board  */
    constructor() {
        x = -1
        y = -1
    }

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor(pos: IntArray) {
        if (pos.size != 2) {
            throw IllegalArgumentException("A position only has two numbers")
        }
        x = pos[0]
        y = pos[1]
    }

    /** Basically clones another position
     * @param pos the other position
     */
    constructor(pos: Position) {
        x = pos.x
        y = pos.y
    }

    /** @return (x, y)
     */
    override fun toString(): String {
        return "($x, $y)"
    }

    fun equals(pos: Position): Boolean {
        return pos.x == x && pos.y == y
    }

    fun equals(pos: IntArray): Boolean {
        if (pos.size != 2) {
            throw IllegalArgumentException("A position only has two numbers")
        }
        return pos[0] == x && pos[1] == y
    }

    fun getPositionsDistance(dist: Int): ArrayList<Position> {
        val out = ArrayList<Position>()

        if (isValid(x + dist, y)) {
            out.add(Position(x + dist, y))
        }
        if (isValid(x - dist, y)) {
            out.add(Position(x - dist, y))
        }
        if (isValid(x, y + dist)) {
            out.add(Position(x, y + dist))
        }
        if (isValid(x, y - dist)) {
            out.add(Position(x, y - dist))
        }
        return out
    }

    fun getPositionsDiagonal(xdist: Int, ydist: Int): ArrayList<Position> {
        val out = ArrayList<Position>()

        if (isValid(x + xdist, y + ydist)) {
            out.add(Position(x + xdist, y + ydist))
        }
        if (isValid(x + xdist, y - ydist)) {
            out.add(Position(x + xdist, y - ydist))
        }
        if (isValid(x - xdist, y + ydist)) {
            out.add(Position(x - xdist, y + ydist))
        }
        if (isValid(x - ydist, y - ydist)) {
            out.add(Position(x - xdist, y - ydist))
        }
        return out
    }

    /** Disclaimer: no diagonals
     * @param x the x position of the other point
     * @param y the y position of the other point
     * @return distance to given point
     */
    fun distanceToPosition(x: Int, y: Int): Int {
        return Math.abs(this.x - x) + Math.abs(this.y - y)
    }

    /** @param other the other position
     * @return distance to given point
     */
    fun distanceToPosition(other: Position): Int {
        return Math.abs(x - other.x) + Math.abs(y - other.y)
    }

    fun existsInArray(positions: ArrayList<Position>): Boolean {
        for (pos in positions) {
            if (equals(pos)) {
                return true
            }
        }
        return false
    }

    fun setPos(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun setPos(pos: Position) {
        x = pos.x
        y = pos.y
    }

    fun reset() {
        setPos(-1, -1)
    }

    fun translate(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    companion object {

        fun isValid(x: Int, y: Int): Boolean {
            return x >= 0 && y >= 0 && x < Constants.BOARD_WIDTH && y < Constants.BOARD_HEIGHT
        }
    }
}
