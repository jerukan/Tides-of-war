package io.github.jerukan.game

import com.badlogic.gdx.Input.Buttons
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import io.github.jerukan.util.Constants

class Input : InputProcessor {

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Keys.LEFT || keycode == Keys.A) {
            WorldRenderer.boardCam.setBoardCamAccelX(-Constants.CAMERA_SPEED_ACCEL)
            WorldRenderer.boardCam.boardCamSlowingX = false
        }
        if (keycode == Keys.RIGHT || keycode == Keys.D) {
            WorldRenderer.boardCam.setBoardCamAccelX(Constants.CAMERA_SPEED_ACCEL)
            WorldRenderer.boardCam.boardCamSlowingX = false
        }
        if (keycode == Keys.UP || keycode == Keys.W) {
            WorldRenderer.boardCam.setBoardCamAccelY(Constants.CAMERA_SPEED_ACCEL)
            WorldRenderer.boardCam.boardCamSlowingY = false
        }
        if (keycode == Keys.DOWN || keycode == Keys.S) {
            WorldRenderer.boardCam.setBoardCamAccelY(-Constants.CAMERA_SPEED_ACCEL)
            WorldRenderer.boardCam.boardCamSlowingY = false
        }
        //        if(keycode == Keys.EQUALS) {
        //            WorldRenderer.setBoardCamZoom(-Constants.CAMERA_ZOOM_SPEED_MAX);
        //        }
        //        if(keycode == Keys.MINUS) {
        //            WorldRenderer.setBoardCamZoom(Constants.CAMERA_ZOOM_SPEED_MAX);
        //        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        if (keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.A || keycode == Keys.D) {
            WorldRenderer.boardCam.setBoardCamAccelX(0f)
            WorldRenderer.boardCam.boardCamSlowingX = true
        }
        if (keycode == Keys.UP || keycode == Keys.DOWN || keycode == Keys.W || keycode == Keys.S) {
            WorldRenderer.boardCam.setBoardCamAccelY(0f)
            WorldRenderer.boardCam.boardCamSlowingY = true
        }
        //        if(keycode == Keys.EQUALS || keycode == Keys.MINUS) {
        //            WorldRenderer.setBoardCamZoom(0);
        //        }
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (button == Buttons.RIGHT) {
            GameState.instance.boardManager.selectedPositionActionRight()
        }
        if (button == Buttons.LEFT) {
            GameState.instance.boardManager.selectedPositionActionLeft()
            WorldRenderer.uiRenderer.clear()
        }
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        if (amount == 1) {
            WorldRenderer.boardCam.setBoardCamTargetZoom(Constants.CAMERA_ZOOM_SPEED_MAX)
        } else if (amount == -1) {
            WorldRenderer.boardCam.setBoardCamTargetZoom(-Constants.CAMERA_ZOOM_SPEED_MAX)
        }
        return false
    }
}
