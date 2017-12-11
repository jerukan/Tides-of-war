package io.github.jerukan.game.gameunits.unitdata.unitactions

import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Position

import java.util.ArrayList

/** A class that defines an action some unit could use
 * There are two basic actions: moving and attacking  */

abstract class UnitAction
/** Constructs an action using stats from a base unit
 * Can be restricted to a single unit and such  */
(protected var baseUnit: BaseUnit) {
    var name: String? = null
        protected set
    var speedConsumption: Int = 0
        protected set
    var requiresTarget: Boolean = false

    var availableTargets = ArrayList<Position>()
        protected set
    var targetSpeedConsumptions = ArrayList<Int>()
        protected set

    /** Action without a target
     * @param self the unit to perform this action
     */
    abstract fun execute(self: Unit)

    /** Action with a target
     * @param self the unit to perform this action
     * @param target the selected position
     */
    abstract fun execute(self: Unit, target: Position)

    /** Recursion flood fill galore (or not you decide)
     * @param self the dude who is performing the action
     * @param startpos the unit's position
     * @param checkedpos the position being checked
     * @param moves ArrayList of all the available moves of the unit
     * @param moveConsump how much speed each position in its corresponding position will take up
     * @param aggregateConsump stuck into moveConsump to get the overall speed consumption at each position
     * @param movesleft how much speed is left in the loop
     */
    abstract fun getTarget(self: Unit, startpos: Position, checkedpos: Position, moves: ArrayList<Position>, moveConsump: ArrayList<Int>, aggregateConsump: Int, movesleft: Int)

    /** Will call getTarget to generate the positions
     * @param self the dude performing the action
     */
    abstract fun generateTargets(self: Unit)
}
