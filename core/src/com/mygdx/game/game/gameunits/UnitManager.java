package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.game.Player;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;

import java.util.ArrayList;

public class UnitManager {

    private ArrayList<Unit> unitlist = new ArrayList<Unit>();

    public UnitManager() {}

    public void addUnit(BaseUnit unit, Player player, int[] position) {
        unitlist.add(new Unit(unit, player, position));
    }

    public void addUnit(Unit unit) {
        unitlist.add(unit);
    }

    public Unit unitFromPosition(int x, int y) {
        for(Unit unit : unitlist) {
            int[] pos = unit.getPosition();
            if(pos[0] == x && pos[1] == y) {
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
