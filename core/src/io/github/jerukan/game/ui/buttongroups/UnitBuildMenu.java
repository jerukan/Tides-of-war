package io.github.jerukan.game.ui.buttongroups;

import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.game.gameunits.unitfiles.BaseUnit;
import io.github.jerukan.game.ui.UnitBuildButton;

public class UnitBuildMenu extends ButtonGroup {

    public UnitBuildMenu() {
        //TODO make it automatically switch to new columns
        table.pad(10);
        for(BaseUnit unit : AllUnits.list) {
            table.add(new UnitBuildButton(unit));
            table.row();
        }
    }

    @Override
    public void updateVisibility() {

    }
}
