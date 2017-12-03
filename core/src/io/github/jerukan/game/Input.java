package io.github.jerukan.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import io.github.jerukan.util.Constants;

public class Input implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.A) {
            WorldRenderer.boardCam.setBoardCamAccelX(-Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCam.boardCamSlowingX = false;
        }
        if(keycode == Keys.RIGHT || keycode == Keys.D) {
            WorldRenderer.boardCam.setBoardCamAccelX(Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCam.boardCamSlowingX = false;
        }
        if(keycode == Keys.UP || keycode == Keys.W) {
            WorldRenderer.boardCam.setBoardCamAccelY(Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCam.boardCamSlowingY = false;
        }
        if(keycode == Keys.DOWN || keycode == Keys.S) {
            WorldRenderer.boardCam.setBoardCamAccelY(-Constants.CAMERA_SPEED_ACCEL);
            WorldRenderer.boardCam.boardCamSlowingY = false;
        }
//        if(keycode == Keys.EQUALS) {
//            WorldRenderer.setBoardCamZoom(-Constants.CAMERA_ZOOM_SPEED_MAX);
//        }
//        if(keycode == Keys.MINUS) {
//            WorldRenderer.setBoardCamZoom(Constants.CAMERA_ZOOM_SPEED_MAX);
//        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.A || keycode == Keys.D) {
            WorldRenderer.boardCam.setBoardCamAccelX(0);
            WorldRenderer.boardCam.boardCamSlowingX = true;
        }
        if(keycode == Keys.UP || keycode == Keys.DOWN || keycode == Keys.W || keycode == Keys.S) {
            WorldRenderer.boardCam.setBoardCamAccelY(0);
            WorldRenderer.boardCam.boardCamSlowingY = true;
        }
//        if(keycode == Keys.EQUALS || keycode == Keys.MINUS) {
//            WorldRenderer.setBoardCamZoom(0);
//        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Buttons.RIGHT) {
            GameState.instance.boardManager.selectedPositionActionRight();
        }
        if(button == Buttons.LEFT) {
            GameState.instance.boardManager.selectedPositionActionLeft();
            WorldRenderer.uiRenderer.clear();
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
        if(amount == 1) {
            WorldRenderer.boardCam.setBoardCamTargetZoom(Constants.CAMERA_ZOOM_SPEED_MAX);
        }
        else if(amount == -1) {
            WorldRenderer.boardCam.setBoardCamTargetZoom(-Constants.CAMERA_ZOOM_SPEED_MAX);
        }
        return false;
    }
}
