package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigBearWithMe {
    private static final String MOD_NAME = ConstantNames.BEAR_WITH_ME;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableBlackBear = true;
        public boolean enableGrizzlyBear = true;
        public boolean enablePandaBear = true;
    }
}
