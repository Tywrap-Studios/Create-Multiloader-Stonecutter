//? if fabric {
/*package com.example.template.platforms.fabric

import com.example.template.ModPlatform
import com.example.template.TemplateInit
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader

class TemplateFabric : ModInitializer {
    override fun onInitialize() {
        TemplateInit.entrypoint(FabricPlatform())
    }

    class FabricPlatform : ModPlatform {
        override fun getModLoader(): String {
            return "Fabric"
        }

        override fun isModLoaded(id: String): Boolean {
            return FabricLoader.getInstance().isModLoaded(id)
        }
    }
}
*///?}