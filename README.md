# SkinSwitchMod
This is a Minecraft **1.19.1** client mod using fabric.
The repository has two separated branches.

## Overview

### _The main branch_

This branch is the "public" version of the mod. It has only one basic feature.
The feature adds a key binding that allow the user to hide/show its top skin layer. The player's cape is not affected by default but an option is available with the ModMenu from TerraformersMC

![keyBindingImage](readme/images/keyBinding.png "Key binding")

![topLayerImage](readme/images/topLayer.png "Top layer")

### _The privateUseModVersion branch_

This branch has the same feature as the main one, but it adds a feature more specific to my own skin.
It is now possible for other players to hide my skin by holding a shear in their main hand and sneaking. In addition, they can show my skin by giving me pink wool. The reason I made this is that my skin is made of wool.

## Other

### _Download_

Archive containing builds for both branches : [Download](https://www.mediafire.com/file/a7z1imp919m3y1k/SkinSwitchMod.zip/file).  
You can also download [releases that are available on Github](https://github.com/matthieuclam/SkinSwitchMod/releases).

### _Installation_

**The mod has two dependencies.** You have to download [Mod Menu](https://github.com/TerraformersMC/ModMenu) and [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) if you want to use this mod.  
Download the SkinSwitchMod after choosing your build (main or privateUseModVersion) and paste it in your mod folder. Add the two dependencies.  
Then start your Minecraft client with Fabric 1.19.1. 
The mod seems to support Fabric 1.19.2 (if you update dependencies).  

Config options with ModMenu (privateUseModVersion branch) :
![optionScreenImage](readme/images/optionScreen.png "Option screen")

### _Sources_

Inspired by [Flashy Clothing](https://www.curseforge.com/minecraft/mc-mods/flashy-clothing).  
Code read for inspiration : [Boosted Brightness](https://github.com/adamviola/BoostedBrightness) and [Logical Zoom](https://github.com/LogicalGeekBoy/logical_zoom).  
Some code taken from [Mod Menu](https://github.com/TerraformersMC/ModMenu) (especially for config system and config gui).

### _Repository forking_

Feel free to edit any branch of the mod to add features or improve the code. I am not familiar with modding, so I probably wrote low quality code. In addition, I have not been coding in java for many months, so I did not always follow object oriented best practices even if I tried.