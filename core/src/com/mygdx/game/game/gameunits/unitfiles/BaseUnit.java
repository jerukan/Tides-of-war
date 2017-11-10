package com.mygdx.game.game.gameunits.unitfiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Position;
import com.mygdx.game.util.Util;

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
    protected void generateMoves(Position startpos, Position checkedpos, ArrayList<Position> moves, int movesleft) {
        System.out.println("startpos: " + startpos.toString());
        System.out.println("checkedpos: " + checkedpos.toString());
        System.out.println("contained: " + moves.contains(checkedpos));
        System.out.println("equals: " + startpos.equals(checkedpos));
        if(!Util.positionInArray(checkedpos, moves) && !startpos.equals(checkedpos)) {
            moves.add(checkedpos);
            System.out.println("\nadded position");
        }
        for(Position pos : moves) {
            System.out.print(pos.toString());
        }
        System.out.println("\nmoves left " + movesleft + "\n---");
        if(movesleft <= 0) {
            return;
        }
        movesleft -= 1;

        if(checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            generateMoves(startpos, newpos, moves, movesleft);
        }
        if(checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            generateMoves(startpos, newpos, moves, movesleft);
        }
        if(checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            generateMoves(startpos, newpos, moves, movesleft);
        }
        if(checkedpos.getY() - 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            generateMoves(startpos, newpos, moves, movesleft);
        }
    }

    /** The recursive method containing the algorithm for generating board positions the unit can move to
     * Can be changed into some more interesting attack schemes
     * @param startpos the starting position of the selected unit
     * @param checkedpos continuously changing position being checked to see if it's a valid attack position
     * @param attacks the aggregate ArrayList of all the valid attack positions of the unit
     * @param attacksleft number of moves left to determine when the max range of the attacks is reached */
    protected void generateAttacks(Position startpos, Position checkedpos, ArrayList<Position> attacks, int attacksleft) {
        if(startpos.equals(checkedpos)) {
            return;
        }
        if(attacksleft <= 0) {
            return;
        }
        if(!attacks.contains(checkedpos)) {
            attacks.add(checkedpos);
        }

        attacksleft -= 1;

        if(checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            generateMoves(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            generateMoves(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            generateMoves(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getY() - 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            generateMoves(startpos, newpos, attacks, attacksleft);
        }
    }

    /** Called by the actual units to retrieve unit moves
     * Uses generateMoves to get the moves
     * @param self the existing selected unit
     * @return ArrayList of valid move positions of the unit */
    public ArrayList<Position> getMoves(Unit self) {
        ArrayList<Position> moves = new ArrayList<Position>();
        Position unitpos = new Position(self.getPosition().getPos());
        Position startpos = new Position(self.getPosition().getPos());
        generateMoves(startpos, unitpos, moves, self.getCurrentSpeed());
        for(Position pos : moves) {
            System.out.println(pos.toString());
        }
        return moves;
    }

    /** Called by the actual units to retrieve unit attacks
     * Uses generateAttacks to get the moves
     * @param self the existing selected unit
     * @return ArrayList of valid attack positions of the unit */
    public ArrayList<Position> getAttacks(Unit self) {
        ArrayList<Position> attacks = new ArrayList<Position>();
        Position unitpos = new Position(self.getPosition().getPos());
        Position startpos = new Position(self.getPosition().getPos());
        generateAttacks(unitpos, startpos, attacks, self.getCurrentRange());
        return attacks;
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