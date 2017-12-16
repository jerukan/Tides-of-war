package io.github.jerukan.game;

import com.badlogic.gdx.graphics.g2d.Batch;

/** Any class that usually has a manager it get info from and renders based on that */
public interface Renderer {

    void init();

    void resize(int width, int height);

    void render(Batch batch);

    void render(Batch batch, float stateTime);
}
