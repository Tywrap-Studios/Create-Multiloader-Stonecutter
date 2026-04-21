//? if forge {
/*package com.example.template.platforms.forge;

import com.example.template.ModPlatform;
import com.example.template.TempLateInit;
import com.example.template.platforms.forge.data.ForgeDatagen;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.jetbrains.annotations.NotNull;

// This class is written in Java because Forge does not seem to like
// casting the Kotlin counterpart of FMLJavaModLoadingContext provided
// by FLK. Nonetheless, this seems to fix it for now.

@Mod(/^$ annotation_id^/"template")
public class TempLateForge {
    public TempLateForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TempLateInit.REGISTRATE.registerEventListeners(bus);
        // Uncomment for datagen
//        bus.addListener(ForgeDatagen::onGatherData);
        TempLateInit.entrypoint(new ForgePlatform());
    }

    public static class ForgePlatform implements ModPlatform {
        @Override
        public @NotNull String getModLoader() {
            return "LexForge";
        }

        @Override
        public boolean isModLoaded(@NotNull String id) {
            return ModList.get().isLoaded(id);
        }
    }
}
*///?}