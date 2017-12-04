package io.github.jerukan.game.gameunits.unitdata;

import com.badlogic.gdx.graphics.Texture;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.AttackAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.DismissAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.MoveAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

/** A base class for all the stats and available actions of a single type of unit
 * Not actually the object that gets placed on the board */

public abstract class BaseUnit {

    //temporary id will be set later in UnitRegistry
    public int id = -1;

    public String name;
    public int baseHealth;
    public int baseAttack;
    public int baseSpeed;
    public int baseRange;

    public int baseUpkeep;
    public int baseCost;

    public Type type;

    public UnitAction[] actions;

    public String description;

    private Texture texture;

    /** Creates a reference unit containing stats of the unit and actions it can perform
     * These are default stats */
    public BaseUnit() {
        name = "dood";
        baseHealth = 10;
        baseAttack = 0;
        baseSpeed = 0;
        baseRange = 0;

        baseUpkeep = 1;
        baseCost = 100;

        actions = new UnitAction[]{new MoveAction(this), new AttackAction(this), new DismissAction(this)};

        description = "This is a generic unit without any love put into it";

        type = Type.SOLDIER;
    }

    /** Call this in the constructor of the custom unit
     * @param texture specified texture of the unit, size (usually) doesn't matter */
    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setId(int id) {
        this.id = id;
    }

    /** The recursive method containing the algorithm for generating board positions the unit can move to
     * Can be changed into some more interesting move schemes
     * @param startpos the starting position of the selected unit
     * @param checkedpos continuously changing position being checked to see if it's a valid move position
     * @param moves the aggregate ArrayList of all the valid move positions of the unit
     * @param movesleft number of moves left to determine when the max range of the moves is reached */
    protected void generateMoves(Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft) {
        int speedconsump = GameState.instance.boardManager.tileFromPosition(checkedpos).getSpeedConsump();

        if(!startpos.equals(checkedpos)) {
            if (movesleft < speedconsump - 1) {
                return;
            }

            if (!checkedpos.existsInArray(moves)) {
                moves.add(checkedpos);
                moveConsump.add(aggregateConsump);
            }
        }
        aggregateConsump += speedconsump;
        movesleft -= speedconsump;

        if (checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            generateMoves(startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            generateMoves(startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            generateMoves(startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getY() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            generateMoves(startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
    }

    /** The recursive method containing the algorithm for generating board positions the unit can move to
     * Can be changed into some more interesting attack schemes
     * @param startpos the starting position of the selected unit
     * @param checkedpos continuously changing position being checked to see if it's a valid attack position
     * @param attacks the aggregate ArrayList of all the valid attack positions of the unit
     * @param attacksleft number of moves left to determine when the max range of the attacks is reached */
    protected void generateAttacks(Position startpos, Position checkedpos, ArrayList<Position> attacks, int attacksleft) {
        if(!checkedpos.existsInArray(attacks) && !startpos.equals(checkedpos)) {
            attacks.add(checkedpos);
        }
        if(attacksleft <= 0) {
            return;
        }

        attacksleft -= 1;

        if(checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            generateAttacks(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            generateAttacks(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            generateAttacks(startpos, newpos, attacks, attacksleft);
        }
        if(checkedpos.getY() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            generateAttacks(startpos, newpos, attacks, attacksleft);
        }
    }

    /** Called by the actual units to retrieve unit moves
     * Uses generateMoves to get the moves
     * @param self the existing selected unit */
    public void getMoves(Unit self) {
        self.getAvailableMoves().clear();
        self.getMoveConsumptions().clear();
        Position unitpos = new Position(self.getPosition());
        Position startpos = new Position(self.getPosition());
        generateMoves(startpos, unitpos, self.getAvailableMoves(), self.getMoveConsumptions(), 0, self.getCurrentSpeed());
    }

    /** Called by the actual units to retrieve unit attacks
     * Uses generateAttacks to get the moves
     * @param self the existing selected unit
     * @return ArrayList of valid attack positions of the unit */
    public ArrayList<Position> getAttacks(Unit self) {
        ArrayList<Position> attacks = new ArrayList<>();
        Position unitpos = new Position(self.getPosition());
        Position startpos = new Position(self.getPosition());
        generateAttacks(unitpos, startpos, attacks, self.getCurrentRange());
        return attacks;
    }

    /** Uses custom condition canBuild given below to determine valid build positions
     * May be overridden
     * @param pos the selected position to build on
     * @param owner the player who owns this unit
     * @return whether the unit can be built or not */
    public boolean _canBuild(Position pos, Player owner) {
        return owner.hasSufficientMoney(baseCost)
                && pos.existsInArray(GameState.instance.boardManager.getAvailableBuildPositions())
                && canBuild(owner) && owner.hasSufficientUpkeep(baseUpkeep);
    }

    /** Defines the conditions under which the unit can be built under
     * Does not include the insufficient money condition: that is assumed for all units
     * @param owner the player who owns this unit
     * @return whether the unit can be built or not */
    public abstract boolean canBuild(Player owner);

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

    public String buttonDisplayString() {
        return name + " $" + baseCost;
    }

    public void dispose() {
        texture.dispose();
    }

    public enum Type {
        BUILDING, SOLDIER
    }
}