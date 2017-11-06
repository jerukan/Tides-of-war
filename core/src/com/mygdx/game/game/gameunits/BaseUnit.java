package com.mygdx.game.game.gameunits;

/*
* A base class for all the stats and actions of a single type of unit
* Not actually the object that gets placed on the board
* */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class BaseUnit {

    public final String name;
    public final int baseHealth;
    public final int baseAttack;
    public final int baseSpeed;
    public final int baseRange;

    private Sprite sprite;

    public BaseUnit(String name, int baseHealth, int baseAttack, int baseSpeed, int baseRange) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseSpeed = baseSpeed;
        this.baseRange = baseRange;
    }

    public void setSprite(Texture texture) {
        sprite.setTexture(texture);
    }

    public void render(Batch batch) {
        sprite.draw(batch);
    }

    public abstract void onTargetAction(Unit target);

    public abstract void onCreation();
    public abstract void onDeath();

    public abstract void onTurnStart();
    public abstract void onTurnEnd();
}