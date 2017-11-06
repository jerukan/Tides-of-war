package com.mygdx.game.game.gameunits.unitfiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.game.gameunits.BaseUnit;
import com.mygdx.game.game.gameunits.Unit;

public class TestUnit extends BaseUnit {

    public TestUnit() {
        super("cool test unit", 2, 2, 2, 2);
        setSprite(new Texture("units/spearman.bmp"));
    }

    @Override
    public void onTargetAction(Unit target) {

    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void onTurnStart() {

    }

    @Override
    public void onTurnEnd() {

    }
}
