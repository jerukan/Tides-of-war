package io.github.jerukan.util;

/** Wraps a boolean with a string name */
public class BooleanFlag {

    private String name;

    private boolean defaultState;
    private boolean state;

    public BooleanFlag() {
        name = "flag";
        defaultState = true;
        state = true;
    }

    public BooleanFlag(String name, boolean defaultState) {
        this.name = name;
        this.defaultState = defaultState;
        state = this.defaultState;
    }

    public void setState(boolean in) {
        state = in;
    }

    public void reset() {
        state = defaultState;
    }

    public boolean getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
