package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

public class EmptyAction extends UnitAction {

    public EmptyAction(BaseUnit baseUnit) {
        super(baseUnit);
        name = "none";
        speedConsumption = 0;
        requiresTarget = false;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {

    }

    @Override
    public void getTarget(Unit self, Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft) {

    }

    @Override
    public void generateTargets(Unit self) {

    }
}
