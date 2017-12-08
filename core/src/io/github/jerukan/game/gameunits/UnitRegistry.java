package io.github.jerukan.game.gameunits;

import io.github.jerukan.game.gameunits.unitdata.*;
import io.github.jerukan.util.Assets;

import java.util.ArrayList;
import java.util.Collections;

/** Manages instances of every type of unit that is to exist in the game */

public class UnitRegistry {

    private static int currentId = 0;

    public static BasicUnit footman = new BasicUnit("footman", 2, 1, 2, 1, 100,
            null, BaseUnit.Type.SOLDIER, "Good ol\' reliable.", Assets.getTexture(Assets.footman));
    public static BasicUnit archer = new BasicUnit("archer", 1, 1, 2, 2, 100,
            null, BaseUnit.Type.SOLDIER, "Masters of rushing, but die to a tap.", Assets.getTexture(Assets.archer));
    public static BasicUnit armory = new BasicUnit("armory", 7, 0, 0, 0, 400,
            null, BaseUnit.Type.BUILDING, "Allows the production of spearmen and shieldbearers", Assets.getTexture(Assets.armory));
    public static BasicUnit blimpWorkshop = new BasicUnit("blimp workshop", 6, 0, 0, 0, 400,
            null, BaseUnit.Type.BUILDING, "Allows production of basic flying units", Assets.getTexture(Assets.blimpWorkshop));
    public static BasicUnit spearman = new BasicUnit("spearman", 3, 2,  3, 1, 200,
            armory, BaseUnit.Type.SOLDIER, "A reliable fast unit that may or may not be broken.", Assets.getTexture(Assets.spearman));
    public static BasicUnit blimp = new BasicUnit("blimp", 3, 1,  3, 2, 300,
            blimpWorkshop, BaseUnit.Type.FLYING, "A flying unit able to traverse terrain without penalty.", Assets.getTexture(Assets.blimp));

    public static VillageUnit village = new VillageUnit();
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
        register(blimpWorkshop);
        register(blimp);
    }

    private static void validateUnits() {
        for(BaseUnit unit : unitList) {
            for(BaseUnit other : unitList) {
                if(!unit.equals(other) && unit.name.equals(other.name)) {
                    throw new IllegalArgumentException("Two or more units of name \"" + unit.name + "\" found");
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
