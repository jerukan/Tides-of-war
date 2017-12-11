package io.github.jerukan.game.gameunits.unitdata

import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.DismissAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Assets

class FarmUnit : BaseUnit() {
    init {
        name = "farm"
        baseHealth = 6

        baseUpkeep = 0

        baseCost = 350

        actions = arrayOf(DismissAction(this))

        type = BaseUnit.Type.BUILDING

        description = "Increases unit upkeep by " + FARM_DEFAULT_INCREASE
        texture = Assets.getTexture(Assets.farm)
    }

    override fun canBuild(owner: Player): Boolean {
        return true
    }

    override fun onCreation(self: Unit) {
        self.owner.addUnitCap(FARM_DEFAULT_INCREASE)
    }

    override fun onDeath(self: Unit) {
        self.owner.addUnitCap(-FARM_DEFAULT_INCREASE)
    }

    override fun onTurnStart(self: Unit) {

    }

    override fun onTurnEnd(self: Unit) {

    }

    companion object {

        var FARM_DEFAULT_INCREASE = 2
    }
}
