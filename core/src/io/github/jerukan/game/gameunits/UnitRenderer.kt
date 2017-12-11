package io.github.jerukan.game.gameunits

import com.badlogic.gdx.graphics.g2d.Batch
import io.github.jerukan.game.Renderer

class UnitRenderer(private val unitManager: UnitManager) : Renderer {

    override fun init() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun render(batch: Batch) {
        batch.begin()
        for (unit in unitManager.unitlist) {
            unit.render(batch)
        }
        batch.end()
    }
}
