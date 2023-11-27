package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigRats {
    private static final String MOD_NAME = ConstantNames.RATS;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Fixes a null world bug when rendering rats. Disabling this will not break anything but there may be log error spam.")
    public boolean enableRenderFixes = true;

    @RequiresMcRestart
    @Comment("Fixes " + MOD_NAME + " Villager JER errors. Disabling this can cause modded JER villagers to display incorrect information.")
    public boolean fixJERVillagers = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableBlackDeath = true;
        public boolean enableFeralRatlantean = true;
        public boolean enableIllagerPiper = true;
        public boolean enableRatlanteanAutomation = true;
        public boolean enableNeoRatlantean = true;
        public boolean enablePirat = true;
        public boolean enablePlagueBeast = true;
        public boolean enablePlagueDoctor = true;
        public boolean enableRat = true;
        public boolean enableRatlanteanSpirit = true;
    }
}
