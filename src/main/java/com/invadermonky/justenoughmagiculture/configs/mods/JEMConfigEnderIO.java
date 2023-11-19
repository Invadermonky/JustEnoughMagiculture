package com.invadermonky.justenoughmagiculture.configs.mods;

import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class JEMConfigEnderIO {
    private static final String MOD_NAME = ConstantNames.ENDERIO;

    @RequiresMcRestart
    @Comment("Enables JEM " + MOD_NAME + " injected loot JER integration. (Enderman Skull)")
    public boolean enableJERInjectedLoot = true;
}
