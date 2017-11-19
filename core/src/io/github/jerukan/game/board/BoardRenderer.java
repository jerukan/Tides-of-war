package io.github.jerukan.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Renderer;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

public class BoardRenderer implements Renderer {

    private BoardManager boardManager;

    private OrthographicCamera camera;

    private float camOriginX, camOriginY;

    public float camOffsetX, camOffsetY;

    private ShapeRenderer highlighter;

    public BoardRenderer(BoardManager boardManager, OrthographicCamera camera) {
        this.boardManager = boardManager;
        highlighter = new ShapeRenderer();
        this.camera = camera;
    }

    public void highlightPosition(Position position, Color color) {
        if(position.isValid()) {
            Gdx.gl20.glEnable(GL20.GL_BLEND);
            Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            highlighter.begin(ShapeRenderer.ShapeType.Filled);
            highlighter.setColor(color);
            highlighter.rect(boardManager.getBoard()[position.getX()][position.getY()].getSprite().getX(), boardManager.getBoard()[position.getX()][position.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            highlighter.end();
            Gdx.gl20.glDisable(GL20.GL_BLEND);
        }
    }

    public void highlightPositions(ArrayList<Position> positions, Color color) {
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        for(Position pos : positions) {
            if(pos.isValid()) {
                highlighter.begin(ShapeRenderer.ShapeType.Filled);
                highlighter.setColor(color);
                highlighter.rect(boardManager.getBoard()[pos.getX()][pos.getY()].getSprite().getX(), boardManager.getBoard()[pos.getX()][pos.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
                highlighter.end();
            }
        }
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void render(Batch batch) {
        highlighter.setProjectionMatrix(camera.combined);
        int mousex = Gdx.input.getX();
        int mousey = Gdx.input.getY();

        batch.begin();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                boardManager.getBoard()[x][y].render(batch);
            }
        }
        batch.end();

        highlightPosition(boardManager.getSelectedPosition(), new Color(0.2f, 0.2f, 1, 0.4f));

        float mouseposboardx = camera.zoom * (mousex - camOriginX) + camera.position.x;
        float mouseposboardy = camera.zoom * (Gdx.graphics.getHeight() - mousey - camOriginY) + camera.position.y;
        int selectx = (int)(mouseposboardx / (float)Constants.TILE_SIZE);
        int selecty = (int)(mouseposboardy / (float)Constants.TILE_SIZE);

        boardManager.setHoveredPosition(new Position(selectx, selecty));

        if(selectx >= 0 && selectx < Constants.BOARD_WIDTH && selecty >= 0 && selecty < Constants.BOARD_HEIGHT) {
            highlightPosition(boardManager.getHoveredPosition(), new Color(0.2f, 0.2f, 1, 0.4f));
            Unit dude = GameState.instance.unitManager.unitFromPosition(boardManager.getHoveredPosition());

            // highlighting moves
            if(dude != null && dude != GameState.instance.unitManager.getSelectedUnit()) {
                highlightPositions(dude.getAvailableMoves(), new Color(1, 0.2f, 0.2f, 0.4f));
            }
        }

        if(GameState.instance.unitManager.getSelectedUnit() != null) {
            if(boardManager.getSelectType() == BoardManager.SelectType.MOVE) {
                highlightPositions(GameState.instance.unitManager.getSelectedUnit().getAvailableMoves(), new Color(0.2f, 0.2f, 1, 0.4f));
            }
            else if(boardManager.getSelectType() == BoardManager.SelectType.ATTACK) {
                highlightPositions(GameState.instance.unitManager.getSelectedUnit().getAvailableAttacks(), new Color(1, 0.2f, 0.2f, 0.4f));
            }
        }
    }

    @Override
    public void init() {
        highlighter.setProjectionMatrix(camera.combined);
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
    }

    public void updateOffsets() {
        camOffsetX = camera.position.x - camOriginX;
        camOffsetY = camera.position.y - camOriginY;
    }

    @Override
    public void resize(int width, int height) {
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
        camera.setToOrtho(false, width, height);
    }


    public void dispose() {
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                boardManager.getBoard()[x][y].dispose();
            }
        }
        highlighter.dispose();
    }
}
