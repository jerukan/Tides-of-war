package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitfiles.BaseUnit;
import io.github.jerukan.game.gameunits.unitfiles.TestUnit;

public class AllUnits {

    public static final BaseUnit[] list = {
            new TestUnit()
    };

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
