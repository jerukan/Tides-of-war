package io.github.jerukan.game.board;

import io.github.jerukan.game.State;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.heightmaps.DiamondSquare;
import io.github.jerukan.util.heightmaps.Perlin;

/** A class managing most elements of the board, namely tiles */

public class BoardState implements State {

    private Tile[][] board;

    public BoardState() {
        //something
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile tileFromPosition(Position pos) {
        if(pos.isValid()) {
            return board[pos.getX()][pos.getY()];
        }
        throw new IllegalArgumentException("No tile at " + pos.toString());
    }

    public void resetBoard() {
        generateHeightsDSquare(1121956);
        board = new Tile[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                Position pos = new Position(x, y);
                board[x][y] = new Tile(pos, (float)DiamondSquare.heights[x][y]);
                board[x][y].setSpritePosition(Constants.TILE_SIZE * x, Constants.TILE_SIZE * y);
            }
        }
    }

    public void generateHeightsDSquare() {
        DiamondSquare.generateHeights();
    }

    public void generateHeightsDSquare(long seed) {
        DiamondSquare.setSeed(seed);
        DiamondSquare.generateHeights();
    }

    public void generateHeightsPerlin() {
        Perlin.randomizeVectors();
        Perlin.zeroExtrema();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                double sum = 0;
                for(int i = 0; i < Constants.PERLIN_SAMPLES; i++) {
                    sum += Perlin.getNoise((float) (x + Math.random()), (float) (y + Math.random()));
                }
                sum /= Constants.PERLIN_SAMPLES;
                //board[x][y].setHeight((float)sum);
            }
        }
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                //board[x][y].setHeight(Util.map(board[x][y].getHeight(), Perlin.min, Perlin.max, 0, 1));
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
}
