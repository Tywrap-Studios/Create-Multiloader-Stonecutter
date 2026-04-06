plugins {
    id("dev.kikugie.stonecutter")
    id("dev.architectury.loom") version "1.13-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT" apply false
    id("me.modmuss50.mod-publish-plugin") version "0.8.4" apply false
}

stonecutter active "1.21.1-neoforge"

stonecutter parameters {
    swaps["mod_id"] = "\"${property("mod.id")}\""
    swaps["annotation_id"] = "\"${property("mod.id")}\")"

    val loader = node.metadata.project.substringAfter('-')
    constants.match(loader, "fabric", "neoforge", "forge")
}
