package io.github.jerukan.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Skin

object Assets {

    var assetManager = AssetManager()

    val grass1 = AssetDescriptor("tiles/grassland_tile1.png", Texture::class.java)
    val desert1 = AssetDescriptor("tiles/desert_tile1.png", Texture::class.java)
    val hill1 = AssetDescriptor("tiles/hill_tile1.png", Texture::class.java)
    val highland1 = AssetDescriptor("tiles/highland_tile1.png", Texture::class.java)
    val mountain1 = AssetDescriptor("tiles/mountain_tile1.png", Texture::class.java)

    val spearman = AssetDescriptor("units/spearman.png", Texture::class.java)
    val footman = AssetDescriptor("units/footman.png", Texture::class.java)
    val archer = AssetDescriptor("units/archer.png", Texture::class.java)
    val wall = AssetDescriptor("units/wall.png", Texture::class.java)
    val village = AssetDescriptor("units/village.png", Texture::class.java)
    val armory = AssetDescriptor("units/armory.png", Texture::class.java)
    val goldmine = AssetDescriptor("units/goldmine.png", Texture::class.java)
    val farm = AssetDescriptor("units/farm.png", Texture::class.java)
    val blimp = AssetDescriptor("units/blimp.png", Texture::class.java)
    val blimpWorkshop = AssetDescriptor("units/blimpworkshop.png", Texture::class.java)

    val uiskin = Skin(Gdx.files.internal("ui/uiskin.json"))

    fun load() {
        assetManager.load(grass1)
        assetManager.load(desert1)
        assetManager.load(hill1)
        assetManager.load(highland1)
        assetManager.load(mountain1)

        assetManager.load(spearman)
        assetManager.load(footman)
        assetManager.load(archer)
        assetManager.load(wall)
        assetManager.load(village)
        assetManager.load(armory)
        assetManager.load(goldmine)
        assetManager.load(farm)
        assetManager.load(blimp)
        assetManager.load(blimpWorkshop)
    }

    fun getTexture(asset: AssetDescriptor<Texture>): Texture {
        return assetManager.get(asset)
    }

    fun dispose() {
        assetManager.dispose()
    }
}
