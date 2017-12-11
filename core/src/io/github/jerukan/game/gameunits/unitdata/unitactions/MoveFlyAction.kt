package io.github.jerukan.game.gameunits.unitdata.unitactions

import io.github.jerukan.game.GameState
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

import java.util.ArrayList

class MoveFlyAction(baseUnit: BaseUnit) : UnitAction(baseUnit) {

    init {
        name = "fly"
        speedConsumption = 0 //changed based on distance
        requiresTarget = true
    }

    override fun execute(self: Unit) {

    }

    override fun execute(self: Unit, target: Position) {
        if (target.existsInArray(availableTargets)) {
            if (GameState.instance.unitManager.positionAvailable(target)) {
                self.currentSpeed = self.currentSpeed - target.distanceToPosition(self.position)
                self.position = Position(target)
                self.moveSprite(target)
            }
        }
    }

    override fun getTarget(self: Unit, startpos: Position, checkedpos: Position, moves: ArrayList<Position>, moveConsump: ArrayList<Int>, aggregateConsump: Int, movesleft: Int) {
        var movesleft = movesleft
        if (!startpos.equals(checkedpos)) {
            if (movesleft < 0) {
                return
            }

            if (!checkedpos.existsInArray(moves)) {
                moves.add(checkedpos)
            }
        }
        movesleft -= 1

        if (checkedpos.x + 1 < Constants.BOARD_WIDTH) {
            val newpos = Position(checkedpos.x + 1, checkedpos.y)
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft)
        }
        if (checkedpos.x - 1 >= 0) {
            val newpos = Position(checkedpos.x - 1, checkedpos.y)
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft)
        }
        if (checkedpos.y + 1 < Constants.BOARD_HEIGHT) {
            val newpos = Position(checkedpos.x, checkedpos.y + 1)
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft)
        }
        if (checkedpos.y - 1 >= 0) {
            val newpos = Position(checkedpos.x, checkedpos.y - 1)
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft)
        }
    }

    override fun generateTargets(self: Unit) {
        availableTargets.clear()
        getTarget(self, self.position, Position(self.position), availableTargets, targetSpeedConsumptions, 0, self.currentSpeed)
    }
}
