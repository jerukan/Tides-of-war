package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/** Anything that is displayed on the screen basically
 * Doesn't matter if it's a bunch of buttons, a bit of text, or a meme, it will (hopefully) display
 * Uses exactly one table, no more, no less */

public abstract class Menu {

    protected Table table = new Table();

    /** Called to update the content of the menu or its visibility itself */
    public abstract void updateVisibility();

    public Table getTable() {
        return table;
    }
}
