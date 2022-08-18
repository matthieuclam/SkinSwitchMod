package net.matthieuclam.skinswitch;

import net.fabricmc.api.ClientModInitializer;
import net.matthieuclam.skinswitch.event.KeyInputHandler;

public class SkinSwitchClient implements ClientModInitializer {

    public static final String MOD_ID = "skinblinker";

    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();

    }

}
