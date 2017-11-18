package io.github.jerukan.game.gameunits.unitfiles;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit3 extends BaseUnit {

    public TestUnit3() {
        super("test3", 15, 5, 4, 3, Type.BUILDING);
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
