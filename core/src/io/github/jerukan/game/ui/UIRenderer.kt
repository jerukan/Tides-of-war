package io.github.jerukan.game.ui

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import io.github.jerukan.game.Renderer

/** Class that manages different game screens
 * Also handles displaying them for now  */

class UIRenderer(private val camera: OrthographicCamera, val stage: Stage) : Renderer {

    var currentScreen: Screen? = null

    fun clear() {
        currentScreen!!.clearWindows()
    }

    override fun init() {
        currentScreen!!.init()
    }

    fun updateVisibility() {
        currentScreen!!.updateVisibility()
    }

    override fun resize(width: Int, height: Int) {
        camera.setToOrtho(false, width.toFloat(), height.toFloat())
    }

    override fun render(batch: Batch) {
        currentScreen!!.render()
    }

    fun dispose() {
        stage.dispose()
    }
}
