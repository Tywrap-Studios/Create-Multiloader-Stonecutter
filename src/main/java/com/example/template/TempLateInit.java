package com.example.template;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TempLateInit {
    public static final String MOD_ID = /*$ mod_id*/"template";
    public static final Logger LOGGER = LoggerFactory.getLogger("Template");
    public static ModPlatform PLATFORM = null;
    public static CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static final ItemEntry<Item> ITEM = REGISTRATE
            .item("item", Item::new)
            .defaultModel()
            .register();

    public static void entrypoint(ModPlatform platform) {
        TempLateInit.PLATFORM = platform;
        LOGGER.info("Started mod in %s loader".formatted(TempLateInit.PLATFORM.getModLoader()));
        //? if fabric
        //REGISTRATE.register();
    }
}