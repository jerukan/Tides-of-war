package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

public class EmptyAction extends UnitAction {

    public EmptyAction(BaseUnit baseUnit) {
        super(baseUnit);
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {

    }
}
