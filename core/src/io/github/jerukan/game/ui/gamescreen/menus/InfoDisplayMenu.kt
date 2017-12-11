package io.github.jerukan.game.ui.gamescreen.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import io.github.jerukan.game.GameState
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.GoldmineUnit
import io.github.jerukan.game.ui.Menu
import io.github.jerukan.util.Assets
import io.github.jerukan.util.Constants

class InfoDisplayMenu : Menu() {

    private val BLANK_TILE_TEXT = "No tile selected"
    private val NO_UNIT_TEXT = "No unit here"

    private val TABLE_WIDTH = 200f

    private val tileInfo: Label
    private val currentPlayerInfo: Label
    private val unitInfo: Label

    init {
        val numofmines = GameState.instance.unitManager.unitsFromPlayer(GameState.instance.currentPlayer) { u: Unit -> u.baseunit.name == "goldmine" }.size
        tileInfo = Label(BLANK_TILE_TEXT, Assets.uiskin, "default")
        currentPlayerInfo = Label("$" + GameState.instance.currentPlayer.money + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION), Assets.uiskin, "default")
        unitInfo = Label(NO_UNIT_TEXT, Assets.uiskin, "default")
        unitInfo.setWrap(true)

        table.add(tileInfo).pad(10f).row()
        table.add(currentPlayerInfo).pad(10f).row()
        table.add(unitInfo).width(TABLE_WIDTH)

        table.width = TABLE_WIDTH
        table.height = tileInfo.height + currentPlayerInfo.height + unitInfo.height
        table.align(Align.topLeft)

        table.setPosition(10f, Gdx.graphics.height.toFloat() - table.height - 20f)
    }

    override fun updateVisibility() {
        //PUT SOMEWHERE ELSE LATER
        val currentplayer = GameState.instance.currentPlayer
        val numofmines = GameState.instance.unitManager.unitsFromPlayer(currentplayer) { u: Unit -> u.baseunit.name == "goldmine" }.size
        if (GameState.instance.boardManager.getHoveredPosition().isValid) {
            tileInfo.setText("Hovered tile: " + GameState.instance.boardManager.getHoveredPosition().toString()
                    + "\nTerrain: " + GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.getHoveredPosition()).terrain.name
                    + "\nSpeed needed: " + GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.getHoveredPosition()).terrain.speedConsump)
        } else {
            tileInfo.setText(BLANK_TILE_TEXT)
        }

        currentPlayerInfo.setText("$" + currentplayer.money + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION)
                + " next turn\nCurrent upkeep: "
                + GameState.instance.unitManager.totalUpkeepFromPlayer(currentplayer) + "/"
                + currentplayer.unitCap)

        if (GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getHoveredPosition()) != null) {
            val u = GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getHoveredPosition())
            unitInfo.setText("Unit: " + u!!.baseunit.name
                    + "\nOwner: " + u.owner.name
                    + "\nHealth: " + u.currentHealth + "/" + u.baseunit.baseHealth
                    + "\nSpeed: " + u.currentSpeed + "/" + u.baseunit.baseSpeed
                    + "\nAttack: " + u.currentAttack
                    + "\nType: " + u.baseunit.type
                    + "\n" + u.baseunit.description)
        } else {
            unitInfo.setText(NO_UNIT_TEXT)
        }
        table.height = tileInfo.height + unitInfo.height
        table.setPosition(10f, Gdx.graphics.height.toFloat() - table.height - 10f)
    }
}
