package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/** Anything that is displayed on the screen basically
 * Doesn't matter if it's a bunch of buttons, a bit of text, or a meme, it will (hopefully) display */

public interface Menu {

    Table getTable();

    void updateVisibility();
}
