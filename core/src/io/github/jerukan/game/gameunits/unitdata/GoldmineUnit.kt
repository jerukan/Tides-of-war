package io.github.jerukan.game.gameunits.unitdata

import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.DismissAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Assets

class GoldmineUnit : BaseUnit() {
    init {
        name = "goldmine"
        baseHealth = 6

        baseUpkeep = 0

        baseCost = 450

        actions = arrayOf(DismissAction(this))

        type = BaseUnit.Type.BUILDING

        description = "Increases money production by $$GOLDMINE_DEFAULT_PRODUCTION"
        texture = Assets.getTexture(Assets.goldmine)
    }

    override fun canBuild(owner: Player): Boolean {
        return true
    }

    override fun onCreation(self: Unit) {

    }

    override fun onDeath(self: Unit) {

    }

    override fun onTurnStart(self: Unit) {
        self.owner.money = self.owner.money + GOLDMINE_DEFAULT_PRODUCTION
    }

    override fun onTurnEnd(self: Unit) {

    }

    companion object {

        val GOLDMINE_DEFAULT_PRODUCTION = 100
    }
}
