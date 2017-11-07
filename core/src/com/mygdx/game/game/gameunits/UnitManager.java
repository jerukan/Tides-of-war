package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.game.Player;
import com.mygdx.game.game.gameunits.Unit;

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

    public void clearUnits() {
        unitlist.clear();
    }

    public void render(Batch batch) {
        for(Unit unit : unitlist) {
            unit.render(batch);
        }
    }
}
