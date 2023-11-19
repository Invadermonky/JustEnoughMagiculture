package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.bewitchment.Bewitchment;
import com.bewitchment.ModConfig;
import com.bewitchment.common.entity.living.*;
import com.bewitchment.common.entity.spirit.demon.*;
import com.bewitchment.common.entity.spirit.ghost.EntityBlackDog;
import com.bewitchment.common.entity.spirit.ghost.EntityGhost;
import com.bewitchment.common.item.ItemModSeeds;
import com.bewitchment.registry.ModObjects;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigBewitchment;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class JERBewitchment extends JERBase implements IJERIntegration {
    public final JEMConfigBewitchment.JER jerConfig = JEMConfig.BEWITCHMENT.JUST_ENOUGH_RESOURCES;

    public JERBewitchment(boolean enableJERMobs, boolean enableJERPlants) {
        if(enableJERMobs) registerModEntities();
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModEntities() {
        if (jerConfig.JER_MOBS.enableBafometyr) {
            registerMob(new EntityBafometyr(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.bafometyr.bafometyrBiomes), new ResourceLocation(Bewitchment.MODID, "entities/bafometyr"));
            adjustHumanoidRenderHook(EntityBafometyr.class);
        }

        if (jerConfig.JER_MOBS.enableBaphomet) {
            EntityBaphomet baphomet = new EntityBaphomet(world);
            baphomet.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModObjects.caduceus));
            registerMob(baphomet, LightLevel.any, new String[] {"None"}, new ResourceLocation(Bewitchment.MODID, "entities/baphomet"));
            adjustLargeHumanoidRenderHook(baphomet.getClass());
        }

        if (jerConfig.JER_MOBS.enableBlackDog) {
            registerMob(new EntityBlackDog(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.blackDog.blackDogBiomes), new ResourceLocation(Bewitchment.MODID, "entities/black_dog"));
            adjustCreatureRenderHook(EntityBlackDog.class);
        }

        if (jerConfig.JER_MOBS.enableCambion) {
            registerMob(new EntityCambion(world), LightLevel.any, new ResourceLocation(Bewitchment.MODID, "entities/cambion"));
            adjustHumanoidRenderHook(EntityCambion.class);
        }

        if (jerConfig.JER_MOBS.enableCleaver) {
            EntityCleaver cleaver = new EntityCleaver(world);
            cleaver.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModObjects.cleaver_sword));
            cleaver.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
            cleaver.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
            registerMob(cleaver, LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.cleaver.cleaverBiomes), new ResourceLocation(Bewitchment.MODID, "entities/cleaver"));
            adjustHumanoidRenderHook(cleaver.getClass());
        }

        if (jerConfig.JER_MOBS.enableDemon) {
            registerMob(new EntityDemon(world), LightLevel.any, new String[] {"None"}, getDemonDrops());
            adjustLargeHumanoidRenderHook(EntityDemon.class);
        }

        if (jerConfig.JER_MOBS.enableDemoness) {
            EntityDemoness demoness = new EntityDemoness(world);
            registerMob(demoness, LightLevel.any, new String[] {"None"}, getDemonDrops());
        }

        if (jerConfig.JER_MOBS.enableDruden) {
            registerMob(new EntityDruden(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.druden.drudenBiomes), new ResourceLocation(Bewitchment.MODID, "entities/druden"));
            adjustHumanoidRenderHook(EntityDruden.class);
        }

        if (jerConfig.JER_MOBS.enableFeuerwurm) {
            registerMob(new EntityFeuerwurm(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.feuerwurm.feuerwurmBiomes), new ResourceLocation(Bewitchment.MODID, "entities/feuerwurm"));
        }

        if (jerConfig.JER_MOBS.enableGhost) {
            registerMob(new EntityGhost(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.ghost.ghostBiomes), new ResourceLocation(Bewitchment.MODID, "entities/ghost"));
            adjustHumanoidRenderHook(EntityGhost.class);
        }

        if (jerConfig.JER_MOBS.enableHellhound) {
            registerMob(new EntityHellhound(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.hellhound.hellhoundBiomes), new ResourceLocation(Bewitchment.MODID, "entities/hellhound"));
            adjustCreatureRenderHook(EntityHellhound.class);
        }

        if (jerConfig.JER_MOBS.enableImp) {
            registerMob(new EntityImp(world), LightLevel.any, new String[] {"None"}, new ResourceLocation(Bewitchment.MODID, "entities/imp"));
            registerRenderHook(EntityImp.class, (renderInfo, e) -> {
                GlStateManager.translate(-0.05, -0.4, 0);
                return renderInfo;
            });
        }

        if (jerConfig.JER_MOBS.enableLeonard) {
            EntityLeonard leonard = new EntityLeonard(world);
            leonard.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModObjects.leonards_wand));
            registerMob(leonard, LightLevel.any, new String[] {"None"}, new ResourceLocation(Bewitchment.MODID, "entities/leonard"));
            adjustLargeHumanoidRenderHook(leonard.getClass());
        }

        if (jerConfig.JER_MOBS.enableLizard) {
            registerMob(new EntityLizard(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.lizard.lizardBiomes), new ResourceLocation(Bewitchment.MODID, "entities/lizard"));
            adjustSmallCreatureRenderHook(EntityLizard.class);
        }

        if (jerConfig.JER_MOBS.enableOwl) {
            registerMob(new EntityOwl(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.owl.owlBiomes), new ResourceLocation(Bewitchment.MODID, "entities/owl"));
        }

        if (jerConfig.JER_MOBS.enableRaven) {
            registerMob(new EntityRaven(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.raven.ravenBiomes), new ResourceLocation(Bewitchment.MODID, "entities/raven"));
        }

        if (jerConfig.JER_MOBS.enableShadowPerson) {
            registerMob(new EntityShadowPerson(world), LightLevel.any, new String[] {"None"}, new ResourceLocation(Bewitchment.MODID, "entities/shadow_person"));
            adjustHumanoidRenderHook(EntityShadowPerson.class);
        }

        if (jerConfig.JER_MOBS.enableSnake) {
            EntitySnake snake = new EntitySnake(world);
            registerMob(snake, LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.snake.snakeBiomes), new ResourceLocation(Bewitchment.MODID, "entities/snake"));
            adjustSmallCreatureRenderHook(snake.getClass());
        }

        if (jerConfig.JER_MOBS.enableToad) {
            registerMob(new EntityToad(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.toad.toadBiomes), new ResourceLocation(Bewitchment.MODID, "entities/toad"));
            adjustSmallCreatureRenderHook(EntityToad.class);
        }

        if (jerConfig.JER_MOBS.enableWerewolf) {
            registerMob(new EntityWerewolf(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(ModConfig.mobSpawns.werewolf.werewolfBiomes), new ResourceLocation(Bewitchment.MODID, "entities/werewolf"));
            adjustHumanoidRenderHook(EntityWerewolf.class);
        }
    }

    private LootDrop[] getDemonDrops() {
        ArrayList<LootDrop> demonDrops = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            ResourceLocation loc = new ResourceLocation(ModIds.BEWITCHMENT.MOD_ID, "entities/demon" + i);
            for(LootDrop drop : LootTableHelper.toDrops(manager.getLootTableFromLocation(loc))) {
                if(!demonDrops.contains(drop)) {
                    demonDrops.add(drop);
                }
            }
        }
        return demonDrops.toArray(new LootDrop[0]);
    }

    private void adjustSmallCreatureRenderHook(Class<? extends EntityLiving> entity) {
        registerRenderHook(entity, (renderInfo, e) -> {
            GlStateManager.scale(2.0, 2.0, 2.0);
            GlStateManager.translate(0, 0.1, 0);
            return renderInfo;
        });
    }

    private void adjustCreatureRenderHook(Class<? extends EntityLiving> entity) {
        registerRenderHook(entity, (renderInfo, e) -> {
            GlStateManager.translate(-0.05, -0.4, 0);
            return renderInfo;
        });
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> entity) {
        registerRenderHook(entity, (renderInfo, e) -> {
            GlStateManager.translate(-0.05, -0.6, 0);
            return renderInfo;
        });
    }

    private void adjustLargeHumanoidRenderHook(Class<? extends EntityLiving> entity) {
        registerRenderHook(entity, (renderInfo, e) -> {
            GlStateManager.scale(0.9, 0.9, 0.9);
            GlStateManager.translate(-0.05, -0.8, 0);
            return renderInfo;
        });
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.JER_PLANTS.enableAconitum) {
            registerPlant((ItemModSeeds) ModObjects.aconitum_seeds,
                    new PlantDrop(new ItemStack(ModObjects.aconitum), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.aconitum_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableBelladonna) {
            registerPlant((ItemModSeeds) ModObjects.belladonna_seeds,
                    new PlantDrop(new ItemStack(ModObjects.belladonna), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.belladonna_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableGarlic) {
            registerPlant((ItemModSeeds) ModObjects.garlic_seeds,
                    new PlantDrop(new ItemStack(ModObjects.garlic), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.garlic_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableHellebore) {
            registerPlant((ItemModSeeds) ModObjects.hellebore_seeds,
                    new PlantDrop(new ItemStack(ModObjects.hellebore), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.hellebore_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableMandrake) {
            registerPlant((ItemModSeeds) ModObjects.mandrake_seeds,
                    new PlantDrop(new ItemStack(ModObjects.mandrake_root), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.mandrake_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableWhiteSage) {
            registerPlant((ItemModSeeds) ModObjects.white_sage_seeds,
                    new PlantDrop(new ItemStack(ModObjects.white_sage), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.white_sage_seeds), 0, 1));
        }

        if(jerConfig.JER_PLANTS.enableWormwood) {
            registerPlant((ItemModSeeds) ModObjects.wormwood_seeds,
                    new PlantDrop(new ItemStack(ModObjects.wormwood), 1, 1),
                    new PlantDrop(new ItemStack(ModObjects.wormwood_seeds), 0, 1));
        }
    }
}
