package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.jerukan.util.BooleanFlag;

/** Initialize buttons for the tables inside the constructor of extended classes */

public abstract class ButtonGroup extends Menu {

    /** Possibly multiple flags that this button group will check to determine its visibility */
    private BooleanFlag[] visibilityFlags;

    /** This ButtonGroup will always be visible */
    public ButtonGroup() {
        visibilityFlags = new BooleanFlag[]{new BooleanFlag()};
    }

    public ButtonGroup(BooleanFlag[] flags) {
        table = new Table();
        visibilityFlags = flags;
    }

    public void resetFlags() {
        for(BooleanFlag flag : visibilityFlags) {
            flag.reset();
        }
    }

    /** Searches for a flag of a given name
     * @param name the flag's hardcoded name
     * @return the BooleanFlag with the matching name */
    protected BooleanFlag flagFromArray(String name) {
        for(BooleanFlag flag : visibilityFlags) {
            if(flag.getName().equals(name)) {
                return flag;
            }
        }
        throw new NullPointerException("Could not find flag of name \"" + name + "\"");
    }
}
