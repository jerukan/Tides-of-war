package io.github.jerukan.game.board

import com.badlogic.gdx.graphics.Texture
import io.github.jerukan.util.Assets

object Terrain {

    var desert = TerrainInfo("desert", 0.2f, 1, Assets.getTexture(Assets.desert1))
    var grassland = TerrainInfo("grassland", 0.6f, 1, Assets.getTexture(Assets.grass1))
    var hill = TerrainInfo("hill", 0.9f, 2, Assets.getTexture(Assets.hill1))
    var highland = TerrainInfo("highland", 0.95f, 3, Assets.getTexture(Assets.highland1))
    var mountain = TerrainInfo("mountain", 1f, 4, Assets.getTexture(Assets.mountain1))

    var terrains = arrayOf(desert, grassland, hill, highland, mountain)

    class TerrainInfo internal constructor(val name: String, height_threshold: Float, val speedConsump: Int, val texture: Texture) {
        val height_threshold: Double

        init {
            this.height_threshold = height_threshold.toDouble()
        }
    }

    /** Whatever later  */
    fun sortTerrain() {

    }

    fun terrainInfoFromHeight(height: Float): TerrainInfo {
        for (t in terrains) {
            if (height < t.height_threshold) {
                return t
            }
        }
        return terrains[terrains.size - 1]
    }
}
