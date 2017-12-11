package io.github.jerukan.game.gameunits.unitdata

import io.github.jerukan.game.GameState
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Assets
import io.github.jerukan.util.Position

class VillageUnit : BaseUnit() {
    init {
        name = "village"
        baseHealth = 20

        baseCost = 0

        actions = arrayOf()

        baseUpkeep = 0

        type = BaseUnit.Type.BUILDING

        description = "This is your lifeline. Don\'t let it die."
        texture = Assets.getTexture(Assets.village)
    }

    override fun _canBuild(pos: Position, owner: Player): Boolean {
        return canBuild(owner)
    }

    override fun canBuild(owner: Player): Boolean {
        return !GameState.instance.unitManager.playerHasUnit(owner, id)
    }

    override fun onCreation(self: Unit) {

    }

    override fun onDeath(self: Unit) {

    }

    override fun onTurnStart(self: Unit) {

    }

    override fun onTurnEnd(self: Unit) {

    }
}
