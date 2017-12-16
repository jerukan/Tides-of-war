package io.github.jerukan.game.ui.gamescreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.ui.Screen;
import io.github.jerukan.game.ui.gamescreen.menus.*;
import io.github.jerukan.util.NamedFlag;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;
    private UnitActionMenu unitActionMenu;

    private InfoDisplayMenu infoDisplayMenu;
    private BuildInfoMenu buildInfoMenu;

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

        buildInfoMenu = new BuildInfoMenu(unitBuildMenu);

        addMenus(tileSelectMenu, unitBuildMenu, unitActionMenu, infoDisplayMenu, endTurnMenu, buildInfoMenu);
    }

    @Override
    public void init() {
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
        infoDisplayMenu.getTable().setVisible(true);
        endTurnMenu.getTable().setVisible(true);
        buildInfoMenu.getTable().setVisible(true);
    }

    @Override
    public void clearWindows() {
        building.setState(false);
        if(tileSelectMenu.getTable().isVisible() || unitBuildMenu.getTable().isVisible() || unitActionMenu.getTable().isVisible()) {
            BoardManager.getSelectedPosition().reset();
        }
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
    }
}