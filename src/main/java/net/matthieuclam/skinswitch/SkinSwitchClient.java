package net.matthieuclam.skinswitch;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.matthieuclam.skinswitch.config.ConfigManager;
import net.matthieuclam.skinswitch.event.KeyInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinSwitchClient implements ClientModInitializer {

    public static final String MOD_ID = "skinswitchmod";
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

        ConfigManager.initializeConfig();
        KeyInputHandler.register();

    }

}
