package io.github.jerukan.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import io.github.jerukan.game.board.BoardRenderer
import io.github.jerukan.game.gameunits.UnitRenderer
import io.github.jerukan.game.ui.UIRenderer
import io.github.jerukan.game.ui.gamescreen.GameScreen

/** Handles all the renderers and graphics related objects  */

object WorldRenderer {

    var batch = SpriteBatch()

    var uiCam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    var boardCam = BoardCamera(OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))

    var uiViewport = ScreenViewport(uiCam)
    var uiStage = Stage(uiViewport)

    var inputs = InputMultiplexer()

    var boardRenderer = BoardRenderer(GameState.instance.boardManager, boardCam)
    var uiRenderer = UIRenderer(uiCam, uiStage)
    var unitRenderer = UnitRenderer(GameState.instance.unitManager)

    fun init() {
        inputs.addProcessor(uiRenderer.stage)
        inputs.addProcessor(Input())
        Gdx.input.inputProcessor = inputs
        boardRenderer.init()
        uiRenderer.currentScreen = GameScreen(uiStage)
        uiRenderer.init()
    }

    fun resize(width: Int, height: Int) {
        boardRenderer.resize(width, height)
        uiRenderer.resize(width, height)
        uiViewport.update(width, height, false)
    }

    fun render() {
        boardCam.update()

        batch.projectionMatrix = boardCam.camera.combined
        uiRenderer.updateVisibility()

        boardRenderer.render(batch)
        unitRenderer.render(batch)
        uiRenderer.render(batch)
    }

    fun dispose() {
        batch.dispose()
        boardRenderer.dispose()
        uiRenderer.dispose()
    }
}
