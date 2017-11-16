package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.ui.buttongroups.TileSelectMenu;
import io.github.jerukan.game.ui.buttongroups.UnitBuildMenu;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;

    public GameScreen(Stage stage) {
        super(stage);

        tileSelectMenu = new TileSelectMenu();
        tables.put("tileSelectionTable", tileSelectMenu.getTable());

        unitBuildMenu = new UnitBuildMenu();
        tables.put("unitsTable", unitBuildMenu.getTable());

        addTablesToStage();
    }

    @Override
    public void init() {
        tables.get("tileSelectionTable").setVisible(false);
        tables.get("unitsTable").setVisible(false);
    }

    @Override
    public void updateVisibility() {
        tileSelectMenu.updateVisibility();
        unitBuildMenu.updateVisibility();
    }
}