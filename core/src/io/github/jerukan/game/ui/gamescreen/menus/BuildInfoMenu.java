package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.game.ui.gamescreen.buttons.UnitBuildButton;
import io.github.jerukan.util.Assets;

public class BuildInfoMenu extends ButtonGroup {

    private UnitBuildMenu unitMenu;

    private Label unitInfo;

    public BuildInfoMenu(UnitBuildMenu unitMenu) {
        this.unitMenu = unitMenu;
        unitInfo = new Label("", Assets.uiskin, "default");
        unitInfo.setWrap(true);

        //table.setWidth(120);
        table.add(unitInfo).width(160);
        table.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100);
    }

    @Override
    public void updateVisibility() {
        UnitBuildButton button = unitMenu.getHoveredButton();
        if(button != null) {
            unitInfo.setText("Unit: " + button.getBaseUnit().name
                    + "\nHealth: " + button.getBaseUnit().baseHealth
                    + "\nSpeed: " + button.getBaseUnit().baseSpeed
                    + "\nAttack: " + button.getBaseUnit().baseAttack
                    + "\nType: " + button.getBaseUnit().type
                    + "\n" + button.getBaseUnit().description);
        }
        else {
            unitInfo.setText("");
        }
    }
}
