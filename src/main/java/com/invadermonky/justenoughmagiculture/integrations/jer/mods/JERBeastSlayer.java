package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigBeastSlayer;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import com.unoriginal.beastslayer.config.BeastSlayerConfig;
import com.unoriginal.beastslayer.entity.Entities.*;
import com.unoriginal.beastslayer.init.ModItems;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.util.ArrayList;
import java.util.List;

public class JERBeastSlayer extends JERBase implements IJERIntegration {
    JEMConfigBeastSlayer.JER jerConfig = JEMConfig.BEAST_SLAYER.JUST_ENOUGH_RESOURCES;

    public JERBeastSlayer(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        ResourceLocation lootTable = new ResourceLocation(ModIds.BEAST_SLAYER.MOD_ID, "structures/circus");
        registerDungeonLoot(lootTable.toString(), StringHelper.getDungeonTranslationKey(ModIds.BEAST_SLAYER.MOD_ID, "circus"), lootTable);
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableBonepile && BeastSlayerConfig.bonepileSpawnChance > 0) {
            registerMob(new EntityBonepile(world), LightLevel.hostile, EntityBonepile.LOOT);
            registerRenderHook(EntityBonepile.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.3,1.3,1.3);
                return renderInfo;
            }));
        }

        if(jerConfig.enableBoulderingZombie) {
            registerMob(new EntityBoulderer(world), LightLevel.hostile, LootTableList.ENTITIES_ZOMBIE);
        }

        if(jerConfig.enableDamcell && BeastSlayerConfig.damcellSpawnChance > 0) {
            registerMob(new EntityDamcell(world), LightLevel.hostile, EntityDamcell.LOOT);
            registerRenderHook(EntityDamcell.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.8, 0);
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.enableFrostwalker) {
            List<LootDrop> whiteDrops = new ArrayList<>(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
            List<LootDrop> redDrops = new ArrayList<>(whiteDrops);
            whiteDrops.add(new LootDrop(new ItemStack(ModItems.ICE_WAND), 1, 1, 0.25f, Conditional.playerKill));
            whiteDrops.add(new LootDrop(new ItemStack(ModItems.ICE_DART), 0, 2, 1.0f));
            redDrops.add(new LootDrop(new ItemStack(ModItems.ICE_WAND_RED), 1, 1, 0.25f, Conditional.playerKill));
            redDrops.add(new LootDrop(new ItemStack(ModItems.ICE_DART, 1, 1), 0, 2, 1.0f));

            EntityFrostWalker whiteWalker = new EntityFrostWalker(world);
            whiteWalker.setVariant(0);
            registerMob(whiteWalker, LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SNOWY), whiteDrops.toArray(new LootDrop[0]));

            EntityFrostWalker redWalker = new EntityFrostWalker(world);
            redWalker.setVariant(1);
            registerMob(redWalker, LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.SNOWY), redDrops.toArray(new LootDrop[0]));


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

        if(jerConfig.enableSandMonster && BeastSlayerConfig.sandmonsterSpawnChance > 0) {
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
            if(BeastSlayerConfig.zealotSpawnEverywhere) {
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
