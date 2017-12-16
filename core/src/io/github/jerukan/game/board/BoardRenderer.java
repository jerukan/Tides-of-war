package io.github.jerukan.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import java.util.List;

public class BoardRenderer implements Renderer {

    private BoardState boardState;

    private BoardCamera camera;

    private ShapeRenderer highlighter;

    public BoardRenderer(BoardState boardState, BoardCamera camera) {
        this.boardState = boardState;
        highlighter = new ShapeRenderer();
        highlighter.setAutoShapeType(true);
        this.camera = camera;
    }

    public void highlightPosition(Position position, Color color, ShapeRenderer.ShapeType shapeType) {
        highlighter.set(shapeType);
        highlighter.setColor(color);
        if(position.isValid()) {
            highlighter.rect(boardState.getBoard()[position.getX()][position.getY()].getSprite().getX(), boardState.getBoard()[position.getX()][position.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
        }
    }

    public void highlightPositions(List<Position> positions, Color color, ShapeRenderer.ShapeType shapeType) {
        highlighter.set(shapeType);
        highlighter.setColor(color);
        for(Position pos : positions) {
            if(pos.isValid()) {
                highlighter.rect(boardState.getBoard()[pos.getX()][pos.getY()].getSprite().getX(), boardState.getBoard()[pos.getX()][pos.getY()].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }
    }

    @Override
    public void render(Batch batch) {

        int mousex = Gdx.input.getX();
        int mousey = Gdx.input.getY();

        batch.begin();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                boardState.getBoard()[x][y].render(batch);
            }
        }
        batch.end();

        highlighter.setProjectionMatrix(camera.getCamera().combined);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        highlighter.begin();

        //highlight the selected position
        highlightPosition(BoardManager.getSelectedPosition(), Colors.SELECTED_TILE_COLOR, ShapeRenderer.ShapeType.Filled);

        for(Unit u : GameState.instance.unitState.getUnitlist()) {
            highlightPosition(u.getPosition(), Util.colorNewAlpha(u.getOwner().color, 0.8f), ShapeRenderer.ShapeType.Filled);
        }

        highlightPositions(BoardManager.getAvailableBuildPositions(), Colors.BUILD_POSITION_COLOR, ShapeRenderer.ShapeType.Filled);

        float mouseposboardx = camera.getCamera().zoom * (mousex - camera.camOriginX) + camera.getCamera().position.x;
        float mouseposboardy = camera.getCamera().zoom * (Gdx.graphics.getHeight() - mousey - camera.camOriginY) + camera.getCamera().position.y;
        int selectx = (int)(mouseposboardx / (float)Constants.TILE_SIZE);
        int selecty = (int)(mouseposboardy / (float)Constants.TILE_SIZE);

        BoardManager.setHoveredPosition(new Position(selectx, selecty));

        if(BoardManager.getHoveredPosition().isValid()) {
            highlightPosition(BoardManager.getHoveredPosition(), Colors.HOVERED_TILE_COLOR, ShapeRenderer.ShapeType.Line);
            Unit dude = GameState.instance.unitState.unitFromPosition(BoardManager.getHoveredPosition());

            // highlighting moves
            if(dude != null && dude != GameState.instance.unitState.getSelectedUnit()) {
                highlightPositions(dude.getAvailableTargets(), Colors.ATTACK_TILE_COLOR, ShapeRenderer.ShapeType.Filled);
            }
        }

        if(GameState.instance.unitState.getSelectedUnit() != null) {
            if(BoardManager.getSelectType() == BoardManager.SelectType.ACTION) {
                UnitAction act = GameState.instance.unitState.getSelectedUnit().getCurrentAction();
                if(act.requiresTarget) {
                    if(act.getName().equals("move") || act.getName().equals("fly")) {
                        highlightPositions(GameState.instance.unitState.getSelectedUnit().getAvailableTargets(), Colors.MOVE_TILE_COLOR, ShapeRenderer.ShapeType.Filled);
                    }
                    else if(act.getName().equals("attack")) {
                        highlightPositions(GameState.instance.unitState.getSelectedUnit().getAvailableTargets(), Colors.ATTACK_TILE_COLOR, ShapeRenderer.ShapeType.Filled);
                    }
                }
            }
        }

        highlighter.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void render(Batch batch, float stateTime) {

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
                boardState.getBoard()[x][y].dispose();
            }
        }
        highlighter.dispose();
    }
}
