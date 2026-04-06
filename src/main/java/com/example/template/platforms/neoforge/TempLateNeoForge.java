//? if neoforge {
package com.example.template.platforms.neoforge;

import com.example.template.TempLateInit;
import com.example.template.ModPlatform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

@Mod("template")
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
        public boolean isModLoaded(String modId) {
            return ModList.get().isLoaded(modId);
        }
    }
}
//?}