package com.mygdx.game.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.gameunits.AllUnits;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Position;

public class UnitBuildButton extends TextButton {

    private BaseUnit baseUnit;

    public UnitBuildButton(final BaseUnit baseUnit) {
        super(baseUnit.name, Assets.uiskin, "default");
        this.baseUnit = baseUnit;
        super.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.instance.unitManager.addUnit(baseUnit, null, new Position(GameState.instance.boardManager.getSelectedPosition()));
                GameState.instance.unitManager.setSelectedToLast();
                GameState.instance.unitManager.generateUnitMoves();
            }
        });
    }

    public BaseUnit getBaseUnit() {
        return baseUnit;
    }
}
