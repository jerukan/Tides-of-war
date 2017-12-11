package io.github.jerukan.util

/** The number Mason, what do they mean?  */

object Constants {

    val GAME_NAME = "Tides of War"

    val BOARD_WIDTH = 11
    val BOARD_HEIGHT = 11

    val TILE_SIZE = 60

    val PERLIN_SAMPLES = 10 //SHOULDN'T EXIST IF PERLIN NOISE WAS ACTUALLY DONE RIGHT

    val TILE_MENU_OFFSET = TILE_SIZE * 0.2f

    val UNIT_SIZE_RATIO = 0.7f   // multiplied by tile size for sprite size

    val UNIT_SIZE = TILE_SIZE * UNIT_SIZE_RATIO

    val CAMERA_SPEED_MAX = 5f // in pixels for now
    val CAMERA_SPEED_ACCEL = 0.25f
    val CAMERA_ZERO_THRESHOLD = 0.3f

    val CAMERA_ZOOM_MAX = 0.3f
    val CAMERA_ZOOM_MIN = 2f
    val CAMERA_ZOOM_SPEED_MAX = 0.05f

    val CHRIS_CONSTANT = 4.2 * Math.pow(10.0, 2.0)  // a mysterious number, what could it mean?

    // GAMEPLAY CONSTANTS
    val DEFAULT_START_MONEY = 200
    val DEFAULT_MONEY_PRODUCTION = 150

    val DEFAULT_UNIT_CAP = 3
}