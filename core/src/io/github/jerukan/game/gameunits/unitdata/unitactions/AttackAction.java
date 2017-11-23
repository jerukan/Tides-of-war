package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.Util;

public class AttackAction extends UnitAction {

    public AttackAction(BaseUnit baseUnit) {
        this.baseUnit = baseUnit;
        name = "attack";
        speedConsumption = 1;
        requiresTarget = true;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {
        if(Util.arrayContainsPosition(target, self.getAvailableAttacks())) {
            if (GameState.instance.unitManager.unitFromPosition(target) != null) {
                self.targetAction(GameState.instance.unitManager.unitFromPosition(target));
                self.setCurrentSpeed(self.getCurrentSpeed() - speedConsumption);
            }
        }
    }

}