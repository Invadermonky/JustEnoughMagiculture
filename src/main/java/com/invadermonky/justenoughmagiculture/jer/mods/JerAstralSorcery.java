package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.invadermonky.justenoughmagiculture.util.DungeonHelper;
import hellfirepvp.astralsorcery.common.util.LootTableUtil;
import net.minecraft.util.ResourceLocation;

public class JerAstralSorcery extends AbstractJerIntegration {
    private static JerAstralSorcery instance;

    public static JerAstralSorcery getInstance() {
        return instance == null ? instance = new JerAstralSorcery() : instance;
    }

    @Override
    public void initJerDungeons() {
        if(!ConfigHandlerJEM.jer_dungeons.astral_sorcery)
            return;

        //Astral Sorcery
        ResourceLocation asShrine = LootTableUtil.LOOT_TABLE_SHRINE;
        DungeonHelper.registerDungeon(asShrine.toString(), DungeonHelper.getDungeonLocalKey("astralsorcery", "shrine"), asShrine);
    }
}
