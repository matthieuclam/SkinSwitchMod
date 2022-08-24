package net.matthieuclam.skinswitch.config;

import com.google.gson.annotations.SerializedName;
import com.terraformersmc.modmenu.config.option.BooleanConfigOption;
import com.terraformersmc.modmenu.config.option.OptionConvertable;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.minecraft.client.option.SimpleOption;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Config {

    public static final BooleanConfigOption CAPE = new BooleanConfigOption("cape", false);
    public static final BooleanConfigOption ANIMATED = new BooleanConfigOption("animated", false);
    public static final BooleanConfigOption PARTICLES_ON_STATIC = new BooleanConfigOption("particlesOnStatic", false);
    public static final BooleanConfigOption PARTICLES_ON_ANIMATED = new BooleanConfigOption("particlesOnAnimated", false);

    public static SimpleOption<?>[] asOptions() {
        ArrayList<SimpleOption<?>> options = new ArrayList<>();
        for (Field field : Config.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && OptionConvertable.class.isAssignableFrom(field.getType()) && !field.getName().equals("HIDE_CONFIG_BUTTONS") && !field.getName().equals("MODIFY_TITLE_SCREEN") && !field.getName().equals("MODIFY_GAME_MENU")) {
                try {
                    options.add(((OptionConvertable) field.get(null)).asOption());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return options.stream().toArray(SimpleOption[]::new);
    }

    public enum Sorting {
        @SerializedName("ascending")
        ASCENDING(Comparator.comparing(mod -> mod.getName().toLowerCase(Locale.ROOT))),
        @SerializedName("descending")
        DESCENDING(ASCENDING.getComparator().reversed());

        Comparator<Mod> comparator;

        Sorting(Comparator<Mod> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Mod> getComparator() {
            return comparator;
        }
    }

    public enum ModCountLocation {
        @SerializedName("title_screen")
        TITLE_SCREEN(true, false),
        @SerializedName("mods_button")
        MODS_BUTTON(false, true),
        @SerializedName("title_screen_and_mods_button")
        TITLE_SCREEN_AND_MODS_BUTTON(true, true),
        @SerializedName("none")
        NONE(false, false);

        private final boolean titleScreen, modsButton;

        ModCountLocation(boolean titleScreen, boolean modsButton) {
            this.titleScreen = titleScreen;
            this.modsButton = modsButton;
        }

        public boolean isOnTitleScreen() {
            return titleScreen;
        }

        public boolean isOnModsButton() {
            return modsButton;
        }
    }

    public enum ModsButtonStyle {
        @SerializedName("classic")
        CLASSIC(false),
        @SerializedName("replace_realms")
        REPLACE_REALMS(true),
        @SerializedName("shrink")
        SHRINK(false),
        @SerializedName("icon")
        ICON(false);

        private final boolean titleScreenOnly;

        ModsButtonStyle(boolean titleScreenOnly) {
            this.titleScreenOnly = titleScreenOnly;
        }

        public ModsButtonStyle forGameMenu() {
            if (titleScreenOnly) {
                return CLASSIC;
            }
            return this;
        }
    }

}
