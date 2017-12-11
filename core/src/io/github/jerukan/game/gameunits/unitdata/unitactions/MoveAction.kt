package io.github.jerukan.game.gameunits.unitdata.unitactions

import io.github.jerukan.game.GameState
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

import java.util.ArrayList

class MoveAction(baseUnit: BaseUnit) : UnitAction(baseUnit) {

    init {
        name = "move"
        speedConsumption = 0   //speed is modified based on distance traveled
        requiresTarget = true
    }

    override fun execute(self: Unit) {

    }

    override fun execute(self: Unit, target: Position) {
        if (target.existsInArray(availableTargets)) {
            if (GameState.instance.unitManager.positionAvailable(target)) {
                var speedconsump = 0
                //checks the corresponding speed the tile will consume
                for (i in 0 until self.getAvailableTargets().size) {
                    if (self.getAvailableTargets()[i].equals(target)) {
                        speedconsump = targetSpeedConsumptions[i]
                    }
                }
                self.currentSpeed = self.currentSpeed - speedconsump
                self.position = Position(target)
                self.moveSprite(target)
            }
        }
    }

    override fun getTarget(self: Unit, startpos: Position, checkedpos: Position, moves: ArrayList<Position>, moveConsump: ArrayList<Int>, aggregateConsump: Int, movesleft: Int) {
        var aggregateConsump = aggregateConsump
        var movesleft = movesleft
        val speedconsump = GameState.instance.boardManager.tileFromPosition(checkedpos).speedConsump

        if (!startpos.equals(checkedpos)) {
            if (movesleft < speedconsump - 1) {
                return
            }

            if (!checkedpos.existsInArray(moves)) {
                moves.add(checkedpos)
                moveConsump.add(aggregateConsump)
            }
        }
        aggregateConsump += speedconsump
        movesleft -= speedconsump

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
        targetSpeedConsumptions.clear()
        getTarget(self, self.position, Position(self.position), availableTargets, targetSpeedConsumptions, 0, self.currentSpeed)
    }
}