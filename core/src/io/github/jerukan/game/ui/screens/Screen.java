package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.HashMap;

/** Bare minimum for all the UI screens */
public abstract class Screen {

    private Stage stage;
    HashMap<String, Table> tables = new HashMap<String, Table>();

    /** Classes that extend Screen have their buttons and tables initialized in the constructor
     * @param stage retrieved from UIRenderer */
    public Screen(Stage stage) {
        this.stage = stage;
    }

    /** Called at the end of the constructors */
    public void addTablesToStage() {
        for(Table table : tables.values()) {
            stage.addActor(table);
        }
    }

    public abstract void init();

    /** Called to determine which tables are visible under conditions */
    public abstract void updateVisibility();

    public void render() {
        stage.draw();
    }
}