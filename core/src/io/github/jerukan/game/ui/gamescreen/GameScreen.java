package io.github.jerukan.game.ui.gamescreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.ui.Screen;
import io.github.jerukan.game.ui.gamescreen.menus.*;
import io.github.jerukan.util.NamedFlag;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;
    private UnitActionMenu unitActionMenu;

    private InfoDisplayMenu infoDisplayMenu;

    private EndTurnMenu endTurnMenu;

    private NamedFlag building;

    public GameScreen(Stage stage) {
        super(stage);

        building = new NamedFlag("build", false);
        NamedFlag[] buildMenuListeners = {building};

        tileSelectMenu = new TileSelectMenu(buildMenuListeners);

        unitBuildMenu = new UnitBuildMenu(buildMenuListeners);

        unitActionMenu = new UnitActionMenu();

        infoDisplayMenu = new InfoDisplayMenu();

        endTurnMenu = new EndTurnMenu();

        addMenus(tileSelectMenu, unitBuildMenu, unitActionMenu, infoDisplayMenu, endTurnMenu);
    }

    @Override
    public void init() {
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
        infoDisplayMenu.getTable().setVisible(true);
        endTurnMenu.getTable().setVisible(true);
    }

    @Override
    public void clearWindows() {
        building.setState(false);
        if(tileSelectMenu.getTable().isVisible() || unitBuildMenu.getTable().isVisible() || unitActionMenu.getTable().isVisible()) {
            GameState.instance.boardManager.getSelectedPosition().reset();
        }
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
    }
}