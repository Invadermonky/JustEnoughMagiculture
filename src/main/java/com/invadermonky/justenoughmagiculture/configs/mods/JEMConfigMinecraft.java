package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigMinecraft {
    private static final String MOD_NAME = "Minecraft";
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";
    public JEMConfigMinecraft() {}

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Plants JER_PLANTS = new Plants();

        public static class Mobs {
            public boolean enablePolarBear = true;
            @Comment("Enables Wither Boss JER Integration. This uses a fake loot table found in\n" +
                    "\"justenoughmagiculture:entities/wither_fake\". If you wish to display additional drops, you can\n" +
                    "edit this loot table like you would any other.")
            public boolean enableWither = true;
        }

        public static class Plants {
            public boolean enableBeetroot = true;
            public boolean enableNetherWart = true;
        }
    }
}
