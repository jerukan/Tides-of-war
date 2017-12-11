package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import io.github.jerukan.game.GameState
import io.github.jerukan.game.WorldRenderer
import io.github.jerukan.game.ui.ButtonGroup
import io.github.jerukan.util.Assets
import io.github.jerukan.util.NamedFlag
import io.github.jerukan.util.Constants

/** The menu that pops up when a tile is selected
 * TODO radial menu would be cool  */

class TileSelectMenu(flags: Array<NamedFlag>) : ButtonGroup(flags) {

    private val buildButton: TextButton

    init {

        buildButton = TextButton("Build", Assets.uiskin, "default")
        buildButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                flagFromArray("build").state = true
            }
        })
        table.align(Align.left)

        table.add(buildButton).pad(10f).row()
    }

    override fun updateVisibility() {
        // when a valid tile is selected
        if (flagFromArray("build").state) {
            table.isVisible = false
        } else if (GameState.instance.boardManager.selectedPosition.isValid) {
            if (GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.selectedPosition) == null) {
                WorldRenderer.boardCam.updateOffsets()
                val s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.selectedPosition).sprite
                table.setPosition(s.x + s.width + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.y - WorldRenderer.boardCam.camOffsetY)
                table.isVisible = true
            } else {
                table.isVisible = false
            }
        } else {
            table.isVisible = false
        }
    }
}
