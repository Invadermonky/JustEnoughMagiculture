package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigFamiliarFauna;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import familiarfauna.config.ConfigurationHandler;
import familiarfauna.entities.*;
import familiarfauna.init.ModConfiguration;
import familiarfauna.init.ModLootTable;
import jeresources.api.conditionals.LightLevel;

public class JERFamiliarFauna extends JERBase implements IJERIntegration {
    private final JEMConfigFamiliarFauna.JER jerConfig = JEMConfig.FAMILIAR_FAUNA.JUST_ENOUGH_RESOURCES;

    public JERFamiliarFauna(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableButterfly && ConfigurationHandler.butterflyEnable) {
            registerMob(new EntityButterfly(world), LightLevel.any, 0, 1, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.butterflyBiomeList.toArray(new String[0])), ModLootTable.BUTTERFLY_LOOT);
        }

        if(jerConfig.enableDeer && ConfigurationHandler.deerEnable) {
            registerMob(new EntityDeer(world), LightLevel.any, 1, 3, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.deerBiomeList.toArray(new String[0])), ModLootTable.DEER_LOOT);
        }

        if(jerConfig.enableDragonfly && ConfigurationHandler.dragonflyEnable) {
            registerMob(new EntityDragonfly(world), LightLevel.any, 0, 1, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.dragonflyBiomeList.toArray(new String[0])), ModLootTable.DRAGONFLY_LOOT);
        }

        if(jerConfig.enablePixie && ConfigurationHandler.pixieEnable) {
            registerMob(new EntityPixie(world), LightLevel.any, 0, 1, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.pixieBiomeList.toArray(new String[0])), ModLootTable.PIXIE_LOOT);
        }

        if(jerConfig.enableSnail && ConfigurationHandler.snailEnable) {
            registerMob(new EntitySnail(world), LightLevel.any, 0, 1, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.snailBiomeList.toArray(new String[0])), ModLootTable.SNAIL_LOOT);
        }

        if(jerConfig.enableTurkey && ConfigurationHandler.turkeyEnable) {
            registerMob(new EntityTurkey(world), LightLevel.any, 1, 3, BiomeHelper.getBiomeNamesForBiomes(ModConfiguration.turkeyBiomeList.toArray(new String[0])), ModLootTable.TURKEY_LOOT);
        }
    }
}
