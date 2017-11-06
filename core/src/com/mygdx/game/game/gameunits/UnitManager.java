package com.mygdx.game.game.gameunits;

import com.mygdx.game.game.gameunits.Unit;

import java.util.ArrayList;

public class UnitManager {

    private ArrayList<Unit> unitlist = new ArrayList<Unit>();

    public UnitManager() {}

    public void addUnit(Unit unit) { unitlist.add(unit); }

    public void clearUnits() { unitlist.clear(); }
}
