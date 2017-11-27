package io.github.jerukan.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.EmptyAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.Util;

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

    private UnitAction currentAction;

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

        currentAction = new EmptyAction();

        sprite = new Sprite(baseunit.getTexture());
        moveSprite(position);
    }

    public void render(Batch batch) {
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void moveSprite(Position position) {
        Sprite tile = GameState.instance.boardManager.getBoard()[position.getX()][position.getY()].getSprite();
        sprite.setSize(Constants.UNIT_SIZE, Constants.UNIT_SIZE);
        float spriteoffset = (Constants.TILE_SIZE - Constants.UNIT_SIZE) / 2;
        sprite.setPosition(tile.getX() + spriteoffset, tile.getY() + spriteoffset);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public void generateMovesAndAttacks() {
        availableMoves = baseunit.getMoves(this);
        availableAttacks = baseunit.getAttacks(this);
    }

    public void clearMoves() {
        availableMoves.clear();
        availableAttacks.clear();
    }

    public boolean hasSufficientSpeed(int spd) {
        return currentSpeed >= spd;
    }

    // unit actions

    public void takeDamage(int amount) {
        currentHealth -= amount;
    }

    public void onTargetAction(Unit target) {
        baseunit.onTargetAction(this, target);
    }

    public void onCreation() {
        baseunit.onCreation(this);
    }

    public void onDeath() {
        baseunit.onDeath(this);
    }

    public void onTurnStart() {
        baseunit.onTurnStart(this);
    }

    public void onTurnEnd() {
        baseunit.onTurnEnd(this);
    }

    // mutators

    public void resetSpeed() {
        currentSpeed = baseunit.baseSpeed;
    }

    public void setCurrentAction(UnitAction currentAction) {
        this.currentAction = currentAction;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setCurrentAttack(int currentAttack) {
        this.currentAttack = currentAttack;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setCurrentRange(int currentRange) {
        this.currentRange = currentRange;
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

    public UnitAction getCurrentAction() {
        return currentAction;
    }

    public Player getOwner() {
        return owner;
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
