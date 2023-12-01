package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigAnimaniaFarm {
    private static final String MOD_NAME = ConstantNames.ANIMANIA_FARM;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables render fixes. Disabling this may cause some entities to not display in JER.")
    public boolean enableRenderFixes = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        @Comment("Registers Male animal variants. These share the same loot table as female variants.")
        public boolean registerMaleAnimals = true;
        @Comment("Register Female animal variants. These share the same loot table as male variants.")
        public boolean registerFemaleAnimals = false;
        @Comment("If only male variants are being registered, inverts cow gender based registries.")
        public boolean invertCows = true;

        public final Animals ANIMALS = new Animals();

        public static class Animals {
            //Chickens
            public boolean enableChickenLeghorn = true;
            public boolean enableChickenOrpington = true;
            public boolean enableChickenPlymouthRock = true;
            public boolean enableChickenRhodeIslandRed = true;
            public boolean enableChickenWyandotte = true;

            //Cows
            public boolean enableCowAngus = true;
            public boolean enableCowFriesian = true;
            public boolean enableCowHereford = true;
            public boolean enableCowHighland = true;
            public boolean enableCowHolstein = true;
            public boolean enableCowJersey = true;
            public boolean enableCowLonghorn = true;
            public boolean enableCowMooshroom = true;

            //Goats
            public boolean enableGoatAlpine = true;
            public boolean enableGoatAngora = true;
            public boolean enableGoatFainting = true;
            public boolean enableGoatKiko = true;
            public boolean enableGoatKinder = true;
            public boolean enableGoatNigerianDwarf = true;
            public boolean enableGoatPygmy = true;

            //Draft Horses
            public boolean enableDraftHorseMare = true;
            public boolean enableDraftHorseStallion = true;

            //Pigs
            public boolean enablePigDuroc = true;
            public boolean enablePigHampshire = true;
            public boolean enablePigLargeBlack = true;
            public boolean enablePigLargeWhite = true;
            public boolean enablePigOldSpot = true;
            public boolean enablePigYorkshire = true;

            //Sheep
            public boolean enableSheepDorper = true;
            public boolean enableSheepDorset = true;
            public boolean enableSheepFriesian = true;
            public boolean enableSheepJacob = true;
            public boolean enableSheepMerino = true;
            public boolean enableSheepSuffolk = true;
        }
    }
}
