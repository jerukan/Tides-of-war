package io.github.jerukan.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.jerukan.util.BooleanFlag;

/** Initialize buttons for the tables inside the constructor of extended classes */

public abstract class ButtonGroup implements Menu {

    protected Table table;
    private Screen screen;

    /** Possibly multiple flags that this button group will check to determine its visibility */
    private BooleanFlag[] visibilityFlags;

    /** This ButtonGroup will always be visible
     * @param screen the screen this button group will be displayed on */
    public ButtonGroup(Screen screen) {
        this.screen = screen;
        table = new Table();
        visibilityFlags = new BooleanFlag[]{new BooleanFlag()};
    }

    public ButtonGroup(Screen screen, BooleanFlag[] flags) {
        this.screen = screen;
        table = new Table();
        visibilityFlags = flags;
    }

    public abstract void updateVisibility();

    public Table getTable() {
        return table;
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
