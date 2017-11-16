package io.github.jerukan.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import io.github.jerukan.util.Constants;

public class Input implements InputProcessor {

    private GameState gameState;

    public Input(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.A) {
            WorldRenderer.setCameraVelX(-Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.RIGHT || keycode == Keys.D) {
            WorldRenderer.setCameraVelX(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.UP || keycode == Keys.W) {
            WorldRenderer.setCameraVelY(Constants.CAMERA_SPEED);
        }
        if(keycode == Keys.DOWN || keycode == Keys.S) {
            WorldRenderer.setCameraVelY(-Constants.CAMERA_SPEED);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.A || keycode == Keys.D) {
            WorldRenderer.setCameraVelX(0);
        }
        if(keycode == Keys.UP || keycode == Keys.DOWN || keycode == Keys.W || keycode == Keys.S) {
            WorldRenderer.setCameraVelY(0);
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
