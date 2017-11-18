package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.ui.buttongroups.TileSelectMenu;
import io.github.jerukan.game.ui.buttongroups.UnitBuildMenu;
import io.github.jerukan.util.BooleanFlag;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;

    private BooleanFlag building;

    public GameScreen(Stage stage) {
        super(stage);

        building = new BooleanFlag("build", false);
        BooleanFlag[] buildMenuListeners = {building};

        tileSelectMenu = new TileSelectMenu(this, buildMenuListeners);
        tables.put("tileSelectionTable", tileSelectMenu.getTable());

        unitBuildMenu = new UnitBuildMenu(this, buildMenuListeners);
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