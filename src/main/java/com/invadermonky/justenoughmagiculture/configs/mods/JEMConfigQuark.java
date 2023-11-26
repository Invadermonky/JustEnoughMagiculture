package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigQuark {
    private static final String MOD_NAME = ConstantNames.QUARK;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";
    public JEMConfigQuark() {}

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @Comment("Enables Quark injected loot JER Integration. (Black Ash and Witch Hats)")
    public boolean enableJERInjectedLoot = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER villager integration.")
    public boolean enableJERVillagers = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public static final JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Villagers JER_VILLAGERS = new Villagers();

        public static class Mobs {
            public boolean enableArchaeologist = true;
            public boolean enableCrab = true;
            public boolean enableAshen = true;
            public boolean enableDweller = true;
            public boolean enableFoxhound = true;
            public boolean enableFrog = true;
            public boolean enablePirate = true;
            public boolean enableStoneling = true;
            public boolean enableWraith = true;
        }

        public static class Villagers {
            public boolean enableArchaeologist = true;
        }
    }
}
