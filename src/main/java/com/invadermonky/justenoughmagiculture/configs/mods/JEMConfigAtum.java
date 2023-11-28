package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigAtum {
    private static final String MOD_NAME = ConstantNames.ATUM;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Adds information pages relating to dirty and clean loot items")
    public boolean enableJEIInfoPages = true;

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
    @Comment("Enables JEM " + MOD_NAME + " JER villager integration.")
    public boolean enableJERVillagers = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Plants JER_PLANTS = new Plants();
        public Villagers JER_VILLAGERS = new Villagers();

        public static class Mobs {
            //Animals
            public boolean enableCamel = true;
            public boolean enableDesertRabbit = true;
            public boolean enableDesertWolf = true;
            public boolean enableDesertWolfAlpha = true;
            public boolean enableScarab = true;
            public boolean enableTarantula = true;
            //Bandits
            public boolean enableAssassin = true;
            public boolean enableBarbarian = true;
            public boolean enableBrigand = true;
            public boolean enableNomad = true;
            //Efreet
            public boolean enableSunspeaker = true;
            //Stone Constructs
            public boolean enableStoneguard = true;
            public boolean enableStonewarden = true;
            //Undead
            public boolean enableBonestorm = true;
            public boolean enableForsaken = true;
            public boolean enableMummy = true;
            public boolean enableWraith = true;
        }

        public static class Plants {
            public boolean enableAnputsFingers = true;
            public boolean enableDate = true;
            public boolean enableEmmer = true;
            public boolean enableFlax = true;
            public boolean enableOasisGrass = true;
        }

        public static class Villagers {
            public boolean enableSunspeaker = true;
        }
    }
}
