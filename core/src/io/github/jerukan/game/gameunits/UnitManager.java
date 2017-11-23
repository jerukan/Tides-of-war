package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.Manager;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

/** A class that organizes the units into an aggregate list
 * Manages the logistics of the units instead of having the board do everything */
public class UnitManager implements Manager {

    private ArrayList<Unit> unitlist;

    private Unit selectedUnit;

    public UnitManager() {
        unitlist = new ArrayList<Unit>();
        selectedUnit = null;
    }

    /** Adds a unit by feeding information and constructing a new unit
     * @param unit the base unit, the one with all the stats and information
     * @param player the owner of the unit
     * @param position the xy coordinate the unit will go */
    public void addUnit(BaseUnit unit, Player player, Position position) {
        if(positionAvailable(position)) {
            unitlist.add(new Unit(unit, player, position));
        }
    }

    public void addUnit(Unit unit) {
        if(positionAvailable(unit.getPosition())) {
            unitlist.add(unit);
        }
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public void setSelectedToLast() {
        this.selectedUnit = unitlist.get(unitlist.size() - 1);
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
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

    public boolean positionAvailable(Position pos) {
        for(Unit unit : unitlist) {
            if(pos.equals(unit.getPosition())) {
                return false;
            }
        }
        return true;
    }

    /** Removes the corpses from the field */
    public void killUnits() {
        for(int i = 0; i < unitlist.size(); i++) {
            if(unitlist.get(i).isDead()) {
                unitlist.get(i).baseunit.onDeath(unitlist.get(i));
                unitlist.remove(i);
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
        }
    }

    @Override
    public void init() {
        AllUnits.validateUnits();
        clearUnits();
    }

    @Override
    public void update() {

    }

    public ArrayList<Unit> getUnitlist() {
        return unitlist;
    }
}
