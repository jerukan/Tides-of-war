package io.github.jerukan.game;

import com.badlogic.gdx.graphics.Color;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.UnitManager;

/** Manages all the nitty gritty numbers and stuff of the game
 * Note to self: INDEPENDENT of the renderers */

public class GameState {

    public static GameState instance;

    public BoardManager boardManager;
    public UnitManager unitManager;

    public Player player1;
    public Player player2;

    public Player[] players;
    public Player currentPlayer;

    public GameState() {
        boardManager = new BoardManager();
        unitManager = new UnitManager();

        player1 = new Player("dood", new Color(0.2f, 0.8f, 0.3f, 1f));
        player2 = new Player("boi", new Color(0.8f, 0.3f, 0.3f, 1f));
        players = new Player[]{player1, player2};
        currentPlayer = players[0];
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