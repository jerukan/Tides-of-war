package io.github.jerukan.game;

import com.badlogic.gdx.graphics.Color;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.UnitManager;

/** Manages all the nitty gritty numbers and stuff of the game
 * Note to self: INDEPENDENT of the renderers */

public class GameState implements Manager {

    public static GameState instance;

    public BoardManager boardManager;
    public UnitManager unitManager;

    public Player player1;
    public Player player2;

    public Player[] players;
    public int currentPlayerNum;

    public GameState(String ... buddyboys) {
        boardManager = new BoardManager();
        unitManager = new UnitManager();

        Player[] ppp = new Player[buddyboys.length];
        for(int i = 0; i < buddyboys.length; i++) {
            ppp[i] = new Player(buddyboys[i], new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1f));
        }
        players = ppp;
        currentPlayerNum = 0;
    }

    @Override
    public void init() {
        verifyPlayers();
        boardManager.init();
        unitManager.init();
    }

    public void reset() {
        boardManager.resetBoard();
        unitManager.clearUnits();
        //to lazy to make it so that nothing happens on both player's first turns
        getCurrentPlayer().onNewTurn();
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerNum];
    }

    public void passTurn() {
        unitManager.onEndTurn();
        currentPlayerNum++;
        if(currentPlayerNum >= players.length) {
            currentPlayerNum = 0;
        }
        boardManager.getSelectedPosition().reset();
        unitManager.onNewTurn();
        getCurrentPlayer().onNewTurn();
        boardManager.updateAvailableBuildPositions();
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
        boardManager.update();
        unitManager.update();
    }

    public void dispose() {
        boardManager.dispose();
    }
}