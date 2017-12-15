package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.GoldmineUnit;
import io.github.jerukan.game.ui.Menu;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;

public class InfoDisplayMenu extends Menu {

    private final String BLANK_TILE_TEXT = "No tile selected";
    private final String NO_UNIT_TEXT = "No unit here";

    private final float TABLE_WIDTH = 200;

    private Label tileInfo;
    private Label currentPlayerInfo;
    private Label unitInfo;

    public InfoDisplayMenu() {
        int numofmines = GameState.instance.unitState.unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> u.baseunit.name.equals("goldmine")).size();
        tileInfo = new Label(BLANK_TILE_TEXT, Assets.uiskin, "default");
        currentPlayerInfo = new Label("$" + GameState.instance.getCurrentPlayer().getMoney() + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION), Assets.uiskin, "default");
        unitInfo = new Label(NO_UNIT_TEXT, Assets.uiskin, "default");
        unitInfo.setWrap(true);

        table.add(tileInfo).pad(10).row();
        table.add(currentPlayerInfo).pad(10).row();
        table.add(unitInfo).width(TABLE_WIDTH);

        table.setWidth(TABLE_WIDTH);
        table.setHeight(tileInfo.getHeight() + currentPlayerInfo.getHeight() + unitInfo.getHeight());
        table.align(Align.topLeft);

        table.setPosition(10, Gdx.graphics.getHeight() - table.getHeight() - 20);
    }

    @Override
    public void updateVisibility() {
        //PUT SOMEWHERE ELSE LATER
        Player currentplayer = GameState.instance.getCurrentPlayer();
        int numofmines = GameState.instance.unitState.unitsFromPlayer(currentplayer, (Unit u) -> u.baseunit.name.equals("goldmine")).size();
        if(BoardManager.getHoveredPosition().isValid()) {
            tileInfo.setText("Hovered tile: " + BoardManager.getHoveredPosition().toString()
                    + "\nTerrain: " + GameState.instance.boardState.tileFromPosition(BoardManager.getHoveredPosition()).getTerrain().name
                    + "\nSpeed needed: " + GameState.instance.boardState.tileFromPosition(BoardManager.getHoveredPosition()).getTerrain().speedConsump);
        }
        else {
            tileInfo.setText(BLANK_TILE_TEXT);
        }

        currentPlayerInfo.setText("$" + currentplayer.getMoney() + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION)
                + " next turn\nCurrent upkeep: "
                + GameState.instance.unitState.totalUpkeepFromPlayer(currentplayer) + "/"
                + currentplayer.getUnitCap());

        if(GameState.instance.unitState.unitFromPosition(BoardManager.getHoveredPosition()) != null) {
            Unit u = GameState.instance.unitState.unitFromPosition(BoardManager.getHoveredPosition());
            unitInfo.setText("Unit: " + u.baseunit.name
                    + "\nOwner: " + u.getOwner().name
                    + "\nHealth: " + u.getCurrentHealth() + "/" + u.baseunit.baseHealth
                    + "\nSpeed: " + u.getCurrentSpeed() + "/" + u.baseunit.baseSpeed
                    + "\nAttack: " + u.getCurrentAttack()
                    + "\nType: " + u.baseunit.type
                    + "\n" + u.baseunit.description);
        }
        else {
            unitInfo.setText(NO_UNIT_TEXT);
        }
        table.setHeight(tileInfo.getHeight() + unitInfo.getHeight());
        table.setPosition(10, Gdx.graphics.getHeight() - table.getHeight() - 10);
    }
}
