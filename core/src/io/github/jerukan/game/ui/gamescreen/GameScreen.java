package io.github.jerukan.game.ui.gamescreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.ui.Screen;
import io.github.jerukan.game.ui.gamescreen.menus.InfoDisplayMenu;
import io.github.jerukan.game.ui.gamescreen.menus.TileSelectMenu;
import io.github.jerukan.game.ui.gamescreen.menus.UnitActionMenu;
import io.github.jerukan.game.ui.gamescreen.menus.UnitBuildMenu;
import io.github.jerukan.util.BooleanFlag;
import io.github.jerukan.util.Position;

public class GameScreen extends Screen {

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;
    private UnitActionMenu unitActionMenu;

    private InfoDisplayMenu infoDisplayMenu;

    private BooleanFlag building;

    public GameScreen(Stage stage) {
        super(stage);

        building = new BooleanFlag("build", false);
        BooleanFlag[] buildMenuListeners = {building};

        tileSelectMenu = new TileSelectMenu(buildMenuListeners);

        unitBuildMenu = new UnitBuildMenu(buildMenuListeners);

        unitActionMenu = new UnitActionMenu();

        infoDisplayMenu = new InfoDisplayMenu();

        addMenus(tileSelectMenu, unitBuildMenu, unitActionMenu, infoDisplayMenu);
    }

    @Override
    public void init() {
        tileSelectMenu.getTable().setVisible(false);
        unitBuildMenu.getTable().setVisible(false);
        unitActionMenu.getTable().setVisible(false);
        infoDisplayMenu.getTable().setVisible(true);
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