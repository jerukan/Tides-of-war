package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.Player;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Position;
import com.mygdx.game.util.Util;

import java.util.ArrayList;

/** The unit that is actually placed on the board and handled
* Not to be confused with the higher pH version of itself */

public class Unit {

    public final BaseUnit baseunit;

    private int currentHealth;
    private int currentAttack;
    private int currentSpeed;
    private int currentRange;

    private Position position;

    private Sprite sprite;

    private ArrayList<Position> availableMoves;
    private ArrayList<Position> availableAttacks;

    private Player owner;

    /** Creates the real unit that should belong to the board
     * @param unit the reference unit
     * @param owner player that owns the unit
     * @param position xy position on the board */
    public Unit(BaseUnit unit, Player owner, Position position) {
        baseunit = unit;

        currentHealth = unit.baseHealth;
        currentAttack = unit.baseAttack;
        currentSpeed = unit.baseSpeed;
        currentRange = unit.baseRange;

        this.owner = owner;

        this.position = position;

        sprite = new Sprite(baseunit.getTexture());
        moveSprite(position);
    }

    public void render(Batch batch) {
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void moveSprite(Position position) {
        Sprite tile = GameState.instance.boardManager.getBoard()[position.getX()][position.getY()].getSprite();
        float size = Constants.TILE_SIZE * Constants.UNIT_SIZE_RATIO;
        sprite.setSize(size, size);
        float spriteoffset = (Constants.TILE_SIZE - size) / 2;
        sprite.setPosition(tile.getX() + spriteoffset, tile.getY() + spriteoffset);
    }

    public void move(Position pos) {
        if(pos.isValid()) {
            if(Util.arrayContainsPosition(pos, availableMoves)) {
                if(GameState.instance.unitManager.positionAvailable(pos)) {
                    position = new Position(pos);
                    moveSprite(position);
                    generateMovesAndAttacks();
                }
            }
        }
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
    }

    public void generateMovesAndAttacks() {
        availableMoves = baseunit.getMoves(this);
        availableAttacks = baseunit.getAttacks(this);
    }

    public void clearMoves() {
        availableMoves.clear();
        availableAttacks.clear();
    }

    // accessors

    public ArrayList<Position> getAvailableMoves() {
        return availableMoves;
    }

    public ArrayList<Position> getAvailableAttacks() {
        return availableAttacks;
    }

    public Position getPosition() {
        return position;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentAttack() {
        return currentAttack;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int getCurrentRange() {
        return currentRange;
    }
}
