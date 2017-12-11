package io.github.jerukan.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.jerukan.Main
import io.github.jerukan.util.Constants

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = Constants.GAME_NAME
        config.width = 1000
        config.height = 900
        LwjglApplication(Main(), config)
    }
}
