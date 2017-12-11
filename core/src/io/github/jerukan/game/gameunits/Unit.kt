package io.github.jerukan.game.gameunits

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import io.github.jerukan.game.GameState
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.game.gameunits.unitdata.unitactions.EmptyAction
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

import java.util.ArrayList

/** The unit that is actually placed on the board and handled
 * Not to be confused with the higher pH version of itself  */

class Unit
/** Creates the real unit that should belong to the board
 * @param unit the reference unit
 * @param owner player that owns the unit
 * @param position xy position on the board
 */
(val baseunit: BaseUnit, val owner: Player, var position: Position) {

    var currentHealth: Int = 0
    var currentAttack: Int = 0
    var currentSpeed: Int = 0
    var currentRange: Int = 0

    var currentAction: UnitAction? = null

    private val sprite: Sprite

    private val availableTargets: ArrayList<Position>
    private val targetSpeedConsumptions: ArrayList<Int>

    val isDead: Boolean
        get() = currentHealth <= 0

    init {

        currentHealth = baseunit.baseHealth
        currentAttack = baseunit.baseAttack
        currentSpeed = baseunit.baseSpeed
        currentRange = baseunit.baseRange

        currentAction = EmptyAction(baseunit)

        sprite = Sprite(baseunit.texture!!)
        moveSprite(position)

        availableTargets = ArrayList()
        targetSpeedConsumptions = ArrayList()
    }

    fun render(batch: Batch) {
        batch.draw(sprite.texture, sprite.x, sprite.y, sprite.width, sprite.height)
    }

    fun moveSprite(position: Position) {
        val tile = GameState.instance.boardManager.board[position.x][position.y].sprite
        sprite.setSize(Constants.UNIT_SIZE, Constants.UNIT_SIZE)
        val spriteoffset = (Constants.TILE_SIZE - Constants.UNIT_SIZE) / 2
        sprite.setPosition(tile.x + spriteoffset, tile.y + spriteoffset)
    }

    fun clearMoves() {
        availableTargets.clear()
    }

    fun hasSufficientSpeed(spd: Int): Boolean {
        return currentSpeed >= spd
    }

    fun clearMoveLists() {
        availableTargets.clear()
        targetSpeedConsumptions.clear()
    }

    // unit actions

    fun takeDamage(amount: Int) {
        currentHealth -= amount
    }

    fun onCreation() {
        baseunit.onCreation(this)
    }

    fun onDeath() {
        baseunit.onDeath(this)
    }

    fun onTurnStart() {
        baseunit.onTurnStart(this)
    }

    fun onTurnEnd() {
        baseunit.onTurnEnd(this)
    }

    // mutators

    fun resetSpeed() {
        currentSpeed = baseunit.baseSpeed
    }

    fun setAvailableTargets(positions: ArrayList<Position>) {
        availableTargets.clear()
        availableTargets.addAll(positions)
    }

    fun setTargetSpeedConsumptions(speeds: ArrayList<Int>) {
        targetSpeedConsumptions.clear()
        targetSpeedConsumptions.addAll(speeds)
    }

    // accessors

    fun getAvailableTargets(): ArrayList<Position> {
        return availableTargets
    }
}
