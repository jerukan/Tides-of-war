package io.github.jerukan.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import io.github.jerukan.util.Constants;

public class BoardCamera {

    private OrthographicCamera camera;

    public float camOriginX, camOriginY;

    public float camOffsetX, camOffsetY;

    private float boardCamVelX = 0;
    private float boardCamVelY = 0;

    private float boardCamAccelX = 0;
    private float boardCamAccelY = 0;

    public boolean boardCamSlowingX = false;
    public boolean boardCamSlowingY = false;

    private float boardCamZoom = 1;
    private float boardCamTargetZoom = 1;

    public BoardCamera(OrthographicCamera camera) {
        this.camera = camera;

        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;

        camOffsetX = camera.position.x - camOriginX;
        camOffsetY = camera.position.y - camOriginY;
    }

    public void update() {
        updateOffsets();

        if(boardCamSlowingX || boardCamSlowingY) {
            slowCamera();
        }
        setCameraVelX();
        setCameraVelY();
        zoomBoardCamera();

        camera.translate(boardCamVelX, boardCamVelY);

        camera.zoom = MathUtils.clamp(boardCamZoom, Constants.CAMERA_ZOOM_MAX, 1);

        camera.update();
    }

    public void setCameraVelX() {
        if(Math.abs(boardCamVelX) > Constants.CAMERA_SPEED_MAX) {
            boardCamVelX = MathUtils.clamp(boardCamVelX, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX);
        }
        else {
            boardCamVelX += boardCamAccelX;
        }
    }


    public void setCameraVelY() {
        if(Math.abs(boardCamVelY) > Constants.CAMERA_SPEED_MAX) {
            boardCamVelY = MathUtils.clamp(boardCamVelY, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX);
        }
        else {
            boardCamVelY += boardCamAccelY;
        }
    }

    public void setBoardCamAccelX(float x) {
        boardCamAccelX = x;
    }

    public void setBoardCamAccelY(float y) {
        boardCamAccelY = y;
    }

    public void slowCamera() {
        float xdir = Math.abs(boardCamVelX) / boardCamVelX;
        float ydir = Math.abs(boardCamVelY) / boardCamVelY;
        if(boardCamAccelX == 0) {
            if (!(Math.abs(boardCamVelX) < Constants.CAMERA_ZERO_THRESHOLD)) {
                boardCamVelX -= Constants.CAMERA_SPEED_ACCEL * xdir;
            }
            else {
                boardCamVelX = 0;
            }
        }
        if(boardCamAccelY == 0) {
            if (!(Math.abs(boardCamVelY) < Constants.CAMERA_ZERO_THRESHOLD)) {
                boardCamVelY -= Constants.CAMERA_SPEED_ACCEL * ydir;
            }
            else {
                boardCamVelY = 0;
            }
        }
    }

    public void setBoardCamTargetZoom(float val) {
        boardCamTargetZoom += val;
        boardCamTargetZoom = MathUtils.clamp(boardCamTargetZoom, Constants.CAMERA_ZOOM_MAX, 1);
    }

    public void zoomBoardCamera() {
        if(Math.abs(boardCamZoom) != boardCamTargetZoom) {
            boardCamZoom += Constants.CAMERA_ZOOM_SPEED_MAX * (Math.abs(boardCamTargetZoom - camera.zoom) / (boardCamTargetZoom - camera.zoom));
            boardCamZoom = MathUtils.clamp(boardCamZoom, Constants.CAMERA_ZOOM_MAX, 1);
        }
    }

    public void updateOffsets() {
        camOffsetX = camera.position.x - camOriginX;
        camOffsetY = camera.position.y - camOriginY;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
