package com.mygdx.game.game.gameunits.unitfiles;

import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.SpriteManager;

public class TestUnit extends BaseUnit {

    public TestUnit() {
        super("test", 2, 2, 2, 2, Type.SOLDIER);
        setTexture(SpriteManager.assetManager.get(SpriteManager.spearman));
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
