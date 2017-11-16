package io.github.jerukan.game.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.jerukan.game.Renderer;
import io.github.jerukan.game.ui.screens.Screen;

/** Class that manages different game screens
 * Also handles displaying them for now */
public class UIRenderer implements Renderer {

    private Stage stage;

    private OrthographicCamera camera;

    private Screen currentScreen;

    public UIRenderer(OrthographicCamera camera, Stage stage) {
        this.camera = camera;
        this.stage = stage;
    }

    @Override
    public void init() {
        currentScreen.init();
    }

    public void updateVisibility() {
        currentScreen.updateVisibility();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width , height);
    }

    @Override
    public void render(Batch batch) {
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
