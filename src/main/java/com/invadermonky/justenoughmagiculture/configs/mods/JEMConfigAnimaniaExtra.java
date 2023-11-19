package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigAnimaniaExtra {
    private static final String MOD_NAME = ConstantNames.ANIMANIA_EXTRA;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        @Comment("Registers Male animal variants. These share the same loot table as female variants.")
        public boolean registerMaleAnimals = true;
        @Comment("Register Female animal variants. These share the same loot table as male variants.")
        public boolean registerFemaleAnimals = false;

        public final Animals ANIMALS = new Animals();

        public static class Animals {
            public boolean enableDartFrog = true;
            public boolean enableFrog = true;
            public boolean enableToad = true;

            public boolean enableHamster = true;
            public boolean enableHedgehogAlbino = true;
            public boolean enableHedgehog = true;

            public boolean enableFerretGray = true;
            public boolean enableFerretWhite = true;

            public boolean enablePeafowlBlue = true;
            public boolean enablePeafowlCharcoal = true;
            public boolean enablePeafowlOpal = true;
            public boolean enablePeafowlPeach = true;
            public boolean enablePeafowlPurple = true;
            public boolean enablePeafowlTaupe = true;
            public boolean enablePeafowlWhite = true;

            public boolean enableRabbitChinchilla = true;
            public boolean enableRabbitCottontail = true;
            public boolean enableRabbitDutch = true;
            public boolean enableRabbitHavana = true;
            public boolean enableRabbitJack = true;
            public boolean enableRabbitLop = true;
            public boolean enableRabbitNewZealand = true;
            public boolean enableRabbitRex = true;
        }
    }
}
