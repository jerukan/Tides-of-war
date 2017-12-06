package io.github.jerukan.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<Texture> grass1 = new AssetDescriptor<>("tiles/grassland_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> desert1 = new AssetDescriptor<>("tiles/desert_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> hill1 = new AssetDescriptor<>("tiles/hill_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> highland1 = new AssetDescriptor<>("tiles/highland_tile1.png", Texture.class);
    public static final AssetDescriptor<Texture> mountain1 = new AssetDescriptor<>("tiles/mountain_tile1.png", Texture.class);

    public static final AssetDescriptor<Texture> spearman = new AssetDescriptor<>("units/spearman.png", Texture.class);
    public static final AssetDescriptor<Texture> footman = new AssetDescriptor<>("units/footman.png", Texture.class);
    public static final AssetDescriptor<Texture> archer = new AssetDescriptor<>("units/archer.png", Texture.class);
    public static final AssetDescriptor<Texture> wall = new AssetDescriptor<>("units/wall.png", Texture.class);
    public static final AssetDescriptor<Texture> village = new AssetDescriptor<>("units/village.png", Texture.class);
    public static final AssetDescriptor<Texture> armory = new AssetDescriptor<>("units/armory.png", Texture.class);
    public static final AssetDescriptor<Texture> goldmine = new AssetDescriptor<>("units/goldmine.png", Texture.class);
    public static final AssetDescriptor<Texture> farm = new AssetDescriptor<>("units/farm.png", Texture.class);

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
        assetManager.load(goldmine);
        assetManager.load(farm);
    }

    public static Texture getTexture(AssetDescriptor<Texture> asset) {
        return assetManager.get(asset);
    }

    private static Animation<TextureRegion> getAnimation(AssetDescriptor<Texture> texture, int tileWidth, int tileHeight, float frameDuration) {
        int frame_cols = assetManager.get(texture).getHeight() / tileWidth;
        int frame_rows = assetManager.get(texture).getWidth() / tileHeight;
        TextureRegion[][] tmp = TextureRegion.split(assetManager.get(texture), tileWidth, tileHeight);
        TextureRegion[] walkFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(frameDuration, walkFrames);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
