package com.mygdx.game.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.util.Constants;

public class Input implements InputProcessor {

    private GameState gameState;

    public Input(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT) {
            gameState.setCameraX(-Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.RIGHT) {
            gameState.setCameraX(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.UP) {
            gameState.setCameraY(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.DOWN) {
            gameState.setCameraY(-Constants.CAMERA_SPEED);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.RIGHT) {
            gameState.setCameraX(0);
        }
        if(keycode == Keys.UP || keycode == Keys.DOWN) {
            gameState.setCameraY(0);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
