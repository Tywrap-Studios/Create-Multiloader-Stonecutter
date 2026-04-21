//? if neoforge {
package com.example.template.platforms.neoforge

import com.example.template.ModPlatform
import com.example.template.TemplateInit
import com.example.template.platforms.neoforge.data.NeoForgeDatagen
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
        // Uncomment for datagen
//        bus.addListener(NeoForgeDatagen::onGatherData)
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
} //?}