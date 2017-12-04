package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.jerukan.util.NamedFlag;

/** Initialize buttons for the tables inside the constructor of extended classes */

public abstract class ButtonGroup extends Menu {

    /** Possibly multiple flags that this button group will check to determine its visibility */
    private NamedFlag[] visibilityFlags;

    /** This ButtonGroup will always be visible */
    public ButtonGroup() {
        visibilityFlags = new NamedFlag[]{new NamedFlag()};
    }

    public ButtonGroup(NamedFlag[] flags) {
        table = new Table();
        visibilityFlags = flags;
    }

    public void resetFlags() {
        for(NamedFlag flag : visibilityFlags) {
            flag.reset();
        }
    }

    /** Searches for a flag of a given name
     * @param name the flag's hardcoded name
     * @return the NamedFlag with the matching name */
    protected NamedFlag flagFromArray(String name) {
        for(NamedFlag flag : visibilityFlags) {
            if(flag.getName().equals(name)) {
                return flag;
            }
        }
        throw new NullPointerException("Could not find flag of name \"" + name + "\"");
    }
}
