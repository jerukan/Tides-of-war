package io.github.jerukan.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.jerukan.game.BoardCamera;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Renderer;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Colors;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.Util;

import java.util.ArrayList;

public class BoardRenderer implements Renderer {

    private BoardManager boardManager;

    private BoardCamera camera;

    private ShapeRenderer highlighter;

    public BoardRenderer(BoardManager boardManager, BoardCamera camera) {
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
        highlighter.setProjectionMatrix(camera.getCamera().combined);
        int mousex = Gdx.input.getX();
        int mousey = Gdx.input.getY();

        batch.begin();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                boardManager.getBoard()[x][y].render(batch);
            }
        }
        batch.end();

        //highlight the selected position
        highlightPosition(boardManager.getSelectedPosition(), Colors.SELECTED_TILE_COLOR);

        for(Unit u : GameState.instance.unitManager.getUnitlist()) {
            highlightPosition(u.getPosition(), Util.colorNewAlpha(u.getOwner().color, 0.8f));
        }

        float mouseposboardx = camera.getCamera().zoom * (mousex - camera.camOriginX) + camera.getCamera().position.x;
        float mouseposboardy = camera.getCamera().zoom * (Gdx.graphics.getHeight() - mousey - camera.camOriginY) + camera.getCamera().position.y;
        int selectx = (int)(mouseposboardx / (float)Constants.TILE_SIZE);
        int selecty = (int)(mouseposboardy / (float)Constants.TILE_SIZE);

        boardManager.setHoveredPosition(new Position(selectx, selecty));

        if(boardManager.getHoveredPosition().isValid()) {
            highlightPosition(boardManager.getHoveredPosition(), Colors.HOVERED_TILE_COLOR);
            Unit dude = GameState.instance.unitManager.unitFromPosition(boardManager.getHoveredPosition());

            // highlighting moves
            if(dude != null && dude != GameState.instance.unitManager.getSelectedUnit()) {
                highlightPositions(dude.getAvailableMoves(), Colors.ATTACK_TILE_COLOR);
            }
        }

        if(GameState.instance.unitManager.getSelectedUnit() != null) {
            if(boardManager.getSelectType() == BoardManager.SelectType.ACTION) {
                UnitAction act = GameState.instance.unitManager.getSelectedUnit().getCurrentAction();
                if(act.requiresTarget) {
                    if(act.getName().equals("move")) {
                        highlightPositions(GameState.instance.unitManager.getSelectedUnit().getAvailableMoves(), Colors.MOVE_TILE_COLOR);
                    }
                    else if(act.getName().equals("attack")) {
                        highlightPositions(GameState.instance.unitManager.getSelectedUnit().getAvailableAttacks(), Colors.ATTACK_TILE_COLOR);
                    }
                }
            }
        }
    }

    @Override
    public void init() {
        highlighter.setProjectionMatrix(camera.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.getCamera().setToOrtho(false, width, height);
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
