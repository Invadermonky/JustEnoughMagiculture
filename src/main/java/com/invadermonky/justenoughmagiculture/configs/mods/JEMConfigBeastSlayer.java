package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigBeastSlayer {
    private static final String MOD_NAME = ConstantNames.BEAST_SLAYER;
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
        public boolean enableBonepile = true;
        public boolean enableBoulderingZombie = true;
        public boolean enableDamcell = true;
        public boolean enableFrostwalker = true;
        public boolean enableGhost = true;
        public boolean enableGiant = true;
        public boolean enableNekros = true;
        public boolean enableNetherhound = true;
        public boolean enableOwlstack = true;
        public boolean enableRiftedEnderman = true;
        public boolean enableSandMonster = true;
        public boolean enableVessel = true;
        public boolean enableZealot = true;
    }
}
