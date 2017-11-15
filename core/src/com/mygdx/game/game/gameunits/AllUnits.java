package com.mygdx.game.game.gameunits;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.game.gameunits.unitfiles.BaseUnit;
import com.mygdx.game.game.gameunits.unitfiles.TestUnit;
import com.mygdx.game.game.ui.UnitBuildButton;

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

    public static Table getUnitTable() {
        Table out = new Table();
        for(BaseUnit unit : list) {
            out.add(new UnitBuildButton(unit));
        }
        return out;
    }

    public static void dispose() {
        for(BaseUnit unit : list) {
            unit.dispose();
        }
    }
}
