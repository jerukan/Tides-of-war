package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitdata.*;
import io.github.jerukan.util.Assets;

import java.util.ArrayList;
import java.util.Collections;

/** Manages instances of every type of unit that is to exist in the game */

public class UnitRegistry {

    private static int currentId = 0;

    public static BasicUnit footman = new BasicUnit("footman", 2, 1, 2, 1, 100,
            BaseUnit.Type.SOLDIER, "Good ol\' reliable.", Assets.getTexture(Assets.footman));
    public static BasicUnit archer = new BasicUnit("archer", 1, 1, 2, 2, 100,
            BaseUnit.Type.SOLDIER, "Masters of rushing, but die to a tap.", Assets.getTexture(Assets.archer));
    public static BasicUnit armory = new BasicUnit("armory", 7, 0, 0, 0, 400,
            BaseUnit.Type.BUILDING, "Allows the production of spearmen and shieldbearers", Assets.getTexture(Assets.armory));

    public static VillageUnit village = new VillageUnit();
    public static SpearmanUnit spearman = new SpearmanUnit();
    public static GoldmineUnit goldmine = new GoldmineUnit();
    public static FarmUnit farm = new FarmUnit();

    public static ArrayList<BaseUnit> unitList = new ArrayList<>();

    private static void register(BaseUnit unit) {
        unit.setId(currentId);
        unitList.add(unit);
        currentId++;
    }

    private static void registerUnits() {
        register(footman);
        register(archer);
        register(armory);
        register(village);
        register(spearman);
        register(goldmine);
        register(farm);
    }

    private static void validateUnits() {
        for(BaseUnit unit : unitList) {
            for(BaseUnit other : unitList) {
                if(!unit.equals(other)) {
                    if(unit.name.equals(other.name)) {
                        throw new IllegalArgumentException("Two or more units of name \"" + unit.name + "\" found");
                    }
                }
            }
        }
    }

    public static void init() {
        registerUnits();
        validateUnits();
    }

    public static BaseUnit getUnitFromName(String name) {
        for(BaseUnit unit : unitList) {
            if(unit.name.equals(name)) {
                return unit;
            }
        }
        throw new NullPointerException("Unit \"" + name + "\" does not exist");
    }

    public static void dispose() {
        for(BaseUnit unit : unitList) {
            unit.dispose();
        }
    }
}
