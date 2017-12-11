package io.github.jerukan.game.board

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import io.github.jerukan.game.BoardCamera
import io.github.jerukan.game.GameState
import io.github.jerukan.game.Renderer
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Colors
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position
import io.github.jerukan.util.Util

import java.util.ArrayList

class BoardRenderer(private val boardManager: BoardManager, private val camera: BoardCamera) : Renderer {

    private val highlighter: ShapeRenderer

    init {
        highlighter = ShapeRenderer()
        highlighter.setAutoShapeType(true)
    }

    fun highlightPosition(position: Position, color: Color, shapeType: ShapeRenderer.ShapeType) {
        highlighter.set(shapeType)
        highlighter.color = color
        if (position.isValid) {
            highlighter.rect(boardManager.board[position.x][position.y].sprite.x, boardManager.board[position.x][position.y].sprite.y, Constants.TILE_SIZE.toFloat(), Constants.TILE_SIZE.toFloat())
        }
    }

    fun highlightPositions(positions: ArrayList<Position>, color: Color, shapeType: ShapeRenderer.ShapeType) {
        highlighter.set(shapeType)
        highlighter.color = color
        for (pos in positions) {
            if (pos.isValid) {
                highlighter.rect(boardManager.board[pos.x][pos.y].sprite.x, boardManager.board[pos.x][pos.y].sprite.y, Constants.TILE_SIZE.toFloat(), Constants.TILE_SIZE.toFloat())
            }
        }
    }

    override fun render(batch: Batch) {

        val mousex = Gdx.input.x
        val mousey = Gdx.input.y

        batch.begin()
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                boardManager.board[x][y].render(batch)
            }
        }
        batch.end()

        highlighter.projectionMatrix = camera.camera.combined
        Gdx.gl20.glEnable(GL20.GL_BLEND)
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        highlighter.begin()

        //highlight the selected position
        highlightPosition(boardManager.selectedPosition, Colors.SELECTED_TILE_COLOR, ShapeRenderer.ShapeType.Filled)

        for (u in GameState.instance.unitManager.unitlist) {
            highlightPosition(u.position, Util.colorNewAlpha(u.owner.color, 0.8f), ShapeRenderer.ShapeType.Filled)
        }

        highlightPositions(boardManager.availableBuildPositions, Colors.BUILD_POSITION_COLOR, ShapeRenderer.ShapeType.Filled)

        val mouseposboardx = camera.camera.zoom * (mousex - camera.camOriginX) + camera.camera.position.x
        val mouseposboardy = camera.camera.zoom * (Gdx.graphics.height.toFloat() - mousey.toFloat() - camera.camOriginY) + camera.camera.position.y
        val selectx = (mouseposboardx / Constants.TILE_SIZE.toFloat()).toInt()
        val selecty = (mouseposboardy / Constants.TILE_SIZE.toFloat()).toInt()

        boardManager.setHoveredPosition(Position(selectx, selecty))

        if (boardManager.getHoveredPosition().isValid) {
            highlightPosition(boardManager.getHoveredPosition(), Colors.HOVERED_TILE_COLOR, ShapeRenderer.ShapeType.Line)
            val dude = GameState.instance.unitManager.unitFromPosition(boardManager.getHoveredPosition())

            // highlighting moves
            if (dude != null && dude !== GameState.instance.unitManager.selectedUnit) {
                highlightPositions(dude.getAvailableTargets(), Colors.ATTACK_TILE_COLOR, ShapeRenderer.ShapeType.Filled)
            }
        }

        if (GameState.instance.unitManager.selectedUnit != null) {
            if (boardManager.selectType === BoardManager.SelectType.ACTION) {
                val act = GameState.instance.unitManager.selectedUnit!!.currentAction
                if (act != null && act.requiresTarget) {
                    if (act.name == "move" || act.name == "fly") {
                        highlightPositions(GameState.instance.unitManager.selectedUnit!!.getAvailableTargets(), Colors.MOVE_TILE_COLOR, ShapeRenderer.ShapeType.Filled)
                    } else if (act.name == "attack") {
                        highlightPositions(GameState.instance.unitManager.selectedUnit!!.getAvailableTargets(), Colors.ATTACK_TILE_COLOR, ShapeRenderer.ShapeType.Filled)
                    }
                }
            }
        }

        highlighter.end()
        Gdx.gl20.glDisable(GL20.GL_BLEND)
    }

    override fun init() {
        highlighter.projectionMatrix = camera.camera.combined
    }

    override fun resize(width: Int, height: Int) {
        camera.camera.setToOrtho(false, width.toFloat(), height.toFloat())
    }


    fun dispose() {
        for (x in 0 until Constants.BOARD_WIDTH) {
            for (y in 0 until Constants.BOARD_HEIGHT) {
                boardManager.board[x][y].dispose()
            }
        }
        highlighter.dispose()
    }
}
