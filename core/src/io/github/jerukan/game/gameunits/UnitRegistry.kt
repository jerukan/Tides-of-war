package io.github.jerukan.game.gameunits

import io.github.jerukan.game.gameunits.unitdata.*
import io.github.jerukan.util.Assets

import java.util.ArrayList
import java.util.Collections

/** Manages instances of every type of unit that is to exist in the game  */

object UnitRegistry {

    private var currentId = 0

    var footman = BasicUnit("footman", 2, 1, 2, 1, 100,
            null, BaseUnit.Type.SOLDIER, "Good ol\' reliable.", Assets.getTexture(Assets.footman))
    var archer = BasicUnit("archer", 1, 1, 2, 2, 100, null, BaseUnit.Type.SOLDIER, "Masters of rushing, but die to a tap.", Assets.getTexture(Assets.archer))
    var armory = BasicUnit("armory", 7, 0, 0, 0, 400, null, BaseUnit.Type.BUILDING, "Allows the production of spearmen and shieldbearers", Assets.getTexture(Assets.armory))
    var blimpWorkshop = BasicUnit("blimp workshop", 6, 0, 0, 0, 400, null, BaseUnit.Type.BUILDING, "Allows production of basic flying units", Assets.getTexture(Assets.blimpWorkshop))
    var spearman = BasicUnit("spearman", 3, 2, 3, 1, 200,
            armory, BaseUnit.Type.SOLDIER, "A reliable fast unit that may or may not be broken.", Assets.getTexture(Assets.spearman))
    var blimp = BasicUnit("blimp", 3, 1, 3, 2, 300,
            blimpWorkshop, BaseUnit.Type.FLYING, "A flying unit able to traverse terrain without penalty.", Assets.getTexture(Assets.blimp))

    var village = VillageUnit()
    var goldmine = GoldmineUnit()
    var farm = FarmUnit()

    var unitList = ArrayList<BaseUnit>()

    private fun register(unit: BaseUnit) {
        unit.id = currentId
        unitList.add(unit)
        currentId++
    }

    private fun registerUnits() {
        register(footman)
        register(archer)
        register(armory)
        register(village)
        register(spearman)
        register(goldmine)
        register(farm)
        register(blimpWorkshop)
        register(blimp)
    }

    private fun validateUnits() {
        for (unit in unitList) {
            for (other in unitList) {
                if (unit != other && unit.name == other.name) {
                    throw IllegalArgumentException("Two or more units of name \"" + unit.name + "\" found")
                }
            }
        }
    }

    fun init() {
        registerUnits()
        validateUnits()
    }

    fun getUnitFromName(name: String): BaseUnit {
        for (unit in unitList) {
            if (unit.name == name) {
                return unit
            }
        }
        throw NullPointerException("Unit \"$name\" does not exist")
    }

    fun dispose() {
        for (unit in unitList) {
            unit.dispose()
        }
    }
}
