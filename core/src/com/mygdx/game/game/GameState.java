package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.game.board.BoardManager;
import com.mygdx.game.game.ui.screens.GameScreen;
import com.mygdx.game.game.gameunits.AllUnits;
import com.mygdx.game.game.gameunits.UnitManager;
import com.mygdx.game.game.ui.UIManager;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Position;

public class GameState {

    public static GameState instance;

    public SpriteBatch batch;

    public BoardManager boardManager;
    public UIManager uiManager;
    public UnitManager unitManager;

    public OrthographicCamera boardCam;
    public OrthographicCamera uiCam;

    public Stage UIStage;

    public ScreenViewport UIViewport;

    public InputMultiplexer inputs;

    private float boardCamX;
    private float boardCamY;

    public GameState() {
        batch = new SpriteBatch();

        boardCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        UIViewport = new ScreenViewport(uiCam);
        UIStage = new Stage(UIViewport);

        boardManager = new BoardManager(boardCam);
        uiManager = new UIManager(uiCam, UIStage);
        unitManager = new UnitManager();

        Assets.load();
        Assets.assetManager.finishLoading();

        inputs = new InputMultiplexer();
    }

    public void init() {
        uiManager.setCurrentScreen(new GameScreen(UIStage));

        uiManager.init();
        boardManager.init();
    }

    public void reset() {
        boardManager.resetBoard();
        unitManager.clearUnits();
        unitManager.addUnit(AllUnits.getUnit("test"), null, new Position(3, 5));
        unitManager.generateUnitMoves();
    }

    public void resize(int width, int height) {
        boardManager.resize(width, height);
        uiManager.resize(width, height);
        UIViewport.update(width, height, false);
    }

    public void update() {
        boardManager.updateOffsets();
        uiManager.update();
    }

    public void render() {
        boardCam.translate(boardCamX, boardCamY);
        boardCam.update();
        batch.setProjectionMatrix(boardCam.combined);
        uiManager.updateVisibility();

        update();

        boardManager.render(batch);
        unitManager.render(batch);
        uiManager.render();
    }

    public void setCameraX(float x) {
        boardCamX = x;
    }

    public void setCameraY(float y) {
        boardCamY = y;
    }

    public void dispose() {
        batch.dispose();
        uiManager.dispose();
        boardManager.dispose();
    }
}