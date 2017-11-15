package com.mygdx.game.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.util.Constants;

public class Input implements InputProcessor {

    private GameState gameState;

    public Input(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.A) {
            gameState.setCameraX(-Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.RIGHT || keycode == Keys.D) {
            gameState.setCameraX(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.UP || keycode == Keys.W) {
            gameState.setCameraY(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.DOWN || keycode == Keys.S) {
            gameState.setCameraY(-Constants.CAMERA_SPEED);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.A || keycode == Keys.D) {
            gameState.setCameraX(0);
        }
        if(keycode == Keys.UP || keycode == Keys.DOWN || keycode == Keys.W || keycode == Keys.S) {
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
        if(button == Buttons.LEFT) {
            GameState.instance.boardManager.selectedPositionAction();
        }
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
