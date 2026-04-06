//? if fabric {
package com.example.template.platforms.fabric;

import com.example.template.ModPlatform;
import com.example.template.TempLateInit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TempLateFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TempLateInit.entrypoint(new FabricPlatform());
    }

    public static class FabricPlatform implements ModPlatform {
        @Override
        public String getModLoader() {
            return "Fabric";
        }

        @Override
        public boolean isModLoaded(String id) {
            return FabricLoader.getInstance().isModLoaded(id);
        }
    }
}
//?}