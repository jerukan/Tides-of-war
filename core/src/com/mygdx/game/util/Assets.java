package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<Texture> grass1 = new AssetDescriptor<Texture>("tiles/grassland_tile1.bmp", Texture.class);

    public static final AssetDescriptor<Texture> spearman = new AssetDescriptor<Texture>("units/spearman.png", Texture.class);

    public static final Skin uiskin = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public static void load() {
        assetManager.load(grass1);

        assetManager.load(spearman);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
