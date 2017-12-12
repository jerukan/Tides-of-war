package io.github.jerukan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.gameunits.UnitRegistry;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Colors;

import java.util.Scanner;

public class Main extends Game {

	private Scanner in = new Scanner(System.in);

	@Override
	public void create () {
        //--------------------RANDOM SHIT START-------------------------------//
        System.out.println("+--------------+\nenter player 1's name");
        String p1 = in.nextLine();
        System.out.println("enter player2's name");
        String p2 = in.nextLine();
        System.out.println("\nBASIC INSTRUCTIONS\n" +
                "You will see a nice board in front of yon\n" +
                "This is a turn based game\n" +
                "To do anything, right click a tile on the board\n" +
                "You will see options pop up, so click one of the buttons\n" +
                "Take into account your money and unit upkeep\n" +
                "Congratulations you now know the basics\n");
        //--------------------RANDOM SHIT END----------------------------//
		Assets.load();
		Assets.assetManager.finishLoading();
		UnitRegistry.init();
		GameState.instance = new GameState(p1 , p2);
		GameState.instance.init();
		WorldRenderer.init();
		GameState.instance.reset();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(Colors.GAME_BACKGROUND_COLOR.r, Colors.GAME_BACKGROUND_COLOR.g, Colors.GAME_BACKGROUND_COLOR.b, Colors.GAME_BACKGROUND_COLOR.a);
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
		UnitRegistry.dispose();
		GameState.instance.dispose();
		WorldRenderer.dispose();
	}
}
