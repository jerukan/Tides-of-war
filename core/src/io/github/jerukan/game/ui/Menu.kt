package io.github.jerukan.game.ui

import com.badlogic.gdx.scenes.scene2d.ui.Table

/** Anything that is displayed on the screen basically
 * Doesn't matter if it's a bunch of buttons, a bit of text, or a meme, it will (hopefully) display
 * Uses exactly one table, no more, no less  */

abstract class Menu {

    var table = Table()
        protected set

    /** Called to update the content of the menu or its visibility itself  */
    abstract fun updateVisibility()
}
