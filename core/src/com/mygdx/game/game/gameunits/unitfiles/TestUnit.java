package com.mygdx.game.game.gameunits.unitfiles;

import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.Assets;

public class TestUnit extends BaseUnit {

    public TestUnit() {
        super("test", 2, 2, 2, 1, Type.SOLDIER);
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
