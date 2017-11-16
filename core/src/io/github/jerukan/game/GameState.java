package io.github.jerukan.game;

import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.game.gameunits.UnitManager;
import io.github.jerukan.game.ui.UIManager;
import io.github.jerukan.game.ui.screens.GameScreen;
import io.github.jerukan.util.Position;

public class GameState {

    public static GameState instance;

    public BoardManager boardManager;
    public UIManager uiManager;
    public UnitManager unitManager;

    public GameState() {
        boardManager = new BoardManager(World.boardCam);
        uiManager = new UIManager(World.uiCam, World.uiStage);
        unitManager = new UnitManager();
    }

    public void init() {
        uiManager.setCurrentScreen(new GameScreen(World.uiStage));

        uiManager.init();
        boardManager.init();
    }

    public void reset() {
        boardManager.resetBoard();
        unitManager.clearUnits();
        unitManager.addUnit(AllUnits.getUnit("test"), null, new Position(3, 5));
        unitManager.generateUnitMoves();
    }

    public void update() {
        boardManager.updateOffsets();
        uiManager.update();
    }

    public void dispose() {
        uiManager.dispose();
        boardManager.dispose();
    }
}