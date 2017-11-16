package io.github.jerukan.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class World {

    public static SpriteBatch batch = new SpriteBatch();

    public static OrthographicCamera boardCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    public static OrthographicCamera uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    public static ScreenViewport uiViewport = new ScreenViewport();
    public static Stage uiStage = new Stage();


    public static InputMultiplexer inputs = new InputMultiplexer();

    private static float boardCamVelX = 0;
    private static float boardCamVelY = 0;

    public static void init() {
        inputs.addProcessor(GameState.instance.uiManager.getStage());
        inputs.addProcessor(new Input(GameState.instance));
        Gdx.input.setInputProcessor(inputs);
    }

    public static void resize(int width, int height) {
        GameState.instance.boardManager.resize(width, height);
        GameState.instance.uiManager.resize(width, height);
        uiViewport.update(width, height, false);
    }

    public static void render() {
        boardCam.translate(boardCamVelX, boardCamVelY);
        boardCam.update();
        batch.setProjectionMatrix(boardCam.combined);
        GameState.instance.uiManager.updateVisibility();

        GameState.instance.boardManager.render(batch);
        GameState.instance.unitManager.render(batch);
        GameState.instance.uiManager.render();
    }

    public static void setCameraVelX(float x) {
        boardCamVelX = x;
    }

    public static void setCameraVelY(float y) {
        boardCamVelY = y;
    }

    public static void dispose() {
        batch.dispose();
    }
}
