package io.github.jerukan.game.gameunits.unitdata

import com.badlogic.gdx.graphics.Texture
import io.github.jerukan.game.GameState
import io.github.jerukan.game.Player
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.UnitRegistry
import io.github.jerukan.game.gameunits.unitdata.unitactions.*

/** All the units that don't have anything very special to them  */

class BasicUnit(name: String, health: Int, attack: Int, speed: Int, range: Int, cost: Int, requiredUnit: BaseUnit?, type: BaseUnit.Type, description: String, texture: Texture) : BaseUnit() {

    init {
        this.name = name
        baseHealth = health
        baseAttack = attack
        baseSpeed = speed
        baseRange = range

        baseCost = cost

        this.requiredUnit = requiredUnit

        this.type = type

        this.description = description

        if (this.type === BaseUnit.Type.SOLDIER) {
            actions = arrayOf(MoveAction(this), AttackAction(this), DismissAction(this))
            baseUpkeep = 1
        } else if (this.type === BaseUnit.Type.BUILDING) {
            actions = arrayOf(DismissAction(this))
            baseUpkeep = 0
        } else if (this.type === BaseUnit.Type.FLYING) {
            actions = arrayOf(MoveFlyAction(this), AttackAction(this), DismissAction(this))
            baseUpkeep = 1
        }

        this.texture = texture
    }

    override fun canBuild(owner: Player): Boolean {
        return requiredUnit == null || GameState.instance.unitManager.playerHasUnit(owner, requiredUnit!!.id)
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
