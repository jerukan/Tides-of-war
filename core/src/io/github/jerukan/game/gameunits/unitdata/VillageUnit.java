package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.util.Assets;

public class VillageUnit extends BaseUnit {

    public VillageUnit() {
        name = "village";
        baseHealth = 20;

        baseCost = 0;
        setTexture(Assets.getTexture(Assets.village));
    }

    @Override
    public void onCreation(Unit self) {

    }

    @Override
    public void onDeath(Unit self) {

    }

    @Override
    public void onTurnStart(Unit self) {

    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
