package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigIceAndFire {
    private static final String MOD_NAME = ConstantNames.ICE_AND_FIRE;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @Comment("Enables Quark injected loot JER Integration. (Black Ash and Witch Hats)")
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
    @Comment("Enables render fixes. Disabling this may cause some entities to not display in JER.")
    public boolean enableRenderFixes = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Villagers JER_VILLAGERS = new Villagers();

        public static class Mobs {
            public boolean enableAmphithere = true;
            public boolean enableCockatrice = true;
            public boolean enableCyclops = true;
            public boolean enableDeathWormRed = true;
            public boolean enableDeathWormRedGiant = true;
            public boolean enableDeathWormTan = true;
            public boolean enableDeathWormTanGiant = true;
            public boolean enableDeathWormWhite = true;
            public boolean enableDeathWormWhiteGiant = true;
            public boolean enableDreadBeast = true;
            public boolean enableDreadGhoul = true;
            public boolean enableDreadKnight = true;
            public boolean enableDreadLich = true;
            public boolean enableDreadScuttler = true;
            public boolean enableDreadThrall = true;
            public boolean enableFireDragonFemale = true;
            public boolean enableFireDragonMale = true;
            public boolean enableGorgon = true;
            public boolean enableHippocampus = true;
            public boolean enableHippogryph = true;
            public boolean enableHydra = true;
            public boolean enableIceDragonFemale = true;
            public boolean enableIceDragonMale = true;
            public boolean enableMyrmexDesertQueen = true;
            public boolean enableMyrmexDesertRoyal = true;
            public boolean enableMyrmexDesertSentinel = true;
            public boolean enableMyrmexDesertSoldier = true;
            public boolean enableMyrmexDesertWorker = true;
            public boolean enableMyrmexJungleQueen = true;
            public boolean enableMyrmexJungleRoyal = true;
            public boolean enableMyrmexJungleSentinel = true;
            public boolean enableMyrmexJungleSoldier = true;
            public boolean enableMyrmexJungleWorker = true;
            public boolean enablePixie = true;
            public boolean enableSeaSerpent = true;
            public boolean enableSiren = true;
            public boolean enableStymphalianBird = true;
            public boolean enableTrollForest = true;
            public boolean enableTrollFrost = true;
            public boolean enableTrollMountain = true;
        }

        public static class Villagers {
            public boolean enableMyrmexQueen = true;
            public boolean enableMyrmexRoyal = true;
            public boolean enableMyrmexSentinel = true;
            public boolean enableMyrmexSoldier = true;
            public boolean enableMyrmexWorker = true;
            public boolean enableSnowVillager = true;
        }
    }
}
