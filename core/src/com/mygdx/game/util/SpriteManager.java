package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Texture;

public class SpriteManager {

    public static final Texture grass1 = new Texture("tiles/grassland_tile1.bmp");

    public static final Texture spearman = new Texture("units/spearman.bmp");

    public static void dispose() {
        grass1.dispose();
        spearman.dispose();
    }
}
