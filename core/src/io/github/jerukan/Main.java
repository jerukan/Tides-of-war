package io.github.jerukan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.util.Assets;

public class Main extends Game {

	@Override
	public void create () {
		Assets.load();
		Assets.assetManager.finishLoading();
		AllUnits.init();
		GameState.instance = new GameState();
		WorldRenderer.init();
		GameState.instance.init();
		GameState.instance.reset();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		WorldRenderer.render();
	}

	@Override
	public void resize (int width, int height) {
		WorldRenderer.resize(width, height);
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		AllUnits.dispose();
		GameState.instance.dispose();
		WorldRenderer.dispose();
	}
}
