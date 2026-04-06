//? if forge {
/*package com.example.template.platforms.forge;

import com.example.template.ModPlatform;
import com.example.template.TempLateInit;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(/^$ annotation_id^/"template")
public class TempLateForge {
    public TempLateForge() {
        TempLateInit.REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
        TempLateInit.entrypoint(new ForgePlatform());
    }

    public static class ForgePlatform implements ModPlatform {
        @Override
        public String getModLoader() {
            return "LexForge";
        }

        @Override
        public boolean isModLoaded(String id) {
            return ModList.get().isLoaded(id);
        }
    }
}
*///?}