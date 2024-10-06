package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigOceanicExpanse {
    private static final String MOD_NAME = ConstantNames.OCEANIC_EXPANSE;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @Comment("Enables " + MOD_NAME + " injected loot JER Integration. (Black Ash and Witch Hats)")
    public boolean enableJERInjectedLoot = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER villager integration.")
    public boolean enableJERVillagers = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public boolean enableCod = true;
        public boolean enableCrab = true;
        public boolean enableDrowned = true;
        public boolean enableGlowSquid = true;
        public boolean enableLobster = true;
        public boolean enablePickled = true;
        public boolean enablePufferfish = true;
        public boolean enableSalmon = true;
        public boolean enableTropicalFish = true;
        public boolean enableTropicalSlime = true;
        public boolean enableTurtle = true;
    }
}
