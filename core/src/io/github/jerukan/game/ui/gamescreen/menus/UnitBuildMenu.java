package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.UnitRegistry;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.game.ui.gamescreen.buttons.UnitBuildButton;
import io.github.jerukan.util.NamedFlag;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

/** Menu that appears when a player is selecting a unit to build */

public class UnitBuildMenu extends ButtonGroup {

    private Position currentPos;
    private ArrayList<UnitBuildButton> unitButtons;

    public UnitBuildMenu(NamedFlag[] flags) {
        super(flags);
        currentPos = new Position();
        table.align(Align.bottomLeft);
        unitButtons = new ArrayList<>();
        for(BaseUnit unit : UnitRegistry.unitList) {
            unitButtons.add(new UnitBuildButton(unit, flagFromArray("build")));
        }
        resetTable();
    }

    public void resetTable() {
        table.clear();
        for(UnitBuildButton button : unitButtons) {
            if(button.getBaseUnit()._canBuild(BoardManager.getSelectedPosition(), GameState.instance.getCurrentPlayer())) {
                table.add(button).pad(5).row();
            }
        }
    }

    public UnitBuildButton getHoveredButton() {
        for(UnitBuildButton button : unitButtons) {
            if(button.isHovered()) {
                return button;
            }
        }
        return null;
    }

    @Override
    public void updateVisibility() {
        if(!currentPos.equals(BoardManager.getSelectedPosition())) {
            flagFromArray("build").setState(false);
            currentPos.setPos(BoardManager.getSelectedPosition());
        }
        if(flagFromArray("build").getState()) {
            if(BoardManager.getSelectedPosition().isValid()) {
                if (GameState.instance.unitState.positionAvailable(BoardManager.getSelectedPosition())) {
                    WorldRenderer.boardCam.updateOffsets();
                    Sprite s = GameState.instance.boardState.tileFromPosition(BoardManager.getSelectedPosition()).getSprite();
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
