package io.github.jerukan.game

import com.badlogic.gdx.graphics.Color
import io.github.jerukan.game.board.BoardManager
import io.github.jerukan.game.gameunits.UnitManager

/** Manages all the nitty gritty numbers and stuff of the game
 * Note to self: INDEPENDENT of the renderers  */

class GameState : Manager {

    var boardManager: BoardManager
    var unitManager: UnitManager

    var player1: Player
    var player2: Player

    var players: Array<Player>
    var currentPlayerNum: Int = 0

    val currentPlayer: Player
        get() = players[currentPlayerNum]

    init {
        boardManager = BoardManager()
        unitManager = UnitManager()

        player1 = Player("boi", Color(0.2f, 0.5f, 0.3f, 1f))
        player2 = Player("dood", Color(0.5f, 0.2f, 0.9f, 1f))
        players = arrayOf(player1, player2)
        currentPlayerNum = 0
    }

    override fun init() {
        verifyPlayers()
        boardManager.init()
        unitManager.init()
    }

    fun reset() {
        boardManager.resetBoard()
        unitManager.clearUnits()
        //to lazy to make it so that nothing happens on both player's first turns
        currentPlayer.onNewTurn()
    }

    fun passTurn() {
        unitManager.onEndTurn()
        currentPlayerNum++
        if (currentPlayerNum >= players.size) {
            currentPlayerNum = 0
        }
        boardManager.selectedPosition.reset()
        unitManager.onNewTurn()
        currentPlayer.onNewTurn()
        boardManager.updateAvailableBuildPositions()
        update()
    }

    fun verifyPlayers() {
        for (player in players) {
            for (other in players) {
                if (player !== other) {
                    if (player.name == other.name) {
                        throw IllegalArgumentException("Found two or more players with the name \"" + player.name + "\"")
                    } else if (player.color == other.color) {
                        throw IllegalArgumentException("Found two or more players with the same colors " + player.color.toString())
                    }
                }
            }
        }
    }

    override fun update() {
        boardManager.update()
        unitManager.update()
    }

    fun dispose() {
        boardManager.dispose()
    }

    companion object {

        lateinit var instance: GameState
    }
}