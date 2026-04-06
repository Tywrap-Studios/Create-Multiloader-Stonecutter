//? if neoforge {
/*package com.example.template.platforms.neoforge

import com.example.template.ModPlatform
import com.example.template.TemplateInit
import dev.nyon.klf.KotlinModContainer
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod

@Mod(/*$ annotation_id*/"template")
class TemplateNeoForge(bus: IEventBus, container: ModContainer, kotlinModContainer: KotlinModContainer, dist: Dist) {
    init {
        TemplateInit.REGISTRATE.registerEventListeners(bus)
        TemplateInit.entrypoint(NeoForgePlatform())
    }

    class NeoForgePlatform : ModPlatform {
        override fun getModLoader(): String {
            return "NeoForge"
        }

        override fun isModLoaded(id: String): Boolean {
            return ModList.get().isLoaded(id)
        }
    }
} *///?}