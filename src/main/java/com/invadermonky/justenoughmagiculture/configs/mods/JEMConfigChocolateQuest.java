package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigChocolateQuest {
    private static final String MOD_NAME = ConstantNames.CHOCOLATE_QUEST;
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER dungeon integration.")
    public boolean enableJERDungeons = true;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " JER mob integration.")
    public boolean enableJERMobs = true;

    @RequiresMcRestart
    @Comment("JEM " + MOD_NAME + " Just Enough Resources settings. Be sure to set \"B:skipHiddenEntityRendering=false\" \n" +
            "in cqrepoured.cfg or JER entries will not render correctly.")
    public JER JUST_ENOUGH_RESOURCES = new JER();
    public static class JER {
        public Bosses CQR_BOSSES = new Bosses();
        public Mobs CQR_MOBS = new Mobs();
        public Mounts CQR_MOUNTS = new Mounts();
        
        public static class Bosses {
            public boolean enableAbyssWalkerKing = true;
            public boolean enableBoarMage = true;
            public boolean enableDragonNether = true;
            public boolean enableEnderCalamity = true;
            public boolean enableEndermanKing = true;
            public boolean enableExterminator = true;
            public boolean enableGiantTurtle = true;
            public boolean enableGoblinShaman = true;
            public boolean enableLich = true;
            public boolean enableNecromancer = true;
            public boolean enablePirateCaptain = true;
            public boolean enableShelob = true;
            public boolean enableSpectreLord = true;
        }
        
        public static class Mobs {
            public boolean enableAbyssWalker = true;
            public boolean enableBoarman = true;
            public boolean enableDwarf = true;
            public boolean enableEnderman = true;
            public boolean enableGoblin = true;
            public boolean enableGolem = true;
            public boolean enableGremlin = true;
            public boolean enableHuman = true;
            public boolean enableIllager = true;
            public boolean enableMandril = true;
            public boolean enableMinotaur = true;
            public boolean enableMummy = true;
            public boolean enableNPC = true;
            public boolean enableOgre = true;
            public boolean enableOrc = true;
            public boolean enablePirate = true;
            public boolean enableSkeleton = true;
            public boolean enableSpectre = true;
            public boolean enableTriton = true;
            public boolean enableZombie = true;
        }

        public static class Mounts {
            public boolean enableGiantEndermite = true;
            public boolean enableGiantSilverfish = true;
            public boolean enableGiantSilverfishGreen = true;
            public boolean enableGiantSilverfishRed = true;
        }
    }
}
