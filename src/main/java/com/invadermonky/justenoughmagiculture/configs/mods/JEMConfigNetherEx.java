package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigNetherEx {
    private static final String MOD_NAME = ConstantNames.NETHEREX;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

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
            public boolean enableBrute = true;
            public boolean enableCoolmarSpider = true;
            public boolean enableEmber = true;
            public boolean enableGhastling = true;
            public boolean enableGhastQueen = true;
            public boolean enableFrost = true;
            public boolean enableGoldGolem = true;
            public boolean enableMogusBrown = true;
            public boolean enableMogusRed = true;
            public boolean enableMogusWhite = true;
            public boolean enableNethermite = true;
            public boolean enablePigtificateVillagers = true;
            public boolean enablePigtificateLeader = true;
            public boolean enableSalamanderBlack = true;
            public boolean enableSalamanderOrange = true;
            public boolean enableSpinout = true;
            public boolean enableSpore = true;
            public boolean enableSporeCreeper = true;
            public boolean enableWight = true;
        }

        public static class Villagers {
            public boolean enablePigtificateArmorsmith = true;
            public boolean enablePigtificateBrewer = true;
            public boolean enablePigtificateChief = true;
            public boolean enablePigtificateEnchanter = true;
            public boolean enablePigtificateGatherer = true;
            public boolean enablePigtificateHunter = true;
            public boolean enablePigtificateNincompoop = true;
            public boolean enablePigtificateScavenger = true;
            public boolean enablePigtificateToolsmith = true;
        }
    }
}
