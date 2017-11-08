package com.mygdx.game.game.gameunits.unitfiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/** A base class for all the stats and available actions of a single type of unit
 * Not actually the object that gets placed on the board */

public abstract class BaseUnit {

    public final String name;
    public final int baseHealth;
    public final int baseAttack;
    public final int baseSpeed;
    public final int baseRange;

    public final Type type;

    private Texture texture;

    /** Creates a reference unit containing stats of the unit and actions it can perform
     * @param name the name of the unit to be used for display and reference
     * @param baseHealth default health of a unit
     * @param baseAttack default attack of a unit
     * @param baseSpeed default speed of a unit
     * @param baseRange default range of a unit
     * @param type determines if the unit is a BUILDING or SOLDIER */
    public BaseUnit(String name, int baseHealth, int baseAttack, int baseSpeed, int baseRange, Type type) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseSpeed = baseSpeed;
        this.baseRange = baseRange;

        this.type = type;
    }

    /** Call this in the constructor of the custom unit
     * @param texture specified texture of the unit, size (usually) doesn't matter */
    void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    /** The recursive method containing the algorithm for generating board positions the unit can move to
     * Can be changed into some more interesting move schemes
     * @param startpos the starting position of the selected unit
     * @param checkedpos continuously changing position being checked to see if it's a valid move position
     * @param moves the aggregate ArrayList of all the valid move positions of the unit
     * @param movesleft number of moves left to determine when the max range of the moves is reached */
    public void generateMoves(Integer[] startpos, Integer[] checkedpos, ArrayList<Integer[]> moves, int movesleft) {
        if(Arrays.equals(startpos, checkedpos)) {
            return;
        }
        if(!moves.contains(checkedpos)) {
            moves.add(checkedpos);
        }

        movesleft -= 1;

        if(checkedpos[0] + 1 < Constants.BOARD_WIDTH) {
            checkedpos[0] += 1;
            generateMoves(startpos, checkedpos, moves, movesleft);
        }
        if(checkedpos[0] - 1 >= 0) {
            checkedpos[0] -= 1;
            generateMoves(startpos, checkedpos, moves, movesleft);
        }
        if(checkedpos[1] + 1 < Constants.BOARD_HEIGHT) {
            checkedpos[1] += 1;
            generateMoves(startpos, checkedpos, moves, movesleft);
        }
        if(checkedpos[1] - 1 >= 0) {
            checkedpos[1] -= 1;
            generateMoves(startpos, checkedpos, moves, movesleft);
        }
    }

    /** Called by the actual units to retrieve unit moves
     * Uses generateMoves to get the moves
     * @param self the existing selected unit
     * @return ArrayList of valid move positions of the unit */
    public ArrayList<Integer[]> getMoves(Unit self) {
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        generateMoves(self.getPosition(), self.getPosition(), moves, self.getCurrentSpeed());
        return moves;
    }

    /** Determines what happens when the unit performs an action on a specified target
     * Can perform actions other than only dealing damage to a single unit
     * @param self the existing selected unit
     * @param target the existing targeted unit */
    public void onTargetAction(Unit self, Unit target) {
        target.takeDamage(self.getCurrentAttack());
    }

    /** Action that is performed when a unit is added to the board
     * @param self the unit being created */
    public abstract void onCreation(Unit self);

    /** Action that is performed when a unit is killed
     * @param self the unit being killed */
    public abstract void onDeath(Unit self);

    /** Action that is performed on the start of the player's turn
     * Only called on the owner's turn start
     * @param self the existing selected unit */
    public abstract void onTurnStart(Unit self);

    /** Action that is performed on the end of the player's turn
     * Only called on the owner's turn end
     * @param self the existing selected unit */
    public abstract void onTurnEnd(Unit self);

    public void dispose() {
        texture.dispose();
    }

    public enum Type {
        BUILDING, SOLDIER
    }
}