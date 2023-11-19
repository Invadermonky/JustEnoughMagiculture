package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigFutureMC {
    private static final String MOD_NAME = ConstantNames.FUTUREMC;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Plants JER_PLANTS = new Plants();

        public static class Mobs {
            public boolean enableCod = true;
            public boolean enablePufferfish = true;
            public boolean enableSalmon = true;
            public boolean enableTropicalFish = true;
        }

        public static class Plants {
            public boolean enableSweetBerry = true;
        }
    }
}
