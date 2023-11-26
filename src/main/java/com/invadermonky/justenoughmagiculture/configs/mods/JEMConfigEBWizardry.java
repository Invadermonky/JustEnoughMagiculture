package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigEBWizardry {
    private static final String MOD_NAME = ConstantNames.EBWIZARDRY;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableEvilWizard = true;
        public boolean enableRemnantEarth = true;
        public boolean enableRemnantFire = true;
        public boolean enableRemnantHealing = true;
        public boolean enableRemnantIce = true;
        public boolean enableRemnantLightning = true;
        public boolean enableRemnantNecromancy = true;
        public boolean enableRemnantSorcery = true;
    }
}
