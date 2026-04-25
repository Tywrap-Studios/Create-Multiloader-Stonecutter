# Create mod addon Kotlin Multiloader + Stonecutter template

This template is a fork of JavaJumper's base Multiloader + Stonecutter template.
Please report issues to us first, then we can determine whether it is an issue
with our modifications or the base template.

This template allows you to create a Multiloader Multiversion Create addon
by utilising Stonecutter logic and Architectury Loom.

## Setup

There are three default versions defined:

- 1.20.1, Fabric (depends on Create 6.0.8.1)
- 1.20.1, LexForge (depends on Create 6.0.8-289)
- 1.21.1, NeoForge (depends on Create 6.0.9-215)

If you want to change versions or add them, you can do so in
`settings.gradle.kts` using Stonecutter.

> [!NOTE]
> The build script being Kotlin DSL does not mean you need to use Kotlin to code your addon!

## C# Script

The base template came with a C# script to automatically change
template names, however, it may not fully work anymore due to
slight modifications made. Always check the output of autonomous
actions.

Open RenameTemplate.cs, change the names in the replacements array and run "dotnet run"
in this directory.
It is highly recommended to do this before opening the project in your IDE
to then remove all C# related files from the project (obj and bin folders, .csproj and the script itself).
Afterwards, you can also remove the related C# stuff from the .gitignore (there's a comment to indicate
what is safe to remove).

## Build tools usage

To start current active version use the `runActive` task

For testing all versions you can use `chiseledRunAllClients`, it runs all possible version and loader variants.

The template also has the publishing plugin. You can configure it as needed.
After configuration, you can use the `chiseledPublishMods` task.

## Template usage

The template comes with some predefined things:

- Common and platform specific entrypoints;
- `ModPlatform` interface for platform-specific code;
- Example mixin (clientside);
- Common entrypoint with Logger, Mod ID, and a `ModPlatform` instance;
- Prerequisites to set up data generation;
- A logo pack in order to aid you in creating a Create-styled logo.

## More information

For more information, see the respective documentations of
[Stonecutter](https://stonecutter.kikugie.dev),
[Architectury Loom](https://docs.architectury.dev/loom/introduction)
and [Create](https://wiki.createmod.net/developers/)
