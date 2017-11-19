package io.github.jerukan.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.jerukan.game.board.BoardRenderer;
import io.github.jerukan.game.gameunits.UnitRenderer;
import io.github.jerukan.game.ui.UIRenderer;
import io.github.jerukan.game.ui.screens.GameScreen;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Input;

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

    private static float boardCamAccelX = 0;
    private static float boardCamAccelY = 0;

    public static boolean boardCamSlowingX = false;
    public static boolean boardCamSlowingY = false;

    private static float boardCamZoom = 0;

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
        boardCamUpdate();
        boardCam.translate(boardCamVelX, boardCamVelY);
        boardCam.zoom += boardCamZoom;

        boardCam.zoom = MathUtils.clamp(boardCam.zoom, 0.3f, 1);
/*
        float effectiveViewportWidth = boardCam.viewportWidth * boardCam.zoom;
        float effectiveViewportHeight = boardCam.viewportHeight * boardCam.zoom;

        boardCam.position.x = MathUtils.clamp(boardCam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        boardCam.position.y = MathUtils.clamp(boardCam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
        */

        boardCam.update();
        boardRenderer.updateOffsets();
        batch.setProjectionMatrix(boardCam.combined);
        uiRenderer.updateVisibility();

        boardRenderer.render(batch);
        unitRenderer.render(batch);
        uiRenderer.render(batch);
    }

    //BOARD CAMERA SHIT THAT SHOULD PROBABLY BE PUT SOMEWHERE ELSE
    //----------------------------------------------------------------//
    public static void boardCamUpdate() {
        if(boardCamSlowingX || boardCamSlowingY) {
            slowCamera();
        }
        setCameraVelX();
        setCameraVelY();
    }

    public static void setCameraVelX() {
        if(Math.abs(boardCamVelX) > Constants.CAMERA_SPEED_MAX) {
            MathUtils.clamp(boardCamVelX, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX);
        }
        else {
            boardCamVelX += boardCamAccelX;
        }
    }


    public static void setCameraVelY() {
        if(Math.abs(boardCamVelY) > Constants.CAMERA_SPEED_MAX) {
            MathUtils.clamp(boardCamVelY, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX);
        }
        else {
            boardCamVelY += boardCamAccelY;
        }
    }

    public static void setBoardCamAccelX(float x) {
        boardCamAccelX = x;
    }

    public static void setBoardCamAccelY(float y) {
        boardCamAccelY = y;
    }

    public static void slowCamera() {
        float xdir = Math.abs(boardCamVelX) / boardCamVelX;
        float ydir = Math.abs(boardCamVelY) / boardCamVelY;
        if(boardCamAccelX == 0) {
            if (!(Math.abs(boardCamVelX) < Constants.CAMERA_ZERO_THRESHOLD)) {
                boardCamVelX -= Constants.CAMERA_SPEED_ACCEL * xdir;
            }
            else {
                boardCamVelX = 0;
            }
        }
        if(boardCamAccelY == 0) {
            if (!(Math.abs(boardCamVelY) < Constants.CAMERA_ZERO_THRESHOLD)) {
                boardCamVelY -= Constants.CAMERA_SPEED_ACCEL * ydir;
            }
            else {
                boardCamVelY = 0;
            }
        }
    }

    public static void setBoardCamZoom(float vel) {
        boardCamZoom = vel;
    }

    //------------------------------------------------------------------//

    public static void dispose() {
        batch.dispose();
        boardRenderer.dispose();
        uiRenderer.dispose();
    }
}
