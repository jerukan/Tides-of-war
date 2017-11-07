package com.mygdx.game.game.gameunits.unitfiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.game.gameunits.Unit;
import com.mygdx.game.util.Constants;

/** A base class for all the stats and actions of a single type of unit
 * Not actually the object that gets placed on the board */

public abstract class BaseUnit {

    public final String name;
    public final int baseHealth;
    public final int baseAttack;
    public final int baseSpeed;
    public final int baseRange;

    public final Type type;

    private Texture texture;

    /** Creates a reference unit containing stats of the unit and actions it can perform
     * @param name the name of the unit to be used for display and reference
     * @param baseHealth default health of a unit
     * @param baseAttack default attack of a unit
     * @param baseSpeed default speed of a unit
     * @param baseRange default range of a unit
     * @param type determines if the unit is a BUILDING or SOLDIER */
    public BaseUnit(String name, int baseHealth, int baseAttack, int baseSpeed, int baseRange, Type type) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseSpeed = baseSpeed;
        this.baseRange = baseRange;

        this.type = type;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    /** Determines what happens when the unit performs an action on a specified target
     * Can perform actions other than dealing damage to a single unit
     * @param self the existing selected unit
     * @param target the existing targeted unit */
    public void onTargetAction(Unit self, Unit target) {
        target.takeDamage(self.getCurrentAttack());
    };

    public abstract void onCreation(Unit self);
    public abstract void onDeath(Unit self);

    public abstract void onTurnStart(Unit self);
    public abstract void onTurnEnd(Unit self);

    public void dispose() {
        texture.dispose();
    }

    public enum Type {
        BUILDING, SOLDIER
    }
}