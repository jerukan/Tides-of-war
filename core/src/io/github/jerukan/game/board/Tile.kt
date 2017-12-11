package io.github.jerukan.game.board

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import io.github.jerukan.util.Assets
import io.github.jerukan.util.Constants
import io.github.jerukan.util.Position

class Tile(private val position: Position, val height: Float) {
    val sprite: Sprite
    private val rect: Texture
    val terrain: Terrain.TerrainInfo

    val speedConsump: Int
        get() = terrain.speedConsump

    init {
        terrain = Terrain.terrainInfoFromHeight(height)
        sprite = Sprite(terrain.texture)
        sprite.setSize(Constants.TILE_SIZE.toFloat(), Constants.TILE_SIZE.toFloat())
        val pixmap = Pixmap(sprite.width.toInt(), sprite.height.toInt(), Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.BLACK)
        pixmap.drawRectangle(0, sprite.y.toInt(), pixmap.width, pixmap.height)
        rect = Texture(pixmap)
        pixmap.dispose()
    }

    fun render(batch: Batch) {
        batch.draw(sprite.texture, sprite.x, sprite.y, sprite.width, sprite.height)
        batch.draw(rect, sprite.x, sprite.y)
    }

    fun setSpritePosition(x: Float, y: Float) {
        sprite.setPosition(x, y)
    }

    fun dispose() {
        rect.dispose()
    }
}