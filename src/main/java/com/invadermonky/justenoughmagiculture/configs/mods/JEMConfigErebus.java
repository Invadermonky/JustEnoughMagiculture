package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigErebus {
    private static final String MOD_NAME = ConstantNames.EREBUS;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("Enables render fixes. Disabling this may cause some entities to not display in JER.")
    public boolean enableRenderFixes = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Items settings.")
    public JEI JUST_ENOUGH_ITEMS = new JEI();
    public static class JEI {
        public boolean enableCompostCategory = true;
        public boolean enableOfferingAltarCategory = true;
        public boolean enableSmoothieCategory = true;
        @Comment("Force loades " + MOD_NAME + " JEI categories even if MoreTweaker is loaded.")
        public boolean forceLoadCategories = false;
    }

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Plants JER_PLANTS = new Plants();

        public static class Mobs {
            public boolean enableAntlion = true;
            public boolean enableAntlionGuardian = true;
            public boolean enableAntlionOverlord = true;
            public boolean enableBedBug = true;
            public boolean enableBeetle = true;
            public boolean enableBeetleLarva = true;
            public boolean enableBlackWidow = true;
            public boolean enableBogMaw = true;
            public boolean enableBombardierBeetle = true;
            public boolean enableBombardierBeetleLarva = true;
            public boolean enableBotFly = true;
            public boolean enableCentipede = true;
            public boolean enableChameleonTick = true;
            public boolean enableCicada = true;
            public boolean enableCropWeevil = true;
            public boolean enableCrushroom = true;
            public boolean enableDragonfly = true;
            public boolean enableFireAnt = true;
            public boolean enableFireAntSoldier = true;
            public boolean enableFly = true;
            public boolean enableFungalWeevil = true;
            public boolean enableGlowWorm = true;
            public boolean enableGrasshopper = true;
            public boolean enableHoneyPotAnt = true;
            public boolean enableJumpingSpider = true;
            public boolean enableLavaWebSpider = true;
            public boolean enableLocust = true;
            public boolean enableMagmaCrawler = true;
            public boolean enableMidgeSwarm = true;
            public boolean enableMoneySpider = true;
            public boolean enableMosquito = true;
            public boolean enableMoth = true;
            public boolean enablePondSkater = true;
            public boolean enablePrayingMantis = true;
            public boolean enablePunchroom = true;
            public boolean enableRhinoBeetle = true;
            public boolean enableScorpion = true;
            public boolean enableScytodes = true;
            public boolean enableSolifuge = true;
            public boolean enableStagBeetle = true;
            public boolean enableTarantula = true;
            public boolean enableTarantulaBroodMother = true;
            public boolean enableTitanBeetle = true;
            public boolean enableUmberGolemMud = true;
            public boolean enableUmberGolemIron = true;
            public boolean enableUmberGolemGold = true;
            public boolean enableUmberGolemJade = true;
            public boolean enableVelvetWorm = true;
            public boolean enableWasp = true;
            public boolean enableWaspBoss = true;
            public boolean enableWoodlouse = true;
            public boolean enableWorkerBee = true;
            public boolean enableZombieAnt = true;
            public boolean enableZombieAntSoldier = true;
        }

        public static class Plants {
            public boolean enableCabbage = true;
            public boolean enableDarkFruit = true;
            public boolean enableHeartBerry = true;
            public boolean enableJadeBerry = true;
            public boolean enableMandrake = true;
            public boolean enableSwampBerry = true;
            public boolean enableTurnip = true;
        }
    }
}
