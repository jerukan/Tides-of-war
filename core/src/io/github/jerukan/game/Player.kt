package io.github.jerukan.game

import com.badlogic.gdx.graphics.Color
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Constants

/** This will be you  */

class Player(val name: String, val color: Color) {

    var unitCap: Int = 0
        private set
    var money: Int = 0

    init {
        //make sure color is solid
        color.set(color.r, color.g, color.b, 1f)

        unitCap = Constants.DEFAULT_UNIT_CAP
        money = Constants.DEFAULT_START_MONEY
    }

    fun onNewTurn() {
        money += Constants.DEFAULT_MONEY_PRODUCTION
    }

    fun hasSufficientMoney(money: Int): Boolean {
        return this.money >= money
    }

    fun hasSufficientUpkeep(cost: Int): Boolean {
        return GameState.instance.unitManager.totalUpkeepFromPlayer(this) + cost <= unitCap
    }

    fun addUnitCap(`val`: Int) {
        unitCap += `val`
    }
}
