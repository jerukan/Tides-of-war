package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

/** A class that defines an action some unit could use
 * There are two basic actions: moving and attacking */
public abstract class UnitAction {

    protected BaseUnit baseUnit;
    protected String name;
    protected int speedConsumption;
    public boolean requiresTarget;

    /** Constructs an action using stats from a base unit
     * Can be restricted to a single unit and such */
    public UnitAction() {
        name = "action";
        speedConsumption = 0;
        requiresTarget = false;
    }

    /** Action without a target
     * @param self the unit to perform this action */
    public abstract void execute(Unit self);

    /** Action with a target
     * @param self the unit to perform this action
     * @param target the selected position */
    public abstract void execute(Unit self, Position target);

    public String getName() {
        return name;
    }
}
