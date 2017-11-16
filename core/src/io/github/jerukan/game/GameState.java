package io.github.jerukan.game;

import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.UnitManager;

/** Manages all the nitty gritty number and stuff of the game
 * Note to self: INDEPENDENT of the renderers */

public class GameState {

    public static GameState instance;

    public BoardManager boardManager;
    public UnitManager unitManager;

    public GameState() {
        boardManager = new BoardManager();
        unitManager = new UnitManager();
    }

    public void init() {
        boardManager.init();
        unitManager.init();
    }

    public void reset() {
        boardManager.resetBoard();
        unitManager.clearUnits();
    }

    public void update() {
        boardManager.update();
    }

    public void dispose() {
        boardManager.dispose();
    }
}