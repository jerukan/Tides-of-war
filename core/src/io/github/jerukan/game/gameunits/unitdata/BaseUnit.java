package io.github.jerukan.game.gameunits.unitdata;

import com.badlogic.gdx.graphics.Texture;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.AttackAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.DismissAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.MoveAction;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;
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

    public BaseUnit requiredUnit = null;

    private Texture texture;
    public boolean oddAnimation;

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

        setTexture(Assets.getTexture(Assets.wall));
        oddAnimation = false;
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
        BUILDING, SOLDIER, FLYING
    }
}