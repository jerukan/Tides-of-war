package io.github.jerukan

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import io.github.jerukan.game.GameState
import io.github.jerukan.game.WorldRenderer
import io.github.jerukan.game.gameunits.UnitRegistry
import io.github.jerukan.util.Assets
import io.github.jerukan.util.Colors

class Main : Game() {

    override fun create() {
        Assets.load()
        Assets.assetManager.finishLoading()
        UnitRegistry.init()
        GameState.instance = GameState()
        GameState.instance.init()
        WorldRenderer.init()
        GameState.instance.reset()
    }

    override fun render() {
        Gdx.gl.glClearColor(Colors.GAME_BACKGROUND_COLOR.r, Colors.GAME_BACKGROUND_COLOR.g, Colors.GAME_BACKGROUND_COLOR.b, Colors.GAME_BACKGROUND_COLOR.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        WorldRenderer.render()
    }

    override fun resize(width: Int, height: Int) {
        WorldRenderer.resize(width, height)
    }

    override fun dispose() {
        Assets.dispose()
        UnitRegistry.dispose()
        GameState.instance.dispose()
        WorldRenderer.dispose()
    }
}
