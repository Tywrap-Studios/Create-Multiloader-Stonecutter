//? if fabric {
/*package com.example.template.platforms.fabric.data

import com.example.template.TempLateInit

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

// Don't forget to add this entrypoint to fabric.mod.json in case you want to use datagen!
object FabricDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        val helper = ExistingFileHelper.withResources(fabricDataGenerator.getModContainer().findPath("./").orElseThrow())
        TempLateInit.REGISTRATE.setupDatagen(pack, helper)
    }
}
*///?}
