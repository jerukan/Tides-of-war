package io.github.jerukan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Input;
import io.github.jerukan.game.World;
import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.util.Assets;

public class Main extends Game {

	@Override
	public void create () {
		Assets.load();
		Assets.assetManager.finishLoading();
		GameState.instance = new GameState();
		World.init();
		GameState.instance.init();
		GameState.instance.reset();
	}

	@Override
	public void render () {
		GameState.instance.update();
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		World.render();
	}

	@Override
	public void resize (int width, int height) {
		World.resize(width, height);
	}
	
	@Override
	public void dispose () {
		Assets.dispose();
		AllUnits.dispose();
		GameState.instance.dispose();
		World.dispose();
	}
}
