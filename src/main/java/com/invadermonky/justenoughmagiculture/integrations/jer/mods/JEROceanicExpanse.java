package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigOceanicExpanse;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import com.sirsquidly.oe.entity.*;
import com.sirsquidly.oe.util.handlers.ConfigHandler;
import com.sirsquidly.oe.util.handlers.LootTableHandler;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;

public class JEROceanicExpanse extends JERBase implements IJERIntegration {
    JEMConfigOceanicExpanse.JER jerConfig = JEMConfig.OCEANIC_EXPANSE.JUST_ENOUGH_RESOURCES;

    public JEROceanicExpanse(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        if(ConfigHandler.worldGen.shipwreck.enableShipwrecks) {
            registerOEDungeon("shipwreck_map", "chests");
            registerOEDungeon("shipwreck_supply", "chests");
            registerOEDungeon("shipwreck_treasure", "chests");
        }

        if(ConfigHandler.entity.crab.enableCrab)
            registerOEDungeon("crab_dig", "gameplay");

        if(ConfigHandler.worldGen.shellPatch.enableShellPatch)
            registerOEDungeon("shell_sand", "gameplay");
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableCod && ConfigHandler.entity.cod.enableCod) {
            registerMob(new EntityCod(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.OCEAN), LootTableHandler.ENTITIES_COD);
            adjustCreatureRenderHook(EntityCod.class);
        }

        if(jerConfig.enableCrab && ConfigHandler.entity.crab.enableCrab) {
            registerMob(new EntityCrab(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.BEACH), LootTableHandler.ENTITIES_CRAB);
        }

        if(jerConfig.enableDrowned && ConfigHandler.entity.drowned.enableDrowned) {
            registerMob(new EntityDrowned(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.OCEAN, Biomes.RIVER), LootTableHandler.ENTITIES_DROWNED);
        }

        if(jerConfig.enableGlowSquid) {
            registerMob(new EntityGlowSquid(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.DEEP_OCEAN), LootTableHandler.ENTITIES_GLOW_SQUID);
        }

        if(jerConfig.enablePickled && ConfigHandler.entity.pickled.enablePickled) {
            registerMob(new EntityPickled(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.OCEAN), LootTableHandler.ENTITIES_PICKLED);
        }

        if(jerConfig.enablePufferfish && ConfigHandler.entity.pufferfish.enablePufferfish) {
            registerMob(new EntityPufferfish(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.DEEP_OCEAN), LootTableHandler.ENTITIES_PUFFERFISH);
            adjustCreatureRenderHook(EntityPufferfish.class);
        }

        if(jerConfig.enableSalmon && ConfigHandler.entity.salmon.enableSalmon) {
            registerMob(new EntitySalmon(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.RIVER, Biomes.OCEAN), LootTableHandler.ENTITIES_SALMON);
            adjustCreatureRenderHook(EntitySalmon.class);
        }

        if(jerConfig.enableTropicalFish && ConfigHandler.entity.tropicalFish.enableTropicalFish) {
            EntityTropicalFish tropicalFish = new EntityTropicalFish(world);
            tropicalFish.setTropicalFishVariant(3);
            registerMob(tropicalFish, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.OCEAN), LootTableHandler.ENTITIES_TROPICAL_FISH);
            adjustCreatureRenderHook(tropicalFish.getClass());
        }

        if(jerConfig.enableTurtle && ConfigHandler.entity.turtle.enableTurtle) {
            registerMob(new EntityTurtle(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.BEACH), LootTableHandler.ENTITIES_TURTLE);
            adjustCreatureRenderHook(EntityTurtle.class);
        }
    }

    private void adjustCreatureRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(0, 0.5, 0);
            return renderInfo;
        }));
    }

    private void registerOEDungeon(String name, String type) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name, type);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String name, String type) {
            this.category = String.format("%s:%s/%s", ModIds.OCEANIC_EXPANSE.MOD_ID, type, name);
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.OCEANIC_EXPANSE.MOD_ID, name);
            this.lootTable = new ResourceLocation(category);
        }
    }
}
