package net.matthieuclam.skinswitch.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_SWITCH = "key.category.skinswitch.switch";
    public static final String KEY_SWITCH_SKIN = "key.category.skinswitch.switch_skin";
    public static final PlayerModelPart[] modelParts = PlayerModelPart.values();

    public static KeyBinding switchingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(switchingKey.wasPressed()) {
                for(PlayerModelPart modelPart : modelParts) {
                    if (modelPart != PlayerModelPart.CAPE) {
                        client.options.togglePlayerModelPart(modelPart, !client.options.isPlayerModelPartEnabled(modelPart));
                    }
                }
            }
        });
    }

    public static void register() {
        switchingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SWITCH_SKIN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_SWITCH
        ));

        registerKeyInputs();
    }
}
