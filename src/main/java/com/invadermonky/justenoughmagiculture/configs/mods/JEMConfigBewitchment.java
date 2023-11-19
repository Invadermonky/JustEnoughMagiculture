package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigBewitchment {
    private static final String MOD_NAME = ConstantNames.BEWITCHMENT;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER plant integration.")
    public boolean enableJERPlants = true;

    @RequiresMcRestart
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Mobs JER_MOBS = new Mobs();
        public Plants JER_PLANTS = new Plants();

        public static class Mobs {
                        public boolean enableBafometyr = true;
                        public boolean enableBaphomet = true;
                        public boolean enableBlackDog = true;
                        public boolean enableCambion = true;
                        public boolean enableCleaver = true;
                        public boolean enableDemon = true;
                        public boolean enableDemoness = true;
                        public boolean enableDruden = true;
                        public boolean enableFeuerwurm = true;
                        public boolean enableGhost = true;
                        public boolean enableHellhound = true;
                        public boolean enableImp = true;
                        public boolean enableLeonard = true;
                        public boolean enableLizard = true;
                        public boolean enableOwl = true;
                        public boolean enableRaven = true;
                        public boolean enableShadowPerson = true;
                        public boolean enableSnake = true;
                        public boolean enableToad = true;
                        public boolean enableWerewolf = true;
        }

        public static class Plants {
                        public boolean enableAconitum = true;
                        public boolean enableBelladonna = true;
                        public boolean enableGarlic = true;
                        public boolean enableHellebore = true;
                        public boolean enableMandrake = true;
                        public boolean enableWhiteSage = true;
                        public boolean enableWormwood = true;
        }
    }
}
