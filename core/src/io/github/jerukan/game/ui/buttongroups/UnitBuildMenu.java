package io.github.jerukan.game.ui.buttongroups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.game.gameunits.unitfiles.BaseUnit;
import io.github.jerukan.game.ui.UnitBuildButton;
import io.github.jerukan.game.ui.screens.Screen;
import io.github.jerukan.util.BooleanFlag;
import io.github.jerukan.util.Constants;

/** Menu that appears when a player is selecting a unit to build */

public class UnitBuildMenu extends ButtonGroup {

    public UnitBuildMenu(Screen screen, BooleanFlag[] flags) {
        super(screen, flags);
        //TODO make it automatically switch to new columns
        for(BaseUnit unit : AllUnits.list) {
            table.add(new UnitBuildButton(unit, flagFromArray("build"))).pad(5);
            table.row();
        }
    }

    @Override
    public void updateVisibility() {
        if(flagFromArray("build").getState()) {
            if(GameState.instance.unitManager.positionAvailable(GameState.instance.boardManager.getSelectedPosition())) {
                WorldRenderer.boardCam.updateOffsets();
                Sprite s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.getSelectedPosition()).getSprite();
                table.setPosition(s.getX() + s.getWidth() + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.getY() - WorldRenderer.boardCam.camOffsetY);
                table.setVisible(true);
            }
            else {
                flagFromArray("build").setState(false);
            }
        }
        else {
            table.setVisible(false);
        }
    }
}
