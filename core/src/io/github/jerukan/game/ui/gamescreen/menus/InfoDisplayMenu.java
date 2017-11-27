package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.ui.Menu;
import io.github.jerukan.util.Assets;

public class InfoDisplayMenu extends Menu {

    private final String blankTileText = "No tile selected";
    private final String noUnitText = "No unit here";

    private Label tileInfo;
    private Label unitInfo;

    public InfoDisplayMenu() {
        tileInfo = new Label(blankTileText, Assets.uiskin, "default");
        unitInfo = new Label(noUnitText, Assets.uiskin, "default");

        table.add(tileInfo).pad(10).row();
        table.add(unitInfo);

        table.setHeight(tileInfo.getHeight() + unitInfo.getHeight());

        table.setPosition(80, Gdx.graphics.getHeight() - table.getHeight() - 20);
    }

    @Override
    public void updateVisibility() {
        if(GameState.instance.boardManager.getHoveredPosition().isValid()) {
            tileInfo.setText("Hovered tile: " + GameState.instance.boardManager.getHoveredPosition().toString());
        }
        else {
            tileInfo.setText(blankTileText);
        }

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
