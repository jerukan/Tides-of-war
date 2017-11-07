package com.mygdx.game.game.gameunits;

/*
* The unit that is actually placed on the board and handled
* Not to be confused with the higher pH version of itself
* Ok that was bad
* */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.Player;
import com.mygdx.game.util.Constants;

public class Unit {

    private BaseUnit baseunit;

    private int currentHealth;
    private int currentAttack;
    private int currentSpeed;
    private int currentRange;

    private int[] position;

    private Sprite sprite;

    private int[][] availableMoves;
    private int[][] availableAttacks;

    private Player owner;

    public Unit(BaseUnit unit, Player owner, int[] position) {
        baseunit = unit;

        currentHealth = unit.baseHealth;
        currentAttack = unit.baseAttack;
        currentSpeed = unit.baseSpeed;
        currentRange = unit.baseRange;

        this.owner = owner;

        this.position = position;

        sprite = baseunit.getSprite();
        Sprite tile = GameState.instance.boardManager.getBoard()[position[0]][position[1]].getSprite();
        float size = Constants.TILE_SIZE * Constants.UNIT_SIZE_RATIO;
        sprite.setSize(size, size);
        sprite.setPosition(tile.getX(), tile.getY());
        System.out.println("xpos" + sprite.getX());
        System.out.println("ypos" + sprite.getY());
        System.out.println("width" + sprite.getWidth());
        System.out.println("height" + sprite.getHeight());
    }

    public void render(Batch batch) {
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
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
