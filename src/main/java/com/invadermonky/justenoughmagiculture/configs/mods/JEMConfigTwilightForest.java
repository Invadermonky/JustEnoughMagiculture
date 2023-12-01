package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantIds;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigTwilightForest {
    private static final String MOD_ID = ConstantIds.TWILIGHT_FOREST;
    private static final String MOD_NAME = ConstantNames.TWILIGHT_FOREST;
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
        //Bosses
        public boolean enableHydra = true;
        public boolean enableKnightPhantom = true;
        public boolean enableTwilightLich = true;
        public boolean enableMinoshroom = true;
        public boolean enableNaga = true;
        @Comment("Questing Ram quest rewards are shown as dungeon loot.")
        public boolean enableQuestingRam = true;
        public boolean enableUrGhast = true;
        public boolean enableYetiAlpha = true;

        //mobs
        public boolean enableArmoredGiant = true;
        public boolean enableBlockGoblin = true;
        public boolean enableDeathTome = true;
        public boolean enableFireBeetle = true;
        public boolean enableGiantMiner = true;
        public boolean enableGoblinKnight = true;
        public boolean enableHedgeSpider = true;
        public boolean enableHelmetCrab = true;
        public boolean enableHostileWolf = true;
        public boolean enableUnstableIceCore = true;
        public boolean enableStableIceCore = true;
        public boolean enableKingSpider = true;
        public boolean enableKobold = true;
        public boolean enableMazeSlime = true;
        public boolean enableCarminiteGhasting = true;
        public boolean enableMInotaur = true;
        public boolean enableMistWolf = true;
        public boolean enableMosquitoSwarm = true;
        public boolean enablePinchBeetle = true;
        public boolean enableRedcapGoblin = true;
        public boolean enableRedcapSapper = true;
        public boolean enableSkeletonDruid = true;
        public boolean enableSlimeBeetle = true;
        public boolean enableSnowGuardian = true;
        public boolean enableSwarmSpider = true;
        public boolean enableCarminiteBroodling = true;
        public boolean enableCarminiteGhast = true;
        public boolean enableCarminiteGolem = true;
        public boolean enableTowerwoodBorer = true;
        public boolean enableCaveTroll = true;
        public boolean enableWinterWolf = true;
        public boolean enableTwilightWraith = true;
        public boolean enableYeti = true;

        //passive
        public boolean enableBighornSheep = true;
        public boolean enableWildBoar = true;
        public boolean enableDwarfRabbit = true;
        public boolean enableWildDeer = true;
        public boolean enableFirefly = true;
        public boolean enablePenguin = true;
        public boolean enableForestRaven = true;
        public boolean enableForestSquirrel = true;
        public boolean enableTinyBird = true;
    }
}
