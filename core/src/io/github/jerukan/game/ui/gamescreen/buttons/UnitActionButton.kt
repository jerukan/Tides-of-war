package io.github.jerukan.game.ui.gamescreen.buttons

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import io.github.jerukan.game.GameState
import io.github.jerukan.game.board.BoardManager
import io.github.jerukan.game.gameunits.Unit
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction
import io.github.jerukan.util.Assets

class UnitActionButton(self: Unit, action: UnitAction) : TextButton(action.name, Assets.uiskin, "default") {

    init {
        super.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (self.hasSufficientSpeed(action.speedConsumption)) {
                    if (action.requiresTarget) {
                        self.currentAction = action
                        action.generateTargets(self)
                        self.setAvailableTargets(action.availableTargets)
                        self.setTargetSpeedConsumptions(action.targetSpeedConsumptions)
                        GameState.instance.boardManager.selectType = BoardManager.SelectType.ACTION
                    } else {
                        action.execute(self)
                    }
                }
                GameState.instance.update()
            }
        })
    }
}