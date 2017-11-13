package com.mygdx.game.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Position;
import com.mygdx.game.util.Assets;

import java.util.ArrayList;

/** A class managing most elements of the board, namely tiles */

public class BoardManager {

    private OrthographicCamera camera;

    private ShapeRenderer highlighter;

    private float camOriginX, camOriginY;

    public float camOffsetX, camOffsetY;

    private Tile[][] board;

    private Position hoveredPosition;
    private Position selectedPosition;

    private SelectType selectType;

    public BoardManager(OrthographicCamera camera) {
        highlighter = new ShapeRenderer();
        hoveredPosition = new Position();
        selectedPosition = new Position();
        this.camera = camera;
        selectType = SelectType.SELECT;
    }

    public Tile[][] getBoard() {
        return board;
    }

    /** Sets the board position of the currently hovered tile
     * @param x x position on the board
     * @param y y position on the board */
    public void setHoveredPosition(int x, int y) {
        if(x >= 0 && x < Constants.BOARD_WIDTH && y >= 0 && y < Constants.BOARD_HEIGHT) {
            hoveredPosition.setPos(x, y);
        }
        else {
            hoveredPosition.setPos(-1, -1);
        }
    }

    public Position getHoveredPosition() {
        return hoveredPosition;
    }

    public void selectedPositionAction() {
        selectedPosition = new Position(hoveredPosition);
        if(selectType == SelectType.SELECT) {
            GameState.instance.unitManager.setSelectedUnit(GameState.instance.unitManager.unitFromPosition(selectedPosition));
        }
        else if(selectType == SelectType.MOVE) {
            GameState.instance.unitManager.getSelectedUnit().move(selectedPosition);
            selectType = SelectType.SELECT;
            //selectedPosition.setPos(-1, -1);
        }
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectType(SelectType selectType) {
        this.selectType = selectType;
    }

    public Tile tileFromPosition(Position pos) {
        if(pos.isValid()) {
            return board[pos.getX()][pos.getY()];
        }
        else {
            return null;
        }
    }

    public void resetBoard() {
        board = new Tile[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                Position pos = new Position(x, y);
                board[x][y] = new Tile(pos, new Sprite(Assets.assetManager.get(Assets.grass1)));
                board[x][y].setSpritePosition(Constants.TILE_SIZE * x, Constants.TILE_SIZE * y);
            }
        }
    }

    public void highlightPosition(Position position, Color color) {
        if(position.isValid()) {
            Gdx.gl20.glEnable(GL20.GL_BLEND);
            Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            highlighter.begin(ShapeType.Filled);
            highlighter.setColor(color);
            highlighter.rect(board[position.getX()][position.getY()].getSprite().getX(), board[position.getX()][position.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            highlighter.end();
            Gdx.gl20.glDisable(GL20.GL_BLEND);
        }
    }

    public void highlightPositions(ArrayList<Position> positions, Color color) {
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        for(Position pos : positions) {
            if(pos.isValid()) {
                highlighter.begin(ShapeType.Filled);
                highlighter.setColor(color);
                highlighter.rect(board[pos.getX()][pos.getY()].getSprite().getX(), board[pos.getX()][pos.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
                highlighter.end();
            }
        }
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    public void init() {
        highlighter.setProjectionMatrix(camera.combined);
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
    }

    public void updateOffsets() {
        camOffsetX = camera.position.x - camOriginX;
        camOffsetY = camera.position.y - camOriginY;
    }

    public void resize(int width, int height) {
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
        camera.setToOrtho(false, width, height);
    }

    public void render(Batch batch) {
        highlighter.setProjectionMatrix(camera.combined);
        int mousex = Gdx.input.getX();
        int mousey = Gdx.input.getY();

        batch.begin();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                board[x][y].render(batch);
            }
        }
        batch.end();

        highlightPosition(selectedPosition, new Color(0.2f, 0.2f, 1, 0.4f));

        int selectx = (int)(mousex + camera.position.x - camOriginX) / Constants.TILE_SIZE;
        int selecty = (int)(Gdx.graphics.getHeight() - mousey + camera.position.y - camOriginY) / Constants.TILE_SIZE;

        setHoveredPosition(selectx, selecty);

        if(selectx >= 0 && selectx < Constants.BOARD_WIDTH && selecty >= 0 && selecty < Constants.BOARD_HEIGHT) {
            highlightPosition(hoveredPosition, new Color(0.2f, 0.2f, 1, 0.4f));
            Unit dude = GameState.instance.unitManager.unitFromPosition(hoveredPosition);

            // highlighting moves
            if(dude != null && dude != GameState.instance.unitManager.getSelectedUnit()) {
                highlightPositions(dude.getAvailableMoves(), new Color(1, 0.2f, 0.2f, 0.4f));
            }
        }

        if(GameState.instance.unitManager.getSelectedUnit() != null && selectType == SelectType.MOVE) {
            highlightPositions(GameState.instance.unitManager.getSelectedUnit().getAvailableMoves(), new Color(0.2f, 0.2f, 1, 0.4f));
        }
    }

    public void dispose() {
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                board[x][y].dispose();
            }
        }
        highlighter.dispose();
    }

    public enum SelectType {
        SELECT,
        MOVE,
        ATTACK
    }
}
