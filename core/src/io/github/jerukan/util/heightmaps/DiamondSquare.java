package io.github.jerukan.util.heightmaps;

import com.badlogic.gdx.math.MathUtils;
import io.github.jerukan.game.board.Tile;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.Util;

import java.util.ArrayList;

public class DiamondSquare {

    private static int diamondsteps = 0;
    private static int squaresteps = 0;

    public static double[][] heights = new double[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
    private static final double offsetFactorDefault = 0.5;
    private static final double offsetFactorConstant = 0.7;
    private static double offsetFactor = offsetFactorDefault;

    private static void initCorners() {
        heights[0][0] = Math.random();
        heights[Constants.BOARD_WIDTH - 1][0] = Math.random();
        heights[0][Constants.BOARD_HEIGHT - 1] = Math.random();
        heights[Constants.BOARD_WIDTH - 1][Constants.BOARD_HEIGHT - 1] = Math.random();
    }

    private static void reset() {
        offsetFactor = offsetFactorDefault;
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for (int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                heights[x][y] = 0;
            }
        }
        initCorners();
    }

    //apparently this is actually a diamond step but whatever
    private static double squareStep(int xpos, int ypos, int radius) {
        ArrayList<Position> points = new Position(xpos, ypos).getPositionsDistance(radius);
        ArrayList<Double> selectHeights = new ArrayList<>();
        for(Position p : points) {
            if(heights[p.getX()][p.getY()] == 0) {
                return 0; //if any of the heights are 0 return no height
            }
            selectHeights.add(heights[p.getX()][p.getY()]);
        }
        double offset = (2 * Math.random() - 1) * offsetFactor;
        squaresteps++;
        return Util.averageList(selectHeights) + offset;
    }

    //this is supposed to be the square step (?)
    private static double diamondStep(int xpos, int ypos, int radius) {
        ArrayList<Position> points = new Position(xpos, ypos).getPositionsDiagonal(radius, radius);
        ArrayList<Double> selectHeights = new ArrayList<>();
        for(Position p : points) {
            if(heights[p.getX()][p.getY()] == 0) {
                return 0; //if any of the heights are 0 return no height
            }
            selectHeights.add(heights[p.getX()][p.getY()]);
        }
        double offset = (2 * Math.random() - 1) * offsetFactor;
        diamondsteps++;
        return Util.averageList(selectHeights) + offset;
    }

    public static void generateHeights() {
        reset();
        for(int radius = (Constants.BOARD_WIDTH - 1) / 2; radius > 0; radius--) {
            diamondsteps = 0;
            squaresteps = 0;
            for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
                for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                    if(heights[x][y] == 0) {
                        heights[x][y] = MathUtils.clamp(diamondStep(x, y, radius), 0 ,1);
                    }
                }
            }
            for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
                for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                    if(heights[x][y] == 0) {
                        heights[x][y] = MathUtils.clamp(squareStep(x, y, radius), 0, 1);
                    }
                }
            }
//            System.out.println("diamond steps: " + diamondsteps);
//            System.out.println("square steps: " + squaresteps);
            offsetFactor *= offsetFactorConstant;
        }
    }
}
