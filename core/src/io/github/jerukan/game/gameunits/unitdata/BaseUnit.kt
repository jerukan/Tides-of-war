package io.github.jerukan.game.gameunits.unitdata

import com.badlogic.gdx.graphics.Texture
import io.github.jerukan.game.GameState
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.AttackAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.DismissAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.MoveAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

import java.util.ArrayList

/** A base class for all the stats and available actions of a single type of unit
 * Not actually the object that gets placed on the board  */

abstract class BaseUnit {

    //temporary id will be set later in UnitRegistry
    var id = -1

    var name: String
    var baseHealth: Int = 0
    var baseAttack: Int = 0
    var baseSpeed: Int = 0
    var baseRange: Int = 0

    var baseUpkeep: Int = 0
    var baseCost: Int = 0

    var type: Type

    var actions: Array<UnitAction>

    var description: String

    var requiredUnit: BaseUnit? = null

    /** Call this in the constructor of the custom unit
     * @param texture specified texture of the unit, size (usually) doesn't matter
     */
    var texture: Texture? = null
        protected set

    /** Creates a reference unit containing stats of the unit and actions it can perform
     * These are default stats  */
    init {
        name = "dood"
        baseHealth = 10
        baseAttack = 0
        baseSpeed = 0
        baseRange = 0

        baseUpkeep = 1
        baseCost = 100

        actions = arrayOf(MoveAction(this), AttackAction(this), DismissAction(this))

        description = "This is a generic unit without any love put into it"

        type = Type.SOLDIER
    }

    /** Uses custom condition canBuild given below to determine valid build positions
     * May be overridden
     * @param pos the selected position to build on
     * @param owner the player who owns this unit
     * @return whether the unit can be built or not
     */
    open fun _canBuild(pos: Position, owner: Player): Boolean {
        return (owner.hasSufficientMoney(baseCost)
                && pos.existsInArray(GameState.instance.boardManager.availableBuildPositions)
                && canBuild(owner) && owner.hasSufficientUpkeep(baseUpkeep))
    }

    /** Defines the conditions under which the unit can be built under
     * Does not include the insufficient money condition: that is assumed for all units
     * @param owner the player who owns this unit
     * @return whether the unit can be built or not
     */
    abstract fun canBuild(owner: Player): Boolean

    /** Action that is performed when a unit is added to the board
     * @param self the unit being created
     */
    abstract fun onCreation(self: Unit)

    /** Action that is performed when a unit is killed
     * @param self the unit being killed
     */
    abstract fun onDeath(self: Unit)

    /** Action that is performed on the start of the player's turn
     * Only called on the owner's turn start
     * @param self the existing selected unit
     */
    abstract fun onTurnStart(self: Unit)

    /** Action that is performed on the end of the player's turn
     * Only called on the owner's turn end
     * @param self the existing selected unit
     */
    abstract fun onTurnEnd(self: Unit)

    fun buttonDisplayString(): String {
        return "$name $$baseCost"
    }

    fun dispose() {
        texture!!.dispose()
    }

    enum class Type {
        BUILDING, SOLDIER, FLYING
    }
}