package com.mygdx.game.game.ui.buttongroups;

import com.mygdx.game.game.gameunits.AllUnits;

public class UnitBuildMenu extends ButtonGroup {

    public UnitBuildMenu() {
        table = AllUnits.getUnitTable();
    }
}
