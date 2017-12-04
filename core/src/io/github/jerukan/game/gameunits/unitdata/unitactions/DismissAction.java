package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

public class DismissAction extends UnitAction {

    public DismissAction(BaseUnit baseUnit) {
        this.baseUnit = baseUnit;
        name = "dismiss";
        speedConsumption = 0;
        requiresTarget = false;
    }

    @Override
    public void execute(Unit self) {
        GameState.instance.unitManager.removeUnit(self);
    }

    @Override
    public void execute(Unit self, Position target) {

    }
}
