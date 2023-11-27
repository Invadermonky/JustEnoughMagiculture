package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigBeastSlayer;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import com.unoriginal.ancientbeasts.config.AncientBeastsConfig;
import com.unoriginal.ancientbeasts.entity.Entities.*;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary.Type;

public class JERBeastSlayer extends JERBase implements IJERIntegration {
    JEMConfigBeastSlayer.JER jerConfig = JEMConfig.BEAST_SLAYER.JUST_ENOUGH_RESOURCES;

    public JERBeastSlayer(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        ResourceLocation lootTable = new ResourceLocation(ModIds.BEASTSLAYER.MOD_ID, "structures/circus");
        registerDungeonLoot(lootTable.toString(), StringHelper.getDungeonTranslationKey(ModIds.BEASTSLAYER.MOD_ID, "circus"), lootTable);
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableBonepile && AncientBeastsConfig.bonepileSpawnChance > 0) {
            registerMob(new EntityBonepile(world), LightLevel.hostile, EntityBonepile.LOOT);
            registerRenderHook(EntityBonepile.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.3,1.3,1.3);
                return renderInfo;
            }));
        }

        if(jerConfig.enableBoulderingZombie) {
            registerMob(new EntityBoulderer(world), LightLevel.hostile, LootTableList.ENTITIES_ZOMBIE);
        }

        if(jerConfig.enableDamcell && AncientBeastsConfig.damcellSpawnChance > 0) {
            registerMob(new EntityDamcell(world), LightLevel.hostile, EntityDamcell.LOOT);
            registerRenderHook(EntityDamcell.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.8, 0);
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.enableFrostwalker) {
            registerMob(new EntityFrostWalker(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SNOWY), LootTableList.ENTITIES_ZOMBIE);
            registerRenderHook(EntityFrostWalker.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.3,1.3,1.3);
                GlStateManager.translate(0.1,0,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableGhost) {
            registerMob(new EntityGhost(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SPOOKY), EntityGhost.LOOT);
            adjustHumanoidRenderHook(EntityGhost.class);
        }

        if(jerConfig.enableGiant) {
            registerMob(new EntityGiant(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.PLAINS, Biomes.MUTATED_PLAINS), EntityGiant.LOOT);
            registerRenderHook(EntityGiant.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.1,-1.5, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableNekros) {
            registerMob(new EntityNekros(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SPOOKY), EntityNekros.LOOT);
            adjustHumanoidRenderHook(EntityNekros.class);
        }

        if(jerConfig.enableNetherhound) {
            registerMob(new EntityNetherhound(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.NETHER), EntityNetherhound.LOOT);
        }

        if(jerConfig.enableOwlstack) {
            registerMob(new EntityOwlstack(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST_HILLS), EntityOwlstack.LOOT);
            registerRenderHook(EntityOwlstack.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,0.15, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableRiftedEnderman) {
            registerMob(new EntityRiftedEnderman(world), LightLevel.hostile, EntityRiftedEnderman.LOOT);
            adjustHumanoidRenderHook(EntityRiftedEnderman.class);
        }

        if(jerConfig.enableSandMonster && AncientBeastsConfig.isSandmonsterEnabled) {
            registerMob(new EntitySandy(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.DESERT, Biomes.DESERT_HILLS), EntitySandy.LOOT);
            registerRenderHook(EntitySandy.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.08,0.3, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableVessel) {
            registerMob(new EntityVessel(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST), EntityVessel.LOOT);
        }

        if(jerConfig.enableZealot) {
            if(AncientBeastsConfig.zealotSpawnEverywhere) {
                registerMob(new EntityZealot(world), LightLevel.hostile, EntityZealot.LOOT);
            } else {
                registerMob(new EntityZealot(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SPOOKY), EntityZealot.LOOT);
            }
            adjustHumanoidRenderHook(EntityZealot.class);
        }
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.45, 0);
            return renderInfo;
        }));
    }
}
