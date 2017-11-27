package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Position;

public class VillageUnit extends BaseUnit {

    public VillageUnit() {
        name = "village";
        baseHealth = 20;

        baseCost = 0;

        actions = new UnitAction[]{};
        type = Type.BUILDING;
        setTexture(Assets.getTexture(Assets.village));
    }

    @Override
    public boolean canBuild(Position pos, Player owner) {
        return canBuildCondition(owner);
    }

    @Override
    public boolean canBuildCondition(Player owner) {
        return !GameState.instance.unitManager.playerHasUnit(owner, name);
    }

    @Override
    public void onCreation(Unit self) {

    }

    @Override
    public void onDeath(Unit self) {

    }

    @Override
    public void onTurnStart(Unit self) {

    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
