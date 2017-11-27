package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit2 extends BaseUnit {

    public TestUnit2() {
        name = "test2";
        baseHealth = 3;
        baseAttack = 2;
        baseSpeed = 7;
        baseRange = 4;

        baseCost = 50;
        setTexture(Assets.getTexture(Assets.archer));
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

    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
