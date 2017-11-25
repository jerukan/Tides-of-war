package io.github.jerukan.game;

import com.badlogic.gdx.graphics.Color;

/** This will be you */

public class Player {

    public final String name;
    public final Color color;

    public int money;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        //make sure color is solid
        color.set(color.r, color.g, color.b, 1f);

        money = 100;
    }
}
