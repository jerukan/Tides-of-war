package io.github.jerukan.game.ui.gamescreen.buttons

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import io.github.jerukan.game.GameState
import io.github.jerukan.game.gameunits.unitdata.BaseUnit
import io.github.jerukan.util.Assets
import io.github.jerukan.util.NamedFlag

class UnitBuildButton(val baseUnit: BaseUnit, buildFlag: NamedFlag) : TextButton(baseUnit.buttonDisplayString(), Assets.uiskin, "default") {

    private var hovered: Boolean = false

    val isHovered: Boolean
        get() = isOver

    init {
        addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                GameState.instance.unitManager.buildUnit(baseUnit)
                buildFlag.state = false
            }

            override fun enter(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
                hovered = true
            }

            override fun exit(event: InputEvent?, x: Float, y: Float, pointer: Int, fromActor: Actor?) {
                hovered = false
            }
        })
    }
}
