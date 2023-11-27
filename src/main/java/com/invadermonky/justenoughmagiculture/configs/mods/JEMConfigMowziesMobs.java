package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigMowziesMobs {
    private static final String MOD_NAME = ConstantNames.MOWZIESMOBS;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER villager integration.")
    public boolean enableJERVillagers = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Villagers JER_VILLAGERS = new Villagers();

        public static class Mobs {
            public boolean enableBarako = true;
            public boolean enableBarakoBliss = true;
            public boolean enableBarakoFear = true;
            public boolean enableBarakoFury = true;
            public boolean enableBarakoMisery = true;
            public boolean enableBarakoRage = true;
            public boolean enableFerrousWroughtnaut = true;
            public boolean enableFoliaath = true;
            public boolean enableFrostmaw = true;
            public boolean enableGrottol = true;
            public boolean enableLantern = true;
            public boolean enableNaga = true;
        }

        public static class Villagers {
            public boolean enableBarako = true;
            public boolean enableBarakoana = true;
        }
    }
}
