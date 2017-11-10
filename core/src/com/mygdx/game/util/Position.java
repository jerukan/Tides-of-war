package com.mygdx.game.util;

/** Generic class for a typical x y position */
public class Position {

    private int x;
    private int y;

    /** Initializes a position that shouldn't be on the board */
    public Position() {
        x = -1;
        y = -1;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int[] pos) {
        if(pos.length > 2) {
            throw new IllegalArgumentException("A position only has two numbers");
        }
        x = pos[0];
        y = pos[1];
    }

    /** @return (x, y) */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Position pos) {
        return pos.getX() == x && pos.getY() == y;
    }

    // mutators

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }

    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    // accessors

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getPos() {
        return new int[]{x, y};
    }
}
