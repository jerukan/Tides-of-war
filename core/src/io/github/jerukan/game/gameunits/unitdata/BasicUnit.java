package io.github.jerukan.game.gameunits.unitdata;

import com.badlogic.gdx.graphics.Texture;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.*;

/** All the units that don't have anything very special to them */

public class BasicUnit extends BaseUnit {

    public BasicUnit(String name, int health, int attack, int speed, int range, int cost, BaseUnit requiredUnit, Type type, String description, Texture texture, boolean oddAnim) {
        this.name = name;
        baseHealth = health;
        baseAttack = attack;
        baseSpeed = speed;
        baseRange = range;

        baseCost = cost;

        this.requiredUnit = requiredUnit;

        this.type = type;

        this.description = description;

        if(this.type == Type.SOLDIER) {
            actions = new UnitAction[]{new MoveAction(this), new AttackAction(this), new DismissAction(this)};
            baseUpkeep = 1;
        }
        else if(this.type == Type.BUILDING) {
            actions = new UnitAction[]{new DismissAction(this)};
            baseUpkeep = 0;
        }
        else if(this.type == Type.FLYING) {
            actions = new UnitAction[]{new MoveFlyAction(this), new AttackAction(this), new DismissAction(this)};
            baseUpkeep = 1;
        }

        setTexture(texture);
        oddAnimation = oddAnim;
    }

    @Override
    public boolean canBuild(Player owner) {
        return requiredUnit == null || GameState.instance.unitState.playerHasUnit(owner, requiredUnit.id);
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
