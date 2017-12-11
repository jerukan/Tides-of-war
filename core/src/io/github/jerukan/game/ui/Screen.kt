package io.github.jerukan.game.ui

import com.badlogic.gdx.scenes.scene2d.Stage

/** Bare minimum for all the UI screens  */
abstract class Screen
/** Classes that extend Screen have their buttons and tables initialized in the constructor
 * @param stage retrieved from UIRenderer
 */
(private val stage: Stage) {
    private lateinit var menus: Array<Menu?>

    fun addMenus(vararg menu: Menu) {
        menus = arrayOfNulls(menu.size)
        for (i in menu.indices) {
            menus[i] = menu[i]
        }
        for (m in menus) {
            stage.addActor(m!!.table)
        }
    }

    abstract fun init()

    /** Called to determine which tables are visible under conditions  */
    fun updateVisibility() {
        for (menu in menus) {
            menu!!.updateVisibility()
        }
    }

    /** Called to clear windows that aren't visible by default
     * For example, pop up windows and such  */
    abstract fun clearWindows()

    fun render() {
        stage.draw()
    }
}