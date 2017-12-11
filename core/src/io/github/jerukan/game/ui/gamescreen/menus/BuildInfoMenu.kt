package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import io.github.jerukan.game.ui.ButtonGroup
import io.github.jerukan.game.ui.gamescreen.buttons.UnitBuildButton
import io.github.jerukan.util.Assets

class BuildInfoMenu(private val unitMenu: UnitBuildMenu) : ButtonGroup() {

    private val unitInfo: Label

    init {
        unitInfo = Label("", Assets.uiskin, "default")
        unitInfo.setWrap(true)

        //table.setWidth(120);
        table.add(unitInfo).width(160f)
        table.setPosition((Gdx.graphics.width - 200).toFloat(), (Gdx.graphics.height - 100).toFloat())
    }

    override fun updateVisibility() {
        val button = unitMenu.hoveredButton
        if (button != null) {
            unitInfo.setText("Unit: " + button.baseUnit.name
                    + "\nHealth: " + button.baseUnit.baseHealth
                    + "\nSpeed: " + button.baseUnit.baseSpeed
                    + "\nAttack: " + button.baseUnit.baseAttack
                    + "\nType: " + button.baseUnit.type
                    + "\n" + button.baseUnit.description)
        } else {
            unitInfo.setText("")
        }
    }
}
