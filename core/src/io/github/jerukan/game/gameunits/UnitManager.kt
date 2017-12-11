package io.github.jerukan.game.gameunits

import io.github.jerukan.game.GameState
import io.github.jerukan.game.Manager
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Position

import java.util.ArrayList

/** A class that organizes the units into an aggregate unitList
 * Manages the logistics of the units instead of having the board do everything  */
class UnitManager : Manager {

    val unitlist: ArrayList<Unit>

    var selectedUnit: Unit? = null

    init {
        unitlist = ArrayList()
        selectedUnit = null
    }

    /** Adds a unit by feeding information and constructing a new unit
     * @param unit the base unit, the one with all the stats and information
     * @param player the owner of the unit
     * @param position the xy coordinate the unit will go
     */
    fun addUnit(unit: BaseUnit, player: Player, position: Position) {
        if (positionAvailable(position)) {
            unitlist.add(Unit(unit, player, position))
        }
    }

    /** kill kill kill
     * @param unit this dude is dunzo
     */
    fun removeUnit(unit: Unit) {
        unitlist.remove(unit)
    }

    fun buildUnit(baseUnit: BaseUnit) {
        if (baseUnit._canBuild(GameState.instance.boardManager.selectedPosition, GameState.instance.currentPlayer)) {
            addUnit(baseUnit, GameState.instance.currentPlayer, Position(GameState.instance.boardManager.selectedPosition))
            setSelectedToLast()
            selectedUnit!!.onCreation()
            selectedUnit!!.currentSpeed = 0
            GameState.instance.boardManager.updateAvailableBuildPositions()

            GameState.instance.currentPlayer.money = GameState.instance.currentPlayer.money - baseUnit.baseCost
        }
    }

    fun resetSelectedUnit() {
        selectedUnit = null
    }

    fun setSelectedToLast() {
        this.selectedUnit = unitlist[unitlist.size - 1]
    }

    fun unitFromPosition(pos: Position): Unit? {
        for (unit in unitlist) {
            val unitpos = unit.position
            if (unitpos.equals(pos.pos)) {
                return unit
            }
        }
        return null
    }

    fun positionAvailable(pos: Position): Boolean {
        for (unit in unitlist) {
            if (pos.equals(unit.position)) {
                return false
            }
        }
        return true
    }

    fun unitsFromPlayer(p: Player, pred: (Unit) -> Boolean): ArrayList<Unit> {
        val out = ArrayList<Unit>()
        for (u in unitlist) {
            if (pred.equals(true) && u.owner === p) {
                out.add(u)
            }
        }
        return out
    }

    fun totalUpkeepFromPlayer(p: Player): Int {
        var out = 0
        for (u in unitlist) {
            if (u.owner === p) {
                out += u.baseunit.baseUpkeep
            }
        }
        return out
    }

    fun playerHasUnit(p: Player, unitId: Int): Boolean {
        for (u in unitlist) {
            if (u.baseunit.id == unitId && u.owner === p) {
                return true
            }
        }
        return false
    }

    /** Removes the corpses from the field  */
    fun killUnits() {
        for (i in unitlist.indices) {
            if (unitlist[i].isDead) {
                unitlist[i].onDeath()
                unitlist.removeAt(i)
            }
        }
    }

    fun onNewTurn() {
        val temp = unitsFromPlayer(GameState.instance.currentPlayer, { u: Unit -> !u.isDead })

        for (u in temp) {
            u.resetSpeed()
            u.onTurnStart()
        }
    }

    fun onEndTurn() {
        val temp = unitsFromPlayer(GameState.instance.currentPlayer, { u: Unit -> !u.isDead })

        for (u in temp) {
            u.onTurnEnd()
        }
    }

    fun clearUnits() {
        unitlist.clear()
    }

    override fun init() {
        clearUnits()
    }

    override fun update() {}
}
