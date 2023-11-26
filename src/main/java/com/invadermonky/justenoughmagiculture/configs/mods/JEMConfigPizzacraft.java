package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigPizzacraft {
    private static final String MOD_NAME = ConstantNames.PIZZACRAFT;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableBroccoli = true;
        public boolean enableCorn = true;
        public boolean enableCucumber = true;
        public boolean enableOliveLeaves = true;
        public boolean enableOnion = true;
        public boolean enablePepper = true;
        public boolean enablePineapple = true;
        public boolean enableTomato = true;
    }
}
