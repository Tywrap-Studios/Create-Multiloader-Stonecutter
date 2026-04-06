package com.example.template

import com.simibubi.create.foundation.data.CreateRegistrate
import com.tterrag.registrate.util.entry.ItemEntry
import net.minecraft.world.item.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TemplateInit {
    const val MOD_ID: String = /*$ mod_id*/"template"
    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger("Template")
    lateinit var PLATFORM: ModPlatform
    @JvmField
    var REGISTRATE: CreateRegistrate = CreateRegistrate.create(MOD_ID)

    val ITEM: ItemEntry<Item?> = REGISTRATE
        .item<Item?>("item", ::Item)
        .defaultModel()
        .register()

    @JvmStatic
    fun entrypoint(platform: ModPlatform) {
        PLATFORM = platform
        LOGGER.info("Started mod in ${PLATFORM.getModLoader()} loader")
        //? if fabric
        REGISTRATE.register();
    }
}