package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigMutantBeasts {
    private static final String MOD_NAME = ConstantNames.TEMPLATE;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableCreeperMinion = true;
        public boolean enableEndersoulClone = true;
        public boolean enableMutantCreeper = true;
        public boolean enableMutantEnderman = true;
        public boolean enableMutantSkeleton = true;
        public boolean enableMutantSnowGolem = true;
        public boolean enableMutantZombie = true;
        public boolean enableSpiderPig = true;
    }
}
