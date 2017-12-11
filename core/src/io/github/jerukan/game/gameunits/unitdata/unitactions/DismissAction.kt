package io.github.jerukan.game.gameunits.unitdata.unitactions

import io.github.jerukan.game.GameState
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Position

import java.util.ArrayList

class DismissAction(baseUnit: BaseUnit) : UnitAction(baseUnit) {

    init {
        name = "dismiss"
        speedConsumption = 0
        requiresTarget = false
    }

    override fun execute(self: Unit) {
        GameState.instance.unitManager.removeUnit(self)
    }

    override fun execute(self: Unit, target: Position) {

    }

    override fun getTarget(self: Unit, startpos: Position, checkedpos: Position, moves: ArrayList<Position>, moveConsump: ArrayList<Int>, aggregateConsump: Int, movesleft: Int) {

    }

    override fun generateTargets(self: Unit) {

    }
}
