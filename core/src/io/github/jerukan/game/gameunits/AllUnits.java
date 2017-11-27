package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitdata.*;
import io.github.jerukan.util.Assets;

import java.util.ArrayList;
import java.util.Collections;

/** Manages instances of every type of unit that is to exist in the game */

public class AllUnits {

    public static final BaseUnit[] basicList = {
            new BasicUnit("spearman", 3, 2, 3, 1, 2, BaseUnit.Type.SOLDIER, Assets.getTexture(Assets.spearman)),
    };

    public static final BaseUnit[] normalList = {
            new VillageUnit(),
            new TestUnit(),
            new TestUnit2(),
            new TestUnit3()
    };

    public static ArrayList<BaseUnit> list = new ArrayList<BaseUnit>();

    public static void init() {
        Collections.addAll(list, basicList);
        Collections.addAll(list, normalList);
    }

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
