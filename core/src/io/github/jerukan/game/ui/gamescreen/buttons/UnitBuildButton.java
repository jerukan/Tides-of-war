package io.github.jerukan.game.ui.gamescreen.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.NamedFlag;

public class UnitBuildButton extends TextButton {

    private BaseUnit baseUnit;

    private boolean hovered;

    public UnitBuildButton(final BaseUnit baseUnit, final NamedFlag buildFlag) {
        super(baseUnit.buttonDisplayString(), Assets.uiskin, "default");
        this.baseUnit = baseUnit;
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.instance.unitManager.buildUnit(baseUnit);
                buildFlag.setState(false);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hovered = true;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hovered = false;
            }
        });
    }

    public boolean isHovered() {
        return isOver();
    }

    public BaseUnit getBaseUnit() {
        return baseUnit;
    }
}
