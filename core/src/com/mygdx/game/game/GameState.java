package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.game.board.BoardManager;
import com.mygdx.game.game.gameunits.UnitManager;
import com.mygdx.game.game.ui.UIManager;
import com.mygdx.game.util.SpriteManager;

public class GameState {

    public BoardManager boardManager;
    public UIManager uiManager;
    public UnitManager unitManager;

    public OrthographicCamera boardCam;
    public OrthographicCamera uiCam;

    public InputMultiplexer inputs;

    private float boardCamX;
    private float boardCamY;

    public GameState() {
        boardCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        boardManager = new BoardManager(boardCam);
        uiManager = new UIManager(uiCam);
        unitManager = new UnitManager();

        inputs = new InputMultiplexer();
    }

    public void init() {
        uiManager.init();
        boardManager.init();
    }

    public void reset() {
        boardManager.resetBoard();
    }

    public void render(Batch batch) {
        boardCam.translate(boardCamX, boardCamY);
        boardCam.update();
        batch.setProjectionMatrix(boardCam.combined);

        batch.begin();
        boardManager.render(batch);
        batch.end();
        uiManager.render();
    }

    public void setCameraX(float x) {
        boardCamX = x;
    }

    public void setCameraY(float y) {
        boardCamY = y;
    }

    public void dispose() {
        uiManager.dispose();
        boardManager.dispose();
    }
}
