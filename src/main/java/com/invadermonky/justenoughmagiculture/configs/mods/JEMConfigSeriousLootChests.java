package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigSeriousLootChests {
    private static final String MOD_NAME = ConstantNames.SERIOUS_LOOT_CHESTS;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Items settings.")
    public JEI JUST_ENOUGH_ITEMS = new JEI();
    public static class JEI {
        public boolean enableJEILootBags = true;
    }
}
