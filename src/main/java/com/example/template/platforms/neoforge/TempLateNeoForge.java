//? if neoforge {
package com.example.template.platforms.neoforge;

import com.example.template.ModPlatform;
import com.example.template.TempLateInit;

import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

@Mod(/*$ annotation_id*/"template")
public class TempLateNeoForge {
    public TempLateNeoForge() {
        TempLateInit.entrypoint(new NeoForgePlatform());
    }

    public static class NeoForgePlatform implements ModPlatform {
        @Override
        public String getModLoader() {
            return "NeoForge";
        }

        @Override
        public boolean isModLoaded(String id) {
            return ModList.get().isLoaded(id);
        }
    }
}
//?}