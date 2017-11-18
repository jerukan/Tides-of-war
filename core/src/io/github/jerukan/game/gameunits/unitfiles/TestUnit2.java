package io.github.jerukan.game.gameunits.unitfiles;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit2 extends BaseUnit {

    public TestUnit2() {
        super("test2", 5, 4, 6, 4, Type.SOLDIER);
        setTexture(Assets.getTexture(Assets.archer));
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
