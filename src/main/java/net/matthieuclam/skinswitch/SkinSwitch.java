package net.matthieuclam.skinswitch;

import net.fabricmc.api.ModInitializer;
import net.matthieuclam.skinswitch.event.ClientTickHandler;

public class SkinSwitch implements ModInitializer {

    @Override
    public void onInitialize() {

        ClientTickHandler.register();

    }
}
