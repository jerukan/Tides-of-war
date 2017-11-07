package com.mygdx.game.game.gameunits;

/*
* A base class for all the stats and actions of a single type of unit
* Not actually the object that gets placed on the board
* */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.util.Constants;

public abstract class BaseUnit {

    public final String name;
    public final int baseHealth;
    public final int baseAttack;
    public final int baseSpeed;
    public final int baseRange;

    public final Type type;

    private Sprite sprite;

    public BaseUnit(String name, int baseHealth, int baseAttack, int baseSpeed, int baseRange, Type type) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseSpeed = baseSpeed;
        this.baseRange = baseRange;

        this.type = type;

        sprite = new Sprite();
    }

    protected void setSprite(Texture texture) {
        this.sprite.setTexture(texture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void onTargetAction(Unit self, Unit target) {
        target.takeDamage(self.getCurrentAttack());
    };

    public abstract void onCreation(Unit self);
    public abstract void onDeath(Unit self);

    public abstract void onTurnStart(Unit self);
    public abstract void onTurnEnd(Unit self);

    public enum Type {
        BUILDING, SOLDIER
    }
}