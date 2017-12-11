package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.graphics.g2d.Sprite
import io.github.jerukan.game.GameState
import io.github.jerukan.game.WorldRenderer
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.game.ui.ButtonGroup
import io.github.jerukan.game.ui.gamescreen.buttons.UnitActionButton
import io.github.jerukan.util.Constants

import java.util.ArrayList

class UnitActionMenu : ButtonGroup() {

    internal var actionButtons: ArrayList<UnitActionButton>

    init {
        actionButtons = ArrayList()
    }

    fun clearButtons() {
        actionButtons.clear()
        table.clear()
    }

    fun generateButtons(selected: Unit?) {
        clearButtons()
        for (action in selected!!.baseunit.actions) {
            val button = UnitActionButton(selected, action)
            actionButtons.add(button)
            table.add(button).pad(5f).row()
        }
    }

    override fun updateVisibility() {
        if (GameState.instance.boardManager.selectedPosition.isValid) {
            val selected = GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.selectedPosition)
            if (selected != null && selected.owner == GameState.instance.currentPlayer) {
                if (actionButtons.size == 0) {
                    generateButtons(GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.selectedPosition))
                }
                WorldRenderer.boardCam.updateOffsets()
                val s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.selectedPosition).sprite
                table.setPosition(s.x + s.width + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.y - WorldRenderer.boardCam.camOffsetY)
                table.isVisible = true
            } else {
                clearButtons()
                table.isVisible = false
            }
        } else {
            clearButtons()
            table.isVisible = false
        }
    }
}
