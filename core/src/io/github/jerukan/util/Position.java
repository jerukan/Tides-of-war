package io.github.jerukan.util;

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
        if(pos.length != 2) {
            throw new IllegalArgumentException("A position only has two numbers");
        }
        x = pos[0];
        y = pos[1];
    }

    public Position(Position pos) {
        x = pos.getX();
        y = pos.getY();
    }

    /** @return (x, y) */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Position pos) {
        return pos.getX() == x && pos.getY() == y;
    }

    public boolean isValid() {
        return x >= 0 && y >= 0;
    }

    /** Disclaimer: no diagonals
     * @param x the x position of the other point
     * @param y the y position of the other point
     * @return distance to given point
     */
    public int distanceToPosition(int x, int y) {
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }

    /** @param other the other position
     * @return distance to given point */
    public int distanceToPosition(Position other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
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

    public void setPos(Position pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

    public void reset() {
        setPos(-1, -1);
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
