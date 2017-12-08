package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;

/** Bare minimum for all the UI screens */
public abstract class Screen {

    private Stage stage;
    private Menu[] menus;

    /** Classes that extend Screen have their buttons and tables initialized in the constructor
     * @param stage retrieved from UIRenderer */
    public Screen(Stage stage) {
        this.stage = stage;
    }

    public void addMenus(Menu... in) {
        menus = new Menu[in.length];
        for(int i = 0; i < in.length; i++) {
            menus[i] = in[i];
        }
        for(Menu menu : menus) {
            stage.addActor(menu.getTable());
        }
    }

    public abstract void init();

    /** Called to determine which tables are visible under conditions */
    public void updateVisibility() {
        for(Menu menu : menus) {
            menu.updateVisibility();
        }
    }

    /** Called to clear windows that aren't visible by default
     * For example, pop up windows and such */
    public abstract void clearWindows();

    public void render() {
        stage.draw();
    }
}