package io.github.jerukan.game

import com.badlogic.gdx.graphics.g2d.Batch

/** Any class that usually has a manager it get info from and renders based on that  */
interface Renderer {

    fun init()

    fun resize(width: Int, height: Int)

    fun render(batch: Batch)
}
