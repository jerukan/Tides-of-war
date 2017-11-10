package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.game.Player;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.util.Position;

import java.util.ArrayList;

public class UnitManager {

    private ArrayList<Unit> unitlist = new ArrayList<Unit>();

    public UnitManager() {}

    public void addUnit(BaseUnit unit, Player player, Position position) {
        unitlist.add(new Unit(unit, player, position));
    }

    public void addUnit(Unit unit) {
        unitlist.add(unit);
    }

    public Unit unitFromPosition(int x, int y) {
        for(Unit unit : unitlist) {
            Position pos = unit.getPosition();
            if(pos.equals(new Position(x, y))) {
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
