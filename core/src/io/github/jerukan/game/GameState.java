package io.github.jerukan.game;

import com.badlogic.gdx.graphics.Color;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.board.BoardState;
import io.github.jerukan.game.gameunits.UnitState;

/** Manages all the nitty gritty numbers and stuff of the game
 * Note to self: INDEPENDENT of the renderers */

public class GameState implements State {

    public static GameState instance;

    public BoardState boardState;
    public UnitState unitState;

    public Player player1;
    public Player player2;

    public Player[] players;
    public int currentPlayerNum;

    public GameState() {
        boardState = new BoardState();
        unitState = new UnitState();

        player1 = new Player("boi", new Color(0.2f, 0.5f, 0.3f, 1f));
        player2 = new Player("dood", new Color(0.5f, 0.2f, 0.9f, 1f));
        players = new Player[]{player1, player2};
        currentPlayerNum = 0;
    }

    @Override
    public void init() {
        verifyPlayers();
        boardState.init();
        unitState.init();
    }

    public void reset() {
        boardState.resetBoard();
        unitState.clearUnits();
        //to lazy to make it so that nothing happens on both player's first turns
        getCurrentPlayer().onNewTurn();
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerNum];
    }

    public void passTurn() {
        unitState.onEndTurn();
        currentPlayerNum++;
        if(currentPlayerNum >= players.length) {
            currentPlayerNum = 0;
        }
        BoardManager.getSelectedPosition().reset();
        unitState.onNewTurn();
        getCurrentPlayer().onNewTurn();
        BoardManager.updateAvailableBuildPositions();
        update();
    }

    public void verifyPlayers() {
        for(Player player : players) {
            for(Player other : players) {
                if(player != other) {
                    if(player.name.equals(other.name)) {
                        throw new IllegalArgumentException("Found two or more players with the name \"" + player.name + "\"");
                    }
                    else if(player.color.equals(other.color)) {
                        throw new IllegalArgumentException("Found two or more players with the same colors " + player.color.toString());
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        boardState.update();
        unitState.update();
    }

    public void dispose() {
        boardState.dispose();
    }
}