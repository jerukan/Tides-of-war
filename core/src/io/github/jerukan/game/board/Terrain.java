package io.github.jerukan.game.board;

import com.badlogic.gdx.graphics.Texture;
import io.github.jerukan.util.Assets;

public class Terrain {

    static class TerrainInfo {
        public final double height_threshold;
        public final int speedConsump;
        public final Texture texture;
        TerrainInfo(float height_threshold, int speedConsump, Texture texture) {
            this.height_threshold = height_threshold;
            this.speedConsump = speedConsump;
            this.texture = texture;
        }
    }

    public static TerrainInfo desert = new TerrainInfo(0.2f, 1, Assets.getTexture(Assets.desert1));
    public static TerrainInfo grassland = new TerrainInfo(0.6f, 1, Assets.getTexture(Assets.grass1));
    public static TerrainInfo hill = new TerrainInfo(0.9f, 2, Assets.getTexture(Assets.hill1));
    public static TerrainInfo highland = new TerrainInfo(0.95f, 3, Assets.getTexture(Assets.highland1));
    public static TerrainInfo mountain = new TerrainInfo(1, 4, Assets.getTexture(Assets.mountain1));

    public static TerrainInfo[] terrains = {
            desert,
            grassland,
            hill,
            highland,
            mountain
    };

    /** Whatever later */
    public static void sortTerrain() {

    }

    public static TerrainInfo terrainInfoFromHeight(float height) {
        for(TerrainInfo t : terrains) {
            if(height < t.height_threshold) {
                return t;
            }
        }
        return terrains[terrains.length - 1];
    }
}
