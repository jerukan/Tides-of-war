package io.github.jerukan.game.ui.gamescreen.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.BooleanFlag;
import io.github.jerukan.util.Position;

public class UnitBuildButton extends TextButton {

    private BaseUnit baseUnit;

    public UnitBuildButton(final BaseUnit baseUnit, final BooleanFlag buildFlag) {
        super(baseUnit.buttonDisplayString(), Assets.uiskin, "default");
        this.baseUnit = baseUnit;
        super.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(baseUnit.canBuild(GameState.instance.boardManager.getSelectedPosition(), GameState.instance.getCurrentPlayer())) {
                    GameState.instance.unitManager.addUnit(baseUnit, GameState.instance.getCurrentPlayer(), new Position(GameState.instance.boardManager.getSelectedPosition()));
                    GameState.instance.unitManager.setSelectedToLast();
                    GameState.instance.unitManager.getSelectedUnit().onCreation();
                    GameState.instance.unitManager.generateUnitMoves();
                    GameState.instance.boardManager.updateAvailableBuildPositions();
                }
                buildFlag.setState(false);
            }
        });
    }

    public BaseUnit getBaseUnit() {
        return baseUnit;
    }
}
