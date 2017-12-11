package io.github.jerukan.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import io.github.jerukan.util.Constants

class BoardCamera(val camera: OrthographicCamera) {

    var camOriginX: Float = 0.toFloat()
    var camOriginY: Float = 0.toFloat()

    var camOffsetX: Float = 0.toFloat()
    var camOffsetY: Float = 0.toFloat()

    private var boardCamVelX = 0f
    private var boardCamVelY = 0f

    private var boardCamAccelX = 0f
    private var boardCamAccelY = 0f

    var boardCamSlowingX = false
    var boardCamSlowingY = false

    private var boardCamZoom = 1f
    private var boardCamTargetZoom = 1f

    init {

        camOriginX = (Gdx.graphics.width / 2).toFloat()
        camOriginY = (Gdx.graphics.height / 2).toFloat()

        camOffsetX = camera.position.x - camOriginX
        camOffsetY = camera.position.y - camOriginY
    }

    fun update() {
        updateOffsets()

        if (boardCamSlowingX || boardCamSlowingY) {
            slowCamera()
        }
        setCameraVelX()
        setCameraVelY()
        zoomBoardCamera()

        camera.translate(boardCamVelX, boardCamVelY)

        camera.zoom = MathUtils.clamp(boardCamZoom, Constants.CAMERA_ZOOM_MAX, Constants.CAMERA_ZOOM_MIN)

        camera.update()
    }

    fun setCameraVelX() {
        if (Math.abs(boardCamVelX) > Constants.CAMERA_SPEED_MAX) {
            boardCamVelX = MathUtils.clamp(boardCamVelX, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX)
        } else {
            boardCamVelX += boardCamAccelX
        }
    }


    fun setCameraVelY() {
        if (Math.abs(boardCamVelY) > Constants.CAMERA_SPEED_MAX) {
            boardCamVelY = MathUtils.clamp(boardCamVelY, -Constants.CAMERA_SPEED_MAX, Constants.CAMERA_SPEED_MAX)
        } else {
            boardCamVelY += boardCamAccelY
        }
    }

    fun setBoardCamAccelX(x: Float) {
        boardCamAccelX = x
    }

    fun setBoardCamAccelY(y: Float) {
        boardCamAccelY = y
    }

    fun slowCamera() {
        val xdir = Math.abs(boardCamVelX) / boardCamVelX
        val ydir = Math.abs(boardCamVelY) / boardCamVelY
        if (boardCamAccelX == 0f) {
            if (Math.abs(boardCamVelX) >= Constants.CAMERA_ZERO_THRESHOLD) {
                boardCamVelX -= Constants.CAMERA_SPEED_ACCEL * xdir
            } else {
                boardCamVelX = 0f
            }
        }
        if (boardCamAccelY == 0f) {
            if (Math.abs(boardCamVelY) >= Constants.CAMERA_ZERO_THRESHOLD) {
                boardCamVelY -= Constants.CAMERA_SPEED_ACCEL * ydir
            } else {
                boardCamVelY = 0f
            }
        }
    }

    fun setBoardCamTargetZoom(`val`: Float) {
        boardCamTargetZoom += `val`
        boardCamTargetZoom = MathUtils.clamp(boardCamTargetZoom, Constants.CAMERA_ZOOM_MAX, Constants.CAMERA_ZOOM_MIN)
    }

    fun zoomBoardCamera() {
        if (Math.abs(boardCamZoom) != boardCamTargetZoom) {
            boardCamZoom += Constants.CAMERA_ZOOM_SPEED_MAX * (Math.abs(boardCamTargetZoom - camera.zoom) / (boardCamTargetZoom - camera.zoom))
            boardCamZoom = MathUtils.clamp(boardCamZoom, Constants.CAMERA_ZOOM_MAX, Constants.CAMERA_ZOOM_MIN)
        }
    }

    fun updateOrigins() {
        camOriginX = (Gdx.graphics.width / 2).toFloat()
        camOriginY = (Gdx.graphics.height / 2).toFloat()
    }

    fun updateOffsets() {
        updateOrigins()
        camOffsetX = camera.position.x - camOriginX
        camOffsetY = camera.position.y - camOriginY
    }
}
