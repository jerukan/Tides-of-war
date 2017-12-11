package io.github.jerukan.game.ui

import com.badlogic.gdx.scenes.scene2d.ui.Table
import io.github.jerukan.util.NamedFlag

/** Initialize buttons for the tables inside the constructor of extended classes  */

abstract class ButtonGroup : Menu {

    /** Possibly multiple flags that this button group will check to determine its visibility  */
    private var visibilityFlags: Array<NamedFlag>? = null

    /** This ButtonGroup will always be visible  */
    constructor() {
        visibilityFlags = arrayOf(NamedFlag())
    }

    constructor(flags: Array<NamedFlag>) {
        table = Table()
        visibilityFlags = flags
    }

    fun resetFlags() {
        for (flag in visibilityFlags!!) {
            flag.reset()
        }
    }

    /** Searches for a flag of a given name
     * @param name the flag's hardcoded name
     * @return the NamedFlag with the matching name
     */
    protected fun flagFromArray(name: String): NamedFlag {
        for (flag in visibilityFlags!!) {
            if (flag.name == name) {
                return flag
            }
        }
        throw NullPointerException("Could not find flag of name \"" + name + "\"")
    }
}
