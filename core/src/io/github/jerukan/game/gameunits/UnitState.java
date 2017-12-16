package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.State;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

import java.util.ArrayList;
import java.util.function.Predicate;

/** A class that organizes the units into an aggregate unitList
 * Manages the logistics of the units instead of having the board do everything */
public class UnitState implements State {

    private ArrayList<Unit> unitlist;

    private Unit selectedUnit;

    public UnitState() {
        unitlist = new ArrayList<>();
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

    /** kill kill kill
     * @param unit this dude is dunzo */
    public void removeUnit(Unit unit) {
        unitlist.remove(unit);
    }

    public void buildUnit(BaseUnit baseUnit) {
        if(baseUnit._canBuild(BoardManager.getSelectedPosition(), GameState.instance.getCurrentPlayer())) {
            addUnit(baseUnit, GameState.instance.getCurrentPlayer(), new Position(BoardManager.getSelectedPosition()));
            setSelectedToLast();
            getSelectedUnit().onCreation();
            getSelectedUnit().setCurrentSpeed(0);
            BoardManager.updateAvailableBuildPositions();

            GameState.instance.getCurrentPlayer().setMoney(GameState.instance.getCurrentPlayer().getMoney() - baseUnit.baseCost);
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
        for(Unit unit : unitlist) {
            Position unitpos = unit.getPosition();
            if(unitpos.equals(pos.getPos())) {
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
        ArrayList<Unit> out = new ArrayList<>();
        for(Unit u : unitlist) {
            if(pred.test(u) && u.getOwner() == p) {
                out.add(u);
            }
        }
        return out;
    }

    public int totalUpkeepFromPlayer(Player p) {
        int out = 0;
        for(Unit u : unitlist) {
            if(u.getOwner() == p) {
                out += u.baseunit.baseUpkeep;
            }
        }
        return out;
    }

    public boolean playerHasUnit(Player p, int unitId) {
        for(Unit u : unitlist) {
            if(u.baseunit.id == unitId && u.getOwner() == p) {
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

    public void onEndTurn() {
        ArrayList<Unit> temp = unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> !u.isDead());

        for(Unit u : temp) {
            u.onTurnEnd();
        }
    }

    public void clearUnits() {
        unitlist.clear();
    }

    @Override
    public void init() {
        clearUnits();
    }

    @Override
    public void update() {
    }

    public ArrayList<Unit> getUnitlist() {
        return unitlist;
    }
}
