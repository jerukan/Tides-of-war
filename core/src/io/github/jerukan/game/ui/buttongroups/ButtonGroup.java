package io.github.jerukan.game.ui.buttongroups;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class ButtonGroup {

    protected Table table;

    public ButtonGroup() {
        table = new Table();
    }

    public abstract void updateVisibility();

    public Table getTable() {
        return table;
    }
}
