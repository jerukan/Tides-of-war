package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;

public class GoldmineUnit extends BaseUnit {

    public static final int GOLDMINE_DEFAULT_PRODUCTION = 100;

    public GoldmineUnit() {
        name = "goldmine";
        baseHealth = 6;

        baseUpkeep = 0;

        baseCost = 450;

        actions = new UnitAction[]{};

        type = Type.BUILDING;

        description = "Increases money production by $" + GOLDMINE_DEFAULT_PRODUCTION;
        setTexture(Assets.getTexture(Assets.wall));
    }

    @Override
    public boolean canBuildCondition(Player owner) {
        return true;
    }

    @Override
    public void onCreation(Unit self) {

    }

    @Override
    public void onDeath(Unit self) {

    }

    @Override
    public void onTurnStart(Unit self) {
        self.getOwner().setMoney(self.getOwner().getMoney() + GOLDMINE_DEFAULT_PRODUCTION);
    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
