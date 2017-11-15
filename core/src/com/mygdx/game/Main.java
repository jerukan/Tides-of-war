package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.Input;
import com.mygdx.game.game.gameunits.AllUnits;
import com.mygdx.game.util.Assets;

public class Main extends Game {

	@Override
	public void create () {
		GameState.instance = new GameState();
		GameState.instance.init();
		GameState.instance.reset();
		GameState.instance.inputs.addProcessor(GameState.instance.uiManager.getStage());
		GameState.instance.inputs.addProcessor(new Input(GameState.instance));
		Gdx.input.setInputProcessor(GameState.instance.inputs);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		GameState.instance.render();
	}

	@Override
	public void resize (int width, int height) {
		GameState.instance.resize(width, height);
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		AllUnits.dispose();
		GameState.instance.dispose();
	}
}
