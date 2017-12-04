package io.github.jerukan.game.ui.gamescreen.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;

public class UnitActionButton extends TextButton {

    public UnitActionButton(final Unit self, final UnitAction action) {
        super(action.getName(), Assets.uiskin, "default");
        super.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(self.hasSufficientSpeed(action.getSpeedConsumption())) {
                    if (action.requiresTarget) {
                        self.setCurrentAction(action);
                        action.generateTargets(self);
                        self.setAvailableTargets(action.getAvailableTargets());
                        self.setTargetSpeedConsumptions(action.getTargetSpeedConsumptions());
                        GameState.instance.boardManager.setSelectType(BoardManager.SelectType.ACTION);
                    } else {
                        action.execute(self);
                    }
                }
                GameState.instance.update();
            }
        });
    }
}