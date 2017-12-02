package io.github.jerukan.game.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

public class Tile {

    private Position position;
    private Sprite sprite;
    private Texture rect;

    private float height;
    private Terrain.TerrainInfo terrain;

    public Tile(Position position, float height) {
        this.position = position;
        this.height = height;
        terrain = Terrain.terrainInfoFromHeight(height);
        sprite = new Sprite(terrain.texture);
        sprite.setSize(Constants.TILE_SIZE, Constants.TILE_SIZE);
        Pixmap pixmap = new Pixmap((int)(sprite.getWidth()), (int)(sprite.getHeight()), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawRectangle(0, (int)(sprite.getY()), pixmap.getWidth(), pixmap.getHeight());
        rect = new Texture(pixmap);
        pixmap.dispose();
    }

    public void render(Batch batch) {
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        batch.draw(rect, sprite.getX(), sprite.getY());
    }

    public void setSpritePosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getHeight() {
        return height;
    }

    public int getSpeedConsump() {
        return terrain.speedConsump;
    }

    public void dispose() {
        rect.dispose();
    }
}