package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigRustic {
    private static final String MOD_NAME = ConstantNames.RUSTIC;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableAloeVera = true;
        public boolean enableAppleLeaves = true;
        public boolean enableAppleSeeds = true;
        public boolean enableBloodOrchid = true;
        public boolean enableChamomile = true;
        public boolean enableChiliPeppers = true;
        public boolean enableCloudsbuff = true;
        public boolean enableCohosh = true;
        public boolean enableCoreRoot = true;
        public boolean enableDeathstalk = true;
        public boolean enableGinseng = true;
        public boolean enableGrapes = true;
        public boolean enableHorsetail = true;
        public boolean enableMarshMallow = true;
        public boolean enableMooncap = true;
        public boolean enableTomatoes = true;
        public boolean enableWindThistle = true;
    }
}
