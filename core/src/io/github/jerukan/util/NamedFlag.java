package io.github.jerukan.util;

/** Wraps a boolean with a string name */
public class NamedFlag {

    private String name;

    private boolean defaultState;
    private boolean state;

    public NamedFlag() {
        name = "flag";
        defaultState = true;
        state = true;
    }

    public NamedFlag(String name, boolean defaultState) {
        this.name = name.trim();
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
