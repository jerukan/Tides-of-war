package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitdata.*;
import io.github.jerukan.util.Assets;

import java.util.ArrayList;
import java.util.Collections;

/** Manages instances of every type of unit that is to exist in the game */

public class AllUnits {

    private static final BaseUnit[] basicList = {
            new BasicUnit("footman", 2, 1, 2, 1, 100, BaseUnit.Type.SOLDIER, "Good ol\' reliable.", Assets.getTexture(Assets.footman)),
            new BasicUnit("archer", 1, 1, 2, 2, 100, BaseUnit.Type.SOLDIER, "Masters of rushing, but die to a tap.", Assets.getTexture(Assets.archer)),
            new BasicUnit("armory", 7, 0, 0, 0, 400, BaseUnit.Type.BUILDING, "Allows the production of spearmen and shieldbearers", Assets.getTexture(Assets.armory))
    };

    private static final BaseUnit[] specialList = {
            new VillageUnit(),
            new SpearmanUnit(),
            new GoldmineUnit(),
            new FarmUnit()
    };

    public static ArrayList<BaseUnit> list = new ArrayList<>();

    public static void init() {
        Collections.addAll(list, basicList);
        Collections.addAll(list, specialList);
    }

    public static void validateUnits() {
        for(BaseUnit unit : list) {
            for(BaseUnit other : list) {
                if(!unit.equals(other)) {
                    if(unit.name.equals(other.name)) {
                        throw new IllegalArgumentException("Two or more units of name \"" + unit.name + "\" found");
                    }
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
