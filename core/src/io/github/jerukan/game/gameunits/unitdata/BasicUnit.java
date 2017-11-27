package io.github.jerukan.game.gameunits.unitdata;

import com.badlogic.gdx.graphics.Texture;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;

/** All the units that don't have anything very special to them */

public class BasicUnit extends BaseUnit {

    public BasicUnit(String name, int health, int attack, int speed, int range, int cost, Type type, Texture texture) {
        this.name = name;
        baseHealth = health;
        baseAttack = attack;
        baseSpeed = speed;
        baseRange = range;

        baseCost = cost;

        this.type = type;

        setTexture(texture);
    }

    @Override
    public boolean canBuildCondition(Player owner) {
        return true;
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