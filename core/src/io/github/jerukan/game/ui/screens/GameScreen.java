package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.ui.buttongroups.TileSelectMenu;
import io.github.jerukan.game.ui.buttongroups.UnitActionMenu;
import io.github.jerukan.game.ui.buttongroups.UnitBuildMenu;
import io.github.jerukan.util.BooleanFlag;
import io.github.jerukan.util.Position;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;
    private UnitActionMenu unitActionMenu;

    private BooleanFlag building;

    public GameScreen(Stage stage) {
        super(stage);

        building = new BooleanFlag("build", false);
        BooleanFlag[] buildMenuListeners = {building};

        tileSelectMenu = new TileSelectMenu(this, buildMenuListeners);

        unitBuildMenu = new UnitBuildMenu(this, buildMenuListeners);

        unitActionMenu = new UnitActionMenu(this);

        addMenus(tileSelectMenu, unitBuildMenu, unitActionMenu);
    }

    @Override
    public void init() {
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
    }

    @Override
    public void clearWindows() {
        building.setState(false);
        if(tileSelectMenu.getTable().isVisible() || unitBuildMenu.getTable().isVisible() || unitActionMenu.getTable().isVisible()) {
            GameState.instance.boardManager.setSelectedPosition(new Position());
        }
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
    }
}