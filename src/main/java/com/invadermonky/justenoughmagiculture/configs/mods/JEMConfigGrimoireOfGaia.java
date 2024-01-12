package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ConfigHelper.MobJER;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigGrimoireOfGaia {
    private static final String MOD_NAME = ConstantNames.GRIMOIRE_OF_GAIA;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";
    public JEMConfigGrimoireOfGaia() {}

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Items settings.")
    public JEI JUST_ENOUGH_ITEMS = new JEI();
    public static class JEI {
        @Comment("Adds the Grimiore of Gaia loot boxes to the Loot Bag category. This is used to fix the otherwise \n" +
                "hardcoded loot table for GoG's built-in category.")
        public boolean enableJEILootBags = true;
    }

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {

        public boolean enableAntScavenger = true;
        public boolean enableAntWorker = true;
        public boolean enableAnubis = true;
        public boolean enableArachne = true;
        public boolean enableBanshee = true;
        public boolean enableBaphomet = true;
        public boolean enableBee = true;
        public boolean enableBeholder = true;
        public boolean enableBoneKnight = true;
        public boolean enableCecaelia = true;
        public boolean enableCentaur = true;
        public boolean enableCobbleGolem = true;
        public boolean enableCobblestoneGolem = true;
        public boolean enableCreep = true;
        public boolean enableCyclops = true;
        public boolean enableDeathword = true;
        public boolean enableDhampir = true;
        public boolean enableDryad = true;
        public boolean enableDullahan = true;
        public boolean enableDwarf = true;
        public boolean enableDwarfArcher = true;
        public boolean enableDwarfMiner = true;
        public boolean enableEnderDragonGirl = true;
        public boolean enableEnderEye = true;
        public boolean enableFleshLich = true;
        public boolean enableGelatinousSlime = true;
        public boolean enableGoblin = true;
        public boolean enableGoblinArcher = true;
        public boolean enableGravemite = true;
        public boolean enableGryphon = true;
        public boolean enableHarpy = true;
        public boolean enableHarpyWizard = true;
        public boolean enableHunter = true;
        public boolean enableKikimora = true;
        public boolean enableKobold = true;
        public boolean enableMandragora = true;
        public boolean enableMatango = true;
        public boolean enableMermaid = true;
        public boolean enableMimic = true;
        public boolean enableMinotaur = true;
        public boolean enableMinotaurus = true;
        public boolean enableMinotaurusArcher = true;
        public boolean enableMummy = true;
        public boolean enableNaga = true;
        public boolean enableNineTails = true;
        public boolean enableOni = true;
        public boolean enableOrc = true;
        public boolean enableOrcWizard = true;
        public boolean enableSatyress = true;
        public boolean enableSelkie = true;
        public boolean enableSHAMAN = true;
        public boolean enableSHARKO = true;
        public boolean enableSiren = true;
        public boolean enableSludgeGirl = true;
        public boolean enableSphinx = true;
        public boolean enableSporeling = true;
        public boolean enableSpriggan = true;
        public boolean enableSuccubus = true;
        public boolean enableToad = true;
        public boolean enableValkyrie = true;
        public boolean enableVampire = true;
        public boolean enableWerecat = true;
        public boolean enableWitch = true;
        public boolean enableWitherCow = true;
        public boolean enableYeti = true;
        public boolean enableYukiOnna = true;

        //No spawn mobs
        public MobJER FERAL_GOBLIN = new MobJER();
        public MobJER FERAL_GOBLIN_ARCHER = new MobJER();
        public MobJER FERAL_GOBLIN_BOMBER = new MobJER();
        public MobJER ILLAGER_BUTLER = new MobJER();
        public MobJER ILLAGER_INCINERATOR = new MobJER();
        public MobJER ILLAGER_INQUISITOR = new MobJER();

    }
}
