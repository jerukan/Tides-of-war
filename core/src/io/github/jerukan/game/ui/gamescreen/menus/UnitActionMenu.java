package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.game.ui.gamescreen.buttons.UnitActionButton;
import io.github.jerukan.util.Constants;

import java.util.ArrayList;

public class UnitActionMenu extends ButtonGroup {

    ArrayList<UnitActionButton> actionButtons;

    public UnitActionMenu() {
        actionButtons = new ArrayList<UnitActionButton>();
    }

    public void clearButtons() {
        actionButtons.clear();
        table.clear();
    }

    public void generateButtons(Unit selected) {
        clearButtons();
        for(UnitAction action : selected.baseunit.actions) {
            UnitActionButton button = new UnitActionButton(selected, action);
            actionButtons.add(button);
            table.add(button).pad(5).row();
        }
    }

    @Override
    public void updateVisibility() {
        if(BoardManager.getSelectedPosition().isValid()) {
            Unit selected = GameState.instance.unitState.unitFromPosition(BoardManager.getSelectedPosition());
            if(selected != null && selected.getOwner() == GameState.instance.getCurrentPlayer()) {
                if(actionButtons.size() == 0) {
                    generateButtons(GameState.instance.unitState.unitFromPosition(BoardManager.getSelectedPosition()));
                }
                WorldRenderer.boardCam.updateOffsets();
                Sprite s = GameState.instance.boardState.tileFromPosition(BoardManager.getSelectedPosition()).getSprite();
                table.setPosition(s.getX() + s.getWidth() + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.getY() - WorldRenderer.boardCam.camOffsetY);
                table.setVisible(true);
            } else {
                clearButtons();
                table.setVisible(false);
            }
        }
        else{
            clearButtons();
            table.setVisible(false);
        }
    }
}
