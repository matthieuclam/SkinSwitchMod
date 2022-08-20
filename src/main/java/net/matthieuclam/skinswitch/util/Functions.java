package net.matthieuclam.skinswitch.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerModelPart;

public class Functions {

    public static MinecraftClient client = MinecraftClient.getInstance();

    public static void toggleSkin(PlayerModelPart[] modelParts) {
        for(PlayerModelPart modelPart : modelParts) {
            if (modelPart != PlayerModelPart.CAPE) {
                client.options.togglePlayerModelPart(modelPart, !client.options.isPlayerModelPartEnabled(modelPart));
            }
        }
    }

    public static void hideSkin(PlayerModelPart[] modelParts) {
        for(PlayerModelPart modelPart : modelParts) {
            if (modelPart != PlayerModelPart.CAPE) {
                client.options.togglePlayerModelPart(modelPart, false);
            }
        }
    }

}
