package net.matthieuclam.skinswitch;

import net.fabricmc.api.ClientModInitializer;
import net.matthieuclam.skinswitch.event.KeyInputHandler;

public class SkinSwitchClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();

    }

}
