package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Align
import io.github.jerukan.game.GameState
import io.github.jerukan.game.WorldRenderer
import io.github.jerukan.game.gameunits.UnitRegistry
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.game.ui.ButtonGroup
import io.github.jerukan.game.ui.gamescreen.buttons.UnitBuildButton
import io.github.jerukan.util.NamedFlag
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

import java.util.ArrayList

/** Menu that appears when a player is selecting a unit to build  */

class UnitBuildMenu(flags: Array<NamedFlag>) : ButtonGroup(flags) {

    private val currentPos: Position
    private val unitButtons: ArrayList<UnitBuildButton>

    val hoveredButton: UnitBuildButton?
        get() {
            for (button in unitButtons) {
                if (button.isHovered) {
                    return button
                }
            }
            return null
        }

    init {
        currentPos = Position()
        table.align(Align.bottomLeft)
        unitButtons = ArrayList()
        for (unit in UnitRegistry.unitList) {
            unitButtons.add(UnitBuildButton(unit, flagFromArray("build")))
        }
        resetTable()
    }

    fun resetTable() {
        table.clear()
        for (button in unitButtons) {
            if (button.baseUnit._canBuild(GameState.instance.boardManager.selectedPosition, GameState.instance.currentPlayer)) {
                table.add(button).pad(5f).row()
            }
        }
    }

    override fun updateVisibility() {
        if (!currentPos.equals(GameState.instance.boardManager.selectedPosition)) {
            flagFromArray("build").state = false
            currentPos.setPos(GameState.instance.boardManager.selectedPosition)
        }
        if (flagFromArray("build").state) {
            if (GameState.instance.boardManager.selectedPosition.isValid) {
                if (GameState.instance.unitManager.positionAvailable(GameState.instance.boardManager.selectedPosition)) {
                    WorldRenderer.boardCam.updateOffsets()
                    val s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.selectedPosition).sprite
                    table.setPosition(s.x + s.width + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.y - WorldRenderer.boardCam.camOffsetY)
                    table.isVisible = true
                } else {
                    flagFromArray("build").state = false
                }
            }
        } else {
            resetTable()
            table.isVisible = false
        }
    }
}
