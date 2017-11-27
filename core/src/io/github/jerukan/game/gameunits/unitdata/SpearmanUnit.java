package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class SpearmanUnit extends BaseUnit {

    public SpearmanUnit() {
        name = "spearman";
        baseHealth = 3;
        baseAttack = 2;
        baseSpeed = 3;
        baseRange = 1;

        baseCost = 200;

        description = "A reliable fast unit that may or may not be broken.";

        type = Type.SOLDIER;

        setTexture(Assets.getTexture(Assets.spearman));
    }

    @Override
    public boolean canBuildCondition(Player owner) {
        return GameState.instance.unitManager.playerHasUnit(owner, "armory");
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
