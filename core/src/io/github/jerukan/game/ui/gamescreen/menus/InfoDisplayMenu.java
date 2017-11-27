package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.GoldmineUnit;
import io.github.jerukan.game.ui.Menu;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;

public class InfoDisplayMenu extends Menu {

    private final String blankTileText = "No tile selected";
    private final String noUnitText = "No unit here";

    private Label tileInfo;
    private Label currentPlayerInfo;
    private Label unitInfo;

    public InfoDisplayMenu() {
        int numofmines = GameState.instance.unitManager.unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> u.baseunit.name.equals("goldmine")).size();
        tileInfo = new Label(blankTileText, Assets.uiskin, "default");
        currentPlayerInfo = new Label("$" + GameState.instance.getCurrentPlayer().getMoney() + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION), Assets.uiskin, "default");
        unitInfo = new Label(noUnitText, Assets.uiskin, "default");

        table.add(tileInfo).pad(10).row();
        table.add(currentPlayerInfo).row();
        table.add(unitInfo);

        table.setHeight(tileInfo.getHeight() + unitInfo.getHeight());

        table.setPosition(80, Gdx.graphics.getHeight() - table.getHeight() - 20);
    }

    @Override
    public void updateVisibility() {
        //PUT SOMEWHERE ELSE LATER
        int numofmines = GameState.instance.unitManager.unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> u.baseunit.name.equals("goldmine")).size();
        if(GameState.instance.boardManager.getHoveredPosition().isValid()) {
            tileInfo.setText("Hovered tile: " + GameState.instance.boardManager.getHoveredPosition().toString());
        }
        else {
            tileInfo.setText(blankTileText);
        }

        currentPlayerInfo.setText("$" + GameState.instance.getCurrentPlayer().getMoney() + " + $"
                + (numofmines * GoldmineUnit.GOLDMINE_DEFAULT_PRODUCTION + Constants.DEFAULT_MONEY_PRODUCTION));

        if(GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getHoveredPosition()) != null) {
            //lmao
            Unit u = GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getHoveredPosition());
            unitInfo.setText("Unit: " + u.baseunit.name + "\nOwner: " + u.getOwner().name
                    + "\nHealth: " + u.getCurrentHealth() + "/" + u.baseunit.baseHealth
                    + "\nSpeed: " + u.getCurrentSpeed() + "/" + u.baseunit.baseSpeed);
        }
        else {
            unitInfo.setText(noUnitText);
        }
        table.setHeight(tileInfo.getHeight() + unitInfo.getHeight());
        table.setPosition(80, Gdx.graphics.getHeight() - table.getHeight() - 20);
    }
}
