package com.mygdx.game.game.gameunits;

import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.game.gameunits.unitfiles.TestUnit;

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
        throw new NullPointerException("you fucking idiot can't even type in names right");
    }

    public static void dispose() {
        for(BaseUnit unit : list) {
            unit.dispose();
        }
    }
}
