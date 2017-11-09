package com.mygdx.game.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.game.GameState;

public class UIManager {

    private Stage stage;
    private Skin skin;
    private Table table;

    public ScreenViewport viewport;

    private OrthographicCamera camera;

    public UIManager(OrthographicCamera camera) {
        viewport = new ScreenViewport();
        this.camera = camera;
        table = new Table();
        stage = new Stage(viewport);
    }

    public void init() {
        table = new Table();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        final TextButton button = new TextButton("Click me", skin, "default");
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.pad(50);
        table.add(button);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                button.setText("You clicked the button");
            }
        });

        stage.addActor(table);
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width , height);
        viewport.update(width, height, true);
    }

    public void render() {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}