package io.github.jerukan.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.EmptyAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

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

    /** sprite is currently just for position... i guess */
    private Sprite sprite;
    private Animation<TextureRegion> animation;

    private ArrayList<Position> availableTargets;
    private ArrayList<Integer> targetSpeedConsumptions;

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

        currentAction = new EmptyAction(unit);

        sprite = new Sprite(baseunit.getTexture());
        moveSprite(position);
        animation = Assets.getAnimation(baseunit.getTexture(), Assets.UNIT_PIXEL_SIZE, Assets.UNIT_PIXEL_SIZE, 0.8f, unit.oddAnimation);

        availableTargets = new ArrayList<>();
        targetSpeedConsumptions = new ArrayList<>();
    }

    public void render(Batch batch, float stateTime) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void moveSprite(Position position) {
        Sprite tile = GameState.instance.boardState.getBoard()[position.getX()][position.getY()].getSprite();
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

    public void clearMoves() {
        availableTargets.clear();
    }

    public boolean hasSufficientSpeed(int spd) {
        return currentSpeed >= spd;
    }

    public void clearMoveLists() {
        availableTargets.clear();
        targetSpeedConsumptions.clear();
    }

    // unit actions

    public void takeDamage(int amount) {
        currentHealth -= amount;
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

    public void setAvailableTargets(ArrayList<Position> positions) {
        availableTargets.clear();
        availableTargets.addAll(positions);
    }

    public void setTargetSpeedConsumptions(ArrayList<Integer> speeds) {
        targetSpeedConsumptions.clear();
        targetSpeedConsumptions.addAll(speeds);
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

    public ArrayList<Position> getAvailableTargets() {
        return availableTargets;
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
