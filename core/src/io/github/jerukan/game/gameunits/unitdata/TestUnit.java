package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit extends BaseUnit {

    public TestUnit() {
        name = "test1";
        baseHealth = 10;
        baseAttack = 500;
        baseSpeed = 3;
        baseRange = 4;

        baseCost = 200;
        setTexture(Assets.getTexture(Assets.spearman));
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
