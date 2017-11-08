package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.Input;
import com.mygdx.game.game.gameunits.AllUnits;
import com.mygdx.game.util.SpriteManager;

public class Main extends Game {
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		GameState.instance = new GameState();
		GameState.instance.init();
		GameState.instance.reset();
		GameState.instance.inputs.addProcessor(GameState.instance.uiManager.getStage());
		GameState.instance.inputs.addProcessor(new Input(GameState.instance));
		Gdx.input.setInputProcessor(GameState.instance.inputs);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		GameState.instance.render(batch);
	}

	@Override
	public void resize (int width, int height) {
		GameState.instance.resize(width, height);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		SpriteManager.dispose();
		AllUnits.dispose();
		GameState.instance.dispose();
	}
}
