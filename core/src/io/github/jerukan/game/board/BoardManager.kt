package io.github.jerukan.game.board

import io.github.jerukan.game.GameState
import io.github.jerukan.game.Manager
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position
import io.github.jerukan.util.heightmaps.DiamondSquare
import io.github.jerukan.util.heightmaps.Perlin

import java.util.ArrayList

/** A class managing most elements of the board, namely tiles  */

class BoardManager : Manager {

    var board: Array<Array<Tile>> = Array(Constants.BOARD_WIDTH) { Array(Constants.BOARD_HEIGHT) { Tile(Position(), 0f) } }

    private var hoveredPosition: Position = Position()
    var selectedPosition: Position = Position()

    val availableBuildPositions: ArrayList<Position> = ArrayList()

    var selectType: SelectType = SelectType.SELECT

    init {
    }

    /** Sets the board position of the currently hovered tile
     * @param pos desired position to set
     */
    fun setHoveredPosition(pos: Position) {
        if (pos.x >= 0 && pos.x < Constants.BOARD_WIDTH && pos.y >= 0 && pos.y < Constants.BOARD_HEIGHT) {
            hoveredPosition.setPos(pos)
        } else {
            hoveredPosition.reset()
        }
    }

    fun getHoveredPosition(): Position {
        return hoveredPosition
    }

    /** Determines what happens on the next tile selection with the left mouse button  */
    fun selectedPositionActionLeft() {
        when (selectType) {
            BoardManager.SelectType.ACTION -> {
                val unit = GameState.instance.unitManager.selectedUnit
                val act = unit?.currentAction
                if(unit != null && act != null) {
                    if (act.requiresTarget) {
                        act.execute(unit, hoveredPosition)
                    }
                    unit.clearMoveLists()
                }
                GameState.instance.unitManager.killUnits()
                GameState.instance.unitManager.resetSelectedUnit()
                selectType = SelectType.SELECT
                selectedPosition.reset()
                GameState.instance.update()
            }
            BoardManager.SelectType.SELECT -> {
                selectedPosition.reset()
                GameState.instance.unitManager.resetSelectedUnit()
            }
        }
    }

    /** Determines what happens on the next tile selection with the right mouse button  */
    fun selectedPositionActionRight() {
        when (selectType) {
            BoardManager.SelectType.SELECT -> {
                selectedPosition = Position(hoveredPosition)
                GameState.instance.unitManager.selectedUnit = GameState.instance.unitManager.unitFromPosition(selectedPosition)
            }
        }
    }

    fun tileFromPosition(pos: Position): Tile {
        if (pos.isValid) {
            return board[pos.x][pos.y]
        }
        throw IllegalArgumentException("No tile at " + pos.toString())
    }

    fun updateAvailableBuildPositions() {
        availableBuildPositions.clear()
        val buildings = GameState.instance.unitManager.unitsFromPlayer(GameState.instance.currentPlayer) { u: Unit -> u.baseunit.type == BaseUnit.Type.BUILDING }
        for (b in buildings) {
            for (p in b.position.adjacentPositions) {
                if (!p.existsInArray(availableBuildPositions) && GameState.instance.unitManager.positionAvailable(p)) {
                    availableBuildPositions.add(p)
                }
            }
        }
    }

    fun resetBoard() {
        generateHeightsDSquare(1121956)
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                val pos = Position(x, y)
                board[x][y] = Tile(pos, DiamondSquare.heights[x][y].toFloat())
                board[x][y].setSpritePosition((Constants.TILE_SIZE * x).toFloat(), (Constants.TILE_SIZE * y).toFloat())
            }
        }
    }

    fun generateHeightsDSquare() {
        DiamondSquare.generateHeights()
    }

    fun generateHeightsDSquare(seed: Long) {
        DiamondSquare.setSeed(seed)
        DiamondSquare.generateHeights()
    }

    fun generateHeightsPerlin() {
        Perlin.randomizeVectors()
        Perlin.zeroExtrema()
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                var sum = 0.0
                for (i in 0 until Constants.PERLIN_SAMPLES) {
                    sum += Perlin.getNoise((x + Math.random()).toFloat(), (y + Math.random()).toFloat()).toDouble()
                }
                sum /= Constants.PERLIN_SAMPLES.toDouble()
                //board[x][y].setHeight((float)sum);
            }
        }
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                //board[x][y].setHeight(Util.map(board[x][y].getHeight(), Perlin.min, Perlin.max, 0, 1));
            }
        }
    }

    fun dispose() {
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                board[x][y].dispose()
            }
        }
    }

    override fun init() {
        resetBoard()
    }

    override fun update() {
        updateAvailableBuildPositions()
    }

    enum class SelectType {
        SELECT,
        ACTION
    }
}
