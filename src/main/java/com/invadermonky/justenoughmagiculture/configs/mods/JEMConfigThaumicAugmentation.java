package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigThaumicAugmentation {
    private static final String MOD_NAME = ConstantNames.THAUMICAUGMENTATION;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Items settings.")
    public JEMConfigThaumcraft.JEI JUST_ENOUGH_ITEMS = new JEMConfigThaumcraft.JEI();
    public static class JEI {
        public boolean enableJEILootBags = true;
    }

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableAutocaster = true;
        public boolean enableEldritchAutocaster = true;
        public boolean enableEldritchGolem = true;
        public boolean enableEldritchGuardian = true;
        public boolean enableEldritchWarden = true;
        public boolean enablePrimalWisp = true;
    }
}
