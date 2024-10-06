package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigAstralSorcery;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import hellfirepvp.astralsorcery.common.util.LootTableUtil;

public class JERAstralSorcery extends JERBase implements IJERIntegration {
    private static final JEMConfigAstralSorcery jerConfig = JEMConfig.ASTRAL_SORCERY;

    public JERAstralSorcery(boolean enableJERDungeons) {
        if(enableJERDungeons) registerModDungeons();
    }

    @Override
    public void registerModDungeons() {
        registerDungeonLoot(
                LootTableUtil.LOOT_TABLE_SHOOTING_STAR.toString(),
                StringHelper.getDungeonTranslationKey(LootTableUtil.LOOT_TABLE_SHOOTING_STAR),
                LootTableUtil.LOOT_TABLE_SHOOTING_STAR);
        registerDungeonLoot(
                LootTableUtil.LOOT_TABLE_SHRINE.toString(),
                StringHelper.getDungeonTranslationKey(LootTableUtil.LOOT_TABLE_SHRINE),
                LootTableUtil.LOOT_TABLE_SHRINE);
    }
}
