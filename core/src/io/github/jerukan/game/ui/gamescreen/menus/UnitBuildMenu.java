package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.gameunits.UnitRegistry;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.game.ui.gamescreen.buttons.UnitBuildButton;
import io.github.jerukan.util.BooleanFlag;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

/** Menu that appears when a player is selecting a unit to build */

public class UnitBuildMenu extends ButtonGroup {

    private Position currentPos;

    public UnitBuildMenu(BooleanFlag[] flags) {
        super(flags);
        currentPos = new Position();
        table.align(Align.bottomLeft);
        resetTable();
    }

    public void resetTable() {
        table.clear();
        for(BaseUnit unit : UnitRegistry.list) {
            if(unit.canBuild(GameState.instance.boardManager.getSelectedPosition(), GameState.instance.getCurrentPlayer())) {
                table.add(new UnitBuildButton(unit, flagFromArray("build"))).pad(5).row();
            }
        }
    }

    @Override
    public void updateVisibility() {
        if(!currentPos.equals(GameState.instance.boardManager.getSelectedPosition())) {
            flagFromArray("build").setState(false);
            currentPos.setPos(GameState.instance.boardManager.getSelectedPosition());
        }
        if(flagFromArray("build").getState()) {
            if(GameState.instance.boardManager.getSelectedPosition().isValid()) {
                if (GameState.instance.unitManager.positionAvailable(GameState.instance.boardManager.getSelectedPosition())) {
                    WorldRenderer.boardCam.updateOffsets();
                    Sprite s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.getSelectedPosition()).getSprite();
                    table.setPosition(s.getX() + s.getWidth() + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.getY() - WorldRenderer.boardCam.camOffsetY);
                    table.setVisible(true);
                }
                else {
                    flagFromArray("build").setState(false);
                }
            }
        }
        else {
            resetTable();
            table.setVisible(false);
        }
    }
}
