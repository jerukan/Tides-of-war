package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Manager;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

import java.util.ArrayList;
import java.util.function.Predicate;

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

    public void resetSelectedUnit() {
        selectedUnit = null;
    }

    public void setSelectedToLast() {
        this.selectedUnit = unitlist.get(unitlist.size() - 1);
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public Unit unitFromPosition(Position pos) {
        if(!pos.isValid()) {
            return null;
        }
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

    public ArrayList<Unit> unitsFromPlayer(Player p, Predicate<Unit> pred) {
        ArrayList<Unit> out = new ArrayList<Unit>();
        for(Unit u : unitlist) {
            if(pred.test(u) && u.getOwner() == p) {
                out.add(u);
            }
        }
        return out;
    }

    public boolean playerHasUnit(Player p, String unitname) {
        for(Unit u : unitlist) {
            if(u.baseunit.name.equals(unitname) && u.getOwner() == p) {
                return true;
            }
        }
        return false;
    }

    /** Removes the corpses from the field */
    public void killUnits() {
        for(int i = 0; i < unitlist.size(); i++) {
            if(unitlist.get(i).isDead()) {
                unitlist.get(i).onDeath();
                unitlist.remove(i);
            }
        }
    }

    public void onNewTurn() {
        ArrayList<Unit> temp = unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> !u.isDead());

        for(Unit u : temp) {
            u.resetSpeed();
            u.onTurnStart();
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
        generateUnitMoves();
    }

    public ArrayList<Unit> getUnitlist() {
        return unitlist;
    }
}
