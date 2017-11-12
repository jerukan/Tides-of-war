package com.mygdx.game.game.board.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.util.Assets;

public class GameScreen extends Screen {

    public GameScreen(Stage stage) {
        super(stage);

        Table testTable = new Table();
        final TextButton button = new TextButton("Click me", Assets.uiskin, "default");
        testTable.setWidth(stage.getWidth());
        testTable.align(Align.center|Align.top);
        testTable.setPosition(0, Gdx.graphics.getHeight());
        testTable.pad(50);
        testTable.add(button);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                button.setText("You clicked the button");
            }
        });

        tables.put("test", testTable);

        addTablesToStage();
    }
}