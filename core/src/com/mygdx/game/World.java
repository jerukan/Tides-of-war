package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.Input;
import com.mygdx.game.util.SpriteManager;

public class World extends ApplicationAdapter {
	SpriteBatch batch;
	GameState gameState;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = new GameState();
		gameState.init();
		gameState.reset();
		gameState.inputs.addProcessor(gameState.uiManager.getStage());
		gameState.inputs.addProcessor(new Input(gameState));
		Gdx.input.setInputProcessor(gameState.inputs);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		gameState.render(batch);
	}

	@Override
	public void resize (int width, int height) {
		gameState.boardCam.setToOrtho(false, width, height);
		gameState.uiCam.setToOrtho(false, width, height);
		gameState.boardManager.resize();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		SpriteManager.dispose();
		gameState.dispose();
	}
}
