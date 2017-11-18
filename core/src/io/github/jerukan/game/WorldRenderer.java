package io.github.jerukan.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.jerukan.game.board.BoardRenderer;
import io.github.jerukan.game.gameunits.UnitRenderer;
import io.github.jerukan.game.ui.UIRenderer;
import io.github.jerukan.game.ui.screens.GameScreen;

/** Handles all the renderers and graphics related objects */

public class WorldRenderer {

    public static SpriteBatch batch = new SpriteBatch();

    public static OrthographicCamera boardCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    public static OrthographicCamera uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    public static ScreenViewport uiViewport = new ScreenViewport();
    public static Stage uiStage = new Stage();


    public static InputMultiplexer inputs = new InputMultiplexer();

    private static float boardCamVelX = 0;
    private static float boardCamVelY = 0;

    public static BoardRenderer boardRenderer = new BoardRenderer(GameState.instance.boardManager, boardCam);
    public static UIRenderer uiRenderer = new UIRenderer(uiCam, uiStage);
    public static UnitRenderer unitRenderer = new UnitRenderer(GameState.instance.unitManager);

    public static void init() {
        inputs.addProcessor(uiRenderer.getStage());
        inputs.addProcessor(new Input(GameState.instance));
        Gdx.input.setInputProcessor(inputs);
        boardRenderer.init();
        uiRenderer.setCurrentScreen(new GameScreen(uiStage));
        uiRenderer.init();
    }

    public static void resize(int width, int height) {
        boardRenderer.resize(width, height);
        uiRenderer.resize(width, height);
        uiViewport.update(width, height, false);
    }

    public static void render() {
        boardCam.translate(boardCamVelX, boardCamVelY);
        boardCam.update();
        boardRenderer.updateOffsets();
        batch.setProjectionMatrix(boardCam.combined);
        uiRenderer.updateVisibility();

        boardRenderer.render(batch);
        unitRenderer.render(batch);
        uiRenderer.render(batch);
    }

    public static void setCameraVelX(float x) {
        boardCamVelX = x;
    }

    public static void setCameraVelY(float y) {
        boardCamVelY = y;
    }

    public static void dispose() {
        batch.dispose();
        boardRenderer.dispose();
        uiRenderer.dispose();
    }
}
