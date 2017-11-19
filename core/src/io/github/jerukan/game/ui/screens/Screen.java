package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.jerukan.game.ui.buttongroups.ButtonGroup;

import java.util.HashMap;

/** Bare minimum for all the UI screens */
public abstract class Screen {

    private Stage stage;
    HashMap<String, Table> tables = new HashMap<String, Table>();
    ButtonGroup[] menus;

    /** Classes that extend Screen have their buttons and tables initialized in the constructor
     * @param stage retrieved from UIRenderer */
    public Screen(Stage stage) {
        this.stage = stage;
    }

    public void addMenus(ButtonGroup... in) {
        menus = new ButtonGroup[in.length];
        for(int i = 0; i < in.length; i++) {
            menus[i] = in[i];
        }
        addTablesToStage();
    }

    public void addTablesToStage() {
        for(ButtonGroup menu : menus) {
            stage.addActor(menu.getTable());
        }
    }

    public abstract void init();

    /** Called to determine which tables are visible under conditions */
    public abstract void updateVisibility();

    /** Called to clear windows that aren't visible by default
     * For example, pop up windows and such */
    public abstract void clearWindows();

    public void render() {
        stage.draw();
    }
}