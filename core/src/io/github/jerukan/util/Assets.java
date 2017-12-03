package io.github.jerukan.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<Texture> grass1 = new AssetDescriptor<Texture>("tiles/grassland_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> desert1 = new AssetDescriptor<Texture>("tiles/desert_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> hill1 = new AssetDescriptor<Texture>("tiles/hill_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> highland1 = new AssetDescriptor<Texture>("tiles/highland_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> mountain1 = new AssetDescriptor<Texture>("tiles/mountain_tile1.png", Texture.class);

    public static final AssetDescriptor<Texture> spearman = new AssetDescriptor<Texture>("units/spearman.png", Texture.class);
    public static final AssetDescriptor<Texture> footman = new AssetDescriptor<Texture>("units/footman.png", Texture.class);
    public static final AssetDescriptor<Texture> archer = new AssetDescriptor<Texture>("units/archer.png", Texture.class);
    public static final AssetDescriptor<Texture> wall = new AssetDescriptor<Texture>("units/wall.png", Texture.class);
    public static final AssetDescriptor<Texture> village = new AssetDescriptor<Texture>("units/village.png", Texture.class);
    public static final AssetDescriptor<Texture> armory = new AssetDescriptor<Texture>("units/armory.png", Texture.class);

    public static final Skin uiskin = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public static void load() {
        assetManager.load(grass1);
        assetManager.load(desert1);
        assetManager.load(hill1);
        assetManager.load(highland1);
        assetManager.load(mountain1);

        assetManager.load(spearman);
        assetManager.load(footman);
        assetManager.load(archer);
        assetManager.load(wall);
        assetManager.load(village);
        assetManager.load(armory);
    }

    public static Texture getTexture(AssetDescriptor<Texture> asset) {
        return assetManager.get(asset);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
