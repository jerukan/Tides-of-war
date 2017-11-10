package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.game.Player;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.util.Position;

import java.util.ArrayList;

/** A class that organizes the units into an aggregate list
 * Manages the logistics of the units instead of having the board do everything */
public class UnitManager {

    private ArrayList<Unit> unitlist = new ArrayList<Unit>();

    public UnitManager() {}

    /** Adds a unit by feeding information and constructing a new unit
     * @param unit the base unit, the one with all the stats and information
     * @param player the owner of the unit
     * @param position the xy coordinate the unit will go */
    public void addUnit(BaseUnit unit, Player player, Position position) {
        unitlist.add(new Unit(unit, player, position));
    }

    public void addUnit(Unit unit) {
        unitlist.add(unit);
    }

    public Unit unitFromPosition(Position pos) {
        for(Unit unit : unitlist) {
            Position unitpos = unit.getPosition();
            if(unitpos.equals(new Position(pos.getPos()))) {
                return unit;
            }
        }
        return null;
    }

    /** Removes the corpses from the field */
    public void killUnits() {
        for(Unit unit : unitlist) {
            if(unit.isDead()) {
                unitlist.remove(unit);
            }
        }
    }

    public void clearUnits() {
        unitlist.clear();
    }

    /** Also generates attacks, I just didn't feel like putting that in the name */
    public void generateUnitMoves() {
        for(Unit unit : unitlist) {
            unit.generateMovesAndAttacks();
            System.out.println("got moves for: " + unit.baseunit.name);
        }
    }

    public void render(Batch batch) {
        batch.begin();
        for(Unit unit : unitlist) {
            unit.render(batch);
        }
        batch.end();
    }
}
