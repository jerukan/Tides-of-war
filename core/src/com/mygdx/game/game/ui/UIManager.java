package com.mygdx.game.game.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.game.board.screens.Screen;

/** Class that displays a single screen */
public class UIManager {

    private Stage stage;

    private OrthographicCamera camera;

    private Screen currentScreen;

    public UIManager(OrthographicCamera camera, Stage stage) {
        this.camera = camera;
        this.stage = stage;
    }

    public void init() {
        currentScreen.init();
    }

    public void update() {
    }

    public void updateVisibility() {
        currentScreen.updateVisibility();
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width , height);
    }

    public void render() {
        currentScreen.render();
    }

    public void dispose() {
        stage.dispose();
    }

    // mutators

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    // accessors

    public Stage getStage() {
        return stage;
    }
}
