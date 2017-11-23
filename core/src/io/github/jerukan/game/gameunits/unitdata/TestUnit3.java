package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit3 extends BaseUnit {

    public TestUnit3() {
        name = "test3";
        baseHealth = 1;
        baseAttack = 1;
        baseSpeed = 1;
        baseRange = 9;
        type = Type.BUILDING;
        setTexture(Assets.getTexture(Assets.wall));
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
