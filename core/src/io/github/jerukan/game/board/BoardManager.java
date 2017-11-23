package io.github.jerukan.game.board;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Manager;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

/** A class managing most elements of the board, namely tiles */

public class BoardManager implements Manager {

    private Tile[][] board;

    private Position hoveredPosition;
    private Position selectedPosition;

    private SelectType selectType;

    public BoardManager() {
        hoveredPosition = new Position();
        selectedPosition = new Position();
        selectType = SelectType.SELECT;
    }

    public Tile[][] getBoard() {
        return board;
    }

    /** Sets the board position of the currently hovered tile
     * @param pos desired position to set */
    public void setHoveredPosition(Position pos) {
        if(pos.getX() >= 0 && pos.getX() < Constants.BOARD_WIDTH && pos.getY() >= 0 && pos.getY() < Constants.BOARD_HEIGHT) {
            hoveredPosition.setPos(pos);
        }
        else {
            hoveredPosition.reset();
        }
    }

    public Position getHoveredPosition() {
        return hoveredPosition;
    }

    /** Determines what happens on the next tile selection with the left mouse button */
    public void selectedPositionActionLeft() {
        switch (selectType) {
            case ACTION:
                Unit unit = GameState.instance.unitManager.getSelectedUnit();
                UnitAction act = unit.getCurrentAction();
                if(act.requiresTarget) {
                    act.execute(unit, hoveredPosition);
                }
                GameState.instance.unitManager.killUnits();
                selectType = SelectType.SELECT;
                selectedPosition.reset();
                break;
        }
    }

    /** Determines what happens on the next tile selection with the right mouse button */
    public void selectedPositionActionRight() {
        switch (selectType) {
            case SELECT:
                selectedPosition = new Position(hoveredPosition);
                GameState.instance.unitManager.setSelectedUnit(GameState.instance.unitManager.unitFromPosition(selectedPosition));
                break;
        }
    }

    public void setSelectedPosition(Position selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectType(SelectType selectType) {
        this.selectType = selectType;
    }

    public SelectType getSelectType() {
        return selectType;
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

    public void dispose() {
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                board[x][y].dispose();
            }
        }
    }

    @Override
    public void init() {
        resetBoard();
    }

    @Override
    public void update() {

    }

    public enum SelectType {
        SELECT,
        ACTION
    }
}
