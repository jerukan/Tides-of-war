package io.github.jerukan.game.gameunits.unitfiles;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class TestUnit extends BaseUnit {

    public TestUnit() {
        super("test",
                2,
                2,
                2,
                1,
                Type.SOLDIER);
        setTexture(Assets.assetManager.get(Assets.spearman));
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
