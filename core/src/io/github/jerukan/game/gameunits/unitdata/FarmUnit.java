package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;

public class FarmUnit extends BaseUnit {

    public static int FARM_DEFAULT_INCREASE = 2;

    public FarmUnit() {
        name = "farm";
        baseHealth = 6;

        baseUpkeep = 0;

        baseCost = 350;

        actions = new UnitAction[]{};

        type = Type.BUILDING;

        description = "Increases unit upkeep by " + FARM_DEFAULT_INCREASE;
        setTexture(Assets.getTexture(Assets.wall));
    }

    @Override
    public boolean canBuildCondition(Player owner) {
        return true;
    }

    @Override
    public void onCreation(Unit self) {
        self.getOwner().addUnitCap(FARM_DEFAULT_INCREASE);
    }

    @Override
    public void onDeath(Unit self) {
        self.getOwner().addUnitCap(-FARM_DEFAULT_INCREASE);
    }

    @Override
    public void onTurnStart(Unit self) {

    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
