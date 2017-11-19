package io.github.jerukan.util;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;

public class Input implements InputProcessor {

    private GameState gameState;

    public Input(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.A) {
            WorldRenderer.setBoardCamAccelX(-Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCamSlowingX = false;
        }
        if(keycode == Keys.RIGHT || keycode == Keys.D) {
            WorldRenderer.setBoardCamAccelX(Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCamSlowingX = false;
        }
        if(keycode == Keys.UP || keycode == Keys.W) {
            WorldRenderer.setBoardCamAccelY(Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCamSlowingY = false;
        }
        if(keycode == Keys.DOWN || keycode == Keys.S) {
            WorldRenderer.setBoardCamAccelY(-Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCamSlowingY = false;
        }
        if(keycode == Keys.EQUALS) {
            WorldRenderer.setBoardCamZoom(-Constants.CAMERA_ZOOM_SPEED_MAX);
        }
        if(keycode == Keys.MINUS) {
            WorldRenderer.setBoardCamZoom(Constants.CAMERA_ZOOM_SPEED_MAX);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.A || keycode == Keys.D) {
            WorldRenderer.setBoardCamAccelX(0);
            WorldRenderer.boardCamSlowingX = true;
        }
        if(keycode == Keys.UP || keycode == Keys.DOWN || keycode == Keys.W || keycode == Keys.S) {
            WorldRenderer.setBoardCamAccelY(0);
            WorldRenderer.boardCamSlowingY = true;
        }
        if(keycode == Keys.EQUALS || keycode == Keys.MINUS) {
            WorldRenderer.setBoardCamZoom(0);
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
