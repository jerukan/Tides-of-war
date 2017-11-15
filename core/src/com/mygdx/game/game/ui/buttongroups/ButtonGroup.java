package com.mygdx.game.game.ui.buttongroups;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class ButtonGroup {

    protected Table table;

    public ButtonGroup() {
        table = new Table();
    }

    public Table getTable() {
        return table;
    }
}
