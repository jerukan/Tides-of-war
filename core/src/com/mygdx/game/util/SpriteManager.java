package com.mygdx.game.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class SpriteManager {

    public static AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<Texture> spearman = new AssetDescriptor<Texture>("units/spearman.png", Texture.class);
    public static final AssetDescriptor<Texture> grass1 = new AssetDescriptor<Texture>("tiles/grassland_tile1.bmp", Texture.class);

    public static void load() {
        assetManager.load(spearman);
        assetManager.load(grass1);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
