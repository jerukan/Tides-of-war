package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

/** A class that defines an action some unit could use
 * There are two basic actions: moving and attacking */
public abstract class UnitAction {

    protected BaseUnit baseUnit;
    protected String name;
    protected int speedConsumption;
    public boolean requiresTarget;

    protected ArrayList<Position> availableTargets = new ArrayList<>();
    protected ArrayList<Integer> targetSpeedConsumptions = new ArrayList<>();

    /** Constructs an action using stats from a base unit
     * Can be restricted to a single unit and such */
    public UnitAction(BaseUnit baseUnit) {
        this.baseUnit = baseUnit;
    }

    /** Action without a target
     * @param self the unit to perform this action */
    public abstract void execute(Unit self);

    /** Action with a target
     * @param self the unit to perform this action
     * @param target the selected position */
    public abstract void execute(Unit self, Position target);

    /** Recursion flood fill galore
     * @param self the dude who is performing the action
     * @param startpos the unit's position
     * @param checkedpos the position being checked
     * @param moves ArrayList of all the available moves of the unit
     * @param moveConsump how much speed each position in its corresponding position will take up
     * @param aggregateConsump stuck into moveConsump to get the overall speed consumption at each position
     * @param movesleft how much speed is left in the loop */
    public abstract void getTarget(Unit self, Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft);

    /** Will call getTarget to generate the positions
     * @param self the dude performing the action */
    public abstract void generateTargets(Unit self);

    public ArrayList<Position> getAvailableTargets() {
        return availableTargets;
    }

    public ArrayList<Integer> getTargetSpeedConsumptions() {
        return targetSpeedConsumptions;
    }

    public String getName() {
        return name;
    }

    public int getSpeedConsumption() {
        return speedConsumption;
    }
}
