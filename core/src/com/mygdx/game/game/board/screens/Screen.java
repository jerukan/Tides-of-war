package com.mygdx.game.game.board.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class Screen {

    private Table table;
    private Button[] buttons;

    public Screen() {

    }

    public void initListeners() {

    }
}
