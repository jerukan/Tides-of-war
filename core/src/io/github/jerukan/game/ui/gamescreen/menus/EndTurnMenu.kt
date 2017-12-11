package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import io.github.jerukan.game.GameState
import io.github.jerukan.game.ui.ButtonGroup
import io.github.jerukan.util.Assets

class EndTurnMenu : ButtonGroup() {

    private val currentPlayerLabel: Label
    private val endTurnButton: TextButton

    init {
        currentPlayerLabel = Label(GameState.instance.currentPlayer.name + "\'s turn", Assets.uiskin, "default")
        val labelColor = Pixmap(currentPlayerLabel.width.toInt(), currentPlayerLabel.height.toInt() + 10, Pixmap.Format.RGB888)
        labelColor.setColor(Color(0.2f, 0.2f, 0.2f, 1f))
        labelColor.fill()
        currentPlayerLabel.style.background = Image(Texture(labelColor)).drawable
        labelColor.dispose()

        endTurnButton = TextButton("End turn", Assets.uiskin, "default")
        endTurnButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                GameState.instance.passTurn()
            }
        })

        table.add(currentPlayerLabel).pad(10f).row()
        table.add(endTurnButton)
        table.align(Align.bottomRight)
        table.setPosition((Gdx.graphics.width - 100).toFloat(), 20f)
    }

    override fun updateVisibility() {
        table.setPosition((Gdx.graphics.width - 100).toFloat(), 20f)
        currentPlayerLabel.setText(GameState.instance.currentPlayer.name + "\'s turn")
    }
}
