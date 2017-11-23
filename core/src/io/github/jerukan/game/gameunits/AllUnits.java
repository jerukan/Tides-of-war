package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.gameunits.unitdata.TestUnit;
import io.github.jerukan.game.gameunits.unitdata.TestUnit2;
import io.github.jerukan.game.gameunits.unitdata.TestUnit3;

/** Manages instances of every type of unit that is to exist in the game */

public class AllUnits {

    public static final BaseUnit[] list = {
            new TestUnit(),
            new TestUnit2(),
            new TestUnit3()
    };

    public static void validateUnits() {
        for(BaseUnit unit : list) {
            for(BaseUnit other : list) {
                if(!unit.equals(other) && unit.name.equals(other.name)) {
                    throw new IllegalArgumentException("Two or more units of name \"" + unit.name + "\" found");
                }
            }
        }
    }

    public static BaseUnit getUnit(String name) {
        for(BaseUnit unit : list) {
            if(unit.name.equals(name)) {
                return unit;
            }
        }
        throw new NullPointerException("Unit \"" + name + "\" does not exist");
    }

    public static void dispose() {
        for(BaseUnit unit : list) {
            unit.dispose();
        }
    }
}
