package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigThermalFoundation {
    private static final String MOD_NAME = ConstantNames.THERMALFOUNDATION;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";
    public JEMConfigThermalFoundation() {}

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableBasalzMob = true;
        public boolean enableBlitzMob = true;
        public boolean enableBlizzMob = true;
    }
}
