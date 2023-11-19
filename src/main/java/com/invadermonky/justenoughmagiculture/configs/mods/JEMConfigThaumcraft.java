package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigThaumcraft {
    private static final String MOD_NAME = ConstantNames.THAUMCRAFT;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Force loads " + MOD_NAME + " mobs and bosses that normally do not spawn without Thaumic Augmentation.")
    public boolean forceLoadJERMobs = false;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Items settings.")
    public JEI JUST_ENOUGH_ITEMS = new JEI();
    public static class JEI {
        public boolean enableJEIInfernalFurnaceCategory = true;
        public boolean enableJEILootBags = true;
        @Comment("Removes potion loot from Loot Bags drops to reduce spam.")
        public boolean removePotionLoot = true;
    }

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        //Bosses
        public boolean enableBossCultistPraetor = true;
        public boolean enableBossGreaterCrimsonPortal = true;
        public boolean enableBossEldritchGolem = true;
        public boolean enableBossEldritchWarden = true;
        public boolean enableBossTaintacleGiant = false;

        //Cultists
        public boolean enableCultistCleric = true;
        public boolean enableCultistKnight = true;

        //Tainted
        public boolean enableTaintacle = true;
        public boolean enableTaintacleSmall = true;
        public boolean enableTaintCrawler = true;
        public boolean enableTaintSeed = true;
        public boolean enableTaintSeedPrime = true;
        public boolean enableTaintSwarm = true;

        //Monsters
        public boolean enableAngryZombie = true;
        public boolean enableFuriousZombie = true;
        public boolean enableEldrichCrab = true;
        public boolean enableFireBat = true;
        public boolean enablePech = true;
        public boolean enableThaumicSlime = true;
        public boolean enableWisp = true;
    }
}
