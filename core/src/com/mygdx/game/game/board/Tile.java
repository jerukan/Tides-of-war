package com.mygdx.game.game.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Position;

public class Tile {

    private Position position;
    private Sprite sprite;
    private Texture rect;

    public Tile(Position position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
        sprite.setSize(Constants.TILE_SIZE, Constants.TILE_SIZE);
        Pixmap pixmap = new Pixmap((int)(sprite.getWidth()), (int)(sprite.getHeight()), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawRectangle(0, (int)(sprite.getY()), pixmap.getWidth(), pixmap.getHeight());
        rect = new Texture(pixmap);
        pixmap.dispose();
    }

    public void render(Batch batch) {
        sprite.draw(batch);
        batch.draw(rect, sprite.getX(), sprite.getY());
    }

    public void setSpritePosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void dispose() {
        rect.dispose();
    }
}