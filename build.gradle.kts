import java.util.*

plugins {
    kotlin("jvm") version "2.3.20"
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("me.modmuss50.mod-publish-plugin")
}

val minecraft = stonecutter.current.version
val loader = loom.platform.get().name.lowercase()

version = "${mod.version}+$minecraft"
group = mod.group
base {
    archivesName.set("${mod.id}-$loader")
}

architectury.common(stonecutter.tree.branches.mapNotNull {
    if (stonecutter.current.project !in it) null
    else it.project.prop("loom.platform")
})

repositories {
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.terraformersmc.com/")
    maven("https://maven.nucleoid.xyz/")
    maven("https://maven.createmod.net") // Create, Ponder, Flywheel
    maven("https://mvn.devos.one/releases") // Porting Lib releases
    maven("https://mvn.devos.one/snapshots") // Create and several dependencies
    maven("https://maven.ithundxr.dev/mirror") // Registrate
    maven("https://maven.ithundxr.dev/snapshots") // Registrate
    maven("https://maven.jamieswhiteshirt.com/libs-release") // Reach Entity Attributes
    maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven") // Forge Config API Port
    maven("https://repo.nyon.dev/releases") // KLF
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft")
    mappings(loom.officialMojangMappings())

    if (loader == "fabric") {
        modImplementation("net.fabricmc:fabric-loader:${mod.dep("fabric_loader")}")

        //some features (like automatic resource loading from non vanilla namespaces) work only with fabric API installed
        //for example translations from assets/modid/lang/en_us.json won't be working, same stuff with textures
        //but we keep runtime only to not accidentally depend on fabric's api, because it doesn't exist in neo/forge
        modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${mod.dep("fabric_version")}")
        modImplementation("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")

        // Create
        modImplementation("com.simibubi.create:create-fabric:${mod.dep("create")}")
    }

    if (loader == "forge") {
        "forge"("net.minecraftforge:forge:${minecraft}-${mod.dep("forge_loader")}")

        "io.github.llamalad7:mixinextras-forge:${mod.dep("mixin_extras")}".let {
            implementation(it)
            include(it)
        }

        implementation("dev.nyon:KotlinLangForge:${mod.dep("klf")}+$loader")

        // Create
        modImplementation("com.simibubi.create:create-$minecraft:${mod.dep("create")}:slim") { isTransitive = false }
        modImplementation("net.createmod.ponder:Ponder-Forge-$minecraft:${mod.dep("ponder")}")
        modCompileOnly("dev.engine-room.flywheel:flywheel-forge-api-$minecraft:${mod.dep("flywheel")}")
        modRuntimeOnly("dev.engine-room.flywheel:flywheel-forge-$minecraft:${mod.dep("flywheel")}")
        modImplementation("com.tterrag.registrate:Registrate:${mod.dep("registrate")}")
        compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:0.4.1")!!)
        implementation("io.github.llamalad7:mixinextras-forge:0.4.1")
    }

    if (loader == "neoforge") {
        "neoForge"("net.neoforged:neoforge:${mod.dep("neoforge_loader")}")

        implementation("dev.nyon:KotlinLangForge:${mod.dep("klf")}+$loader")

        // Create
        implementation("com.simibubi.create:create-$minecraft:${mod.dep("create")}:slim") { isTransitive = false }
        implementation("net.createmod.ponder:ponder-neoforge:${mod.dep("ponder")}+mc$minecraft")
        compileOnly("dev.engine-room.flywheel:flywheel-neoforge-api-$minecraft:${mod.dep("flywheel")}")
        runtimeOnly("dev.engine-room.flywheel:flywheel-neoforge-$minecraft:${mod.dep("flywheel")}")
        implementation("com.tterrag.registrate:Registrate:${mod.dep("registrate")}")
    }
}

loom {
    accessWidenerPath = rootProject.file("src/main/resources/${mod.id}.accesswidener")

    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }
    if (loader == "forge") {
        forge.mixinConfigs(
            "${mod.id}-common.mixins.json",
            "${mod.id}-forge.mixins.json",
        )
    }
}


val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

publishMods {
    val modrinthToken = localProperties.getProperty("publish.modrinthToken", "")
    val curseforgeToken = localProperties.getProperty("publish.curseforgeToken", "")


    file = project.tasks.remapJar.get().archiveFile
    dryRun = modrinthToken == null || curseforgeToken == null

    displayName = "${mod.name} ${loader.replaceFirstChar { it.uppercase() }} ${property("mod.mc_title")}-${mod.version}"
    version = mod.version
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = BETA

    modLoaders.add(loader)

    val targets = property("mod.mc_targets").toString().split(' ')
    modrinth {
        projectId = property("publish.modrinth").toString()
        accessToken = modrinthToken
        targets.forEach(minecraftVersions::add)
        if (loader == "fabric") {
            requires("fabric-api")
            optional("modmenu")
        }
    }

    curseforge {
        projectId = property("publish.curseforge").toString()
        accessToken = curseforgeToken.toString()
        targets.forEach(minecraftVersions::add)
        if (loader == "fabric") {
            requires("fabric-api")
            optional("modmenu")
        }
    }
}

val requiredJava = if (stonecutter.eval(minecraft, ">=1.20.5")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava
}

kotlin {
    jvmToolchain(requiredJava.majorVersion.toInt())
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(requiredJava.majorVersion))
    }
}

tasks.remapJar {
    injectAccessWidener = true
    archiveClassifier = null
}

tasks.jar {
    archiveClassifier = "dev"
}

val buildAndCollect = tasks.register<Copy>("buildAndCollect") {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
    from(tasks.remapJar.get().archiveFile, tasks.remapSourcesJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}/$loader"))
    dependsOn("build")
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        group = "project"
        dependsOn(buildAndCollect)
    }

    rootProject.tasks.register("runActive") {
        group = "project"
        dependsOn(tasks.named("runClient"))
    }
}

tasks.processResources {
    properties(
        listOf("fabric.mod.json"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_fabric"),
        "create" to mod.dep("create")
    )
    properties(
        listOf("META-INF/mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_forgelike")
    )
    properties(
        listOf("META-INF/neoforge.mods.toml", "pack.mcmeta"),
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.prop("mc_dep_forgelike")
    )
}

tasks.build {
    group = "versioned"
    description = "Must run through 'chiseledBuild'"
}
