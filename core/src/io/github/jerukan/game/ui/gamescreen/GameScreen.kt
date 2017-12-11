package io.github.jerukan.game.ui.gamescreen

import com.badlogic.gdx.scenes.scene2d.Stage
import io.github.jerukan.game.GameState
import io.github.jerukan.game.ui.Screen
import io.github.jerukan.game.ui.gamescreen.menus.*
import io.github.jerukan.util.NamedFlag

class GameScreen(stage: Stage) : Screen(stage) {

    private val tileSelectMenu: TileSelectMenu
    private val unitBuildMenu: UnitBuildMenu
    private val unitActionMenu: UnitActionMenu

    private val infoDisplayMenu: InfoDisplayMenu
    private val buildInfoMenu: BuildInfoMenu

    private val endTurnMenu: EndTurnMenu

    private val building: NamedFlag

    init {

        building = NamedFlag("build", false)
        val buildMenuListeners = arrayOf(building)

        tileSelectMenu = TileSelectMenu(buildMenuListeners)

        unitBuildMenu = UnitBuildMenu(buildMenuListeners)

        unitActionMenu = UnitActionMenu()

        infoDisplayMenu = InfoDisplayMenu()

        endTurnMenu = EndTurnMenu()

        buildInfoMenu = BuildInfoMenu(unitBuildMenu)

        addMenus(tileSelectMenu, unitBuildMenu, unitActionMenu, infoDisplayMenu, endTurnMenu, buildInfoMenu)
    }

    override fun init() {
        tileSelectMenu.table.isVisible = false
        unitBuildMenu.table.isVisible = false
        unitActionMenu.table.isVisible = false
        infoDisplayMenu.table.isVisible = true
        endTurnMenu.table.isVisible = true
        buildInfoMenu.table.isVisible = true
    }

    override fun clearWindows() {
        building.state = false
        if (tileSelectMenu.table.isVisible || unitBuildMenu.table.isVisible || unitActionMenu.table.isVisible) {
            GameState.instance.boardManager.selectedPosition.reset()
        }
        tileSelectMenu.table.isVisible = false
        unitBuildMenu.table.isVisible = false
        unitActionMenu.table.isVisible = false
    }
}