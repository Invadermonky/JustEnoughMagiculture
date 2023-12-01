package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.google.common.collect.Sets;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.erebus.JERRenderGlowWorm;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.erebus.JERRenderMagmaCrawler;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigErebus;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMConditional;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.blocks.EnumWood;
import erebus.entity.*;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;
import erebus.world.feature.plant.WorldGenRottenTreeStump;
import erebus.world.feature.structure.*;
import erebus.world.feature.tree.WorldGenGiantEucalyptus;
import erebus.world.loot.WeightedLootList;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JERErebus extends JERBase implements IJERIntegration {
    private static JERErebus instance;
    private final JEMConfigErebus.JER jerConfig = JEMConfig.EREBUS.JUST_ENOUGH_RESOURCES;
    private static THashMap<BiomeBaseErebus, THashSet<Class<? extends EntityLiving>>> mobSpawns;

    private JERErebus() {}

    public JERErebus(boolean enableJERDungeons, boolean enableJERMobs, boolean enableJERPlants) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        if(enableJERPlants) registerModPlants();
        getInstance();
    }

    public static JERErebus getInstance() {
        return instance != null ? instance : (instance = new JERErebus());
    }

    @Override
    public void registerModDungeons() {
        registerErebusDungeon("antlion_dungeon", WorldGenAntlionDungeon.chestLoot, 3, 10);
        registerErebusDungeon("antlion_lair", WorldGenAntlionLair.chestLoot, 10, 14);
        registerErebusDungeon("dragonfly_dungeon", WorldGenDragonflyDungeon.CHEST_LOOT, 5, 15);
        registerErebusDungeon("dung_pile", WorldGenDungPile.CHEST_LOOT, 8, 14);
        registerErebusDungeon("giant_eucalyptus", WorldGenGiantEucalyptus.chestLoot, 3, 10);
        registerErebusDungeon("locust_shrine", WorldGenLocustShrine.CHEST_LOOT,2, 3);
        registerErebusDungeon("rotten_tree_stump", WorldGenRottenTreeStump.chestLoot, 3, 10);
        registerErebusDungeon("spider_dungeon", WorldGenSpiderDungeons.chestLoot, 3, 10);
    }

    @Override
    public void registerModEntities() {
        populateMobSpawns();
        String[] allBiomes = BiomeHelper.getBiomeNamesForBiomes(mobSpawns.keySet().toArray(new Biome[0]));
        LootDrop exoPlateDrop = new LootDrop(EnumErebusMaterialsType.PLATE_EXO.createStack(), 0, 3, Conditional.affectedByLooting);

        if(jerConfig.JER_MOBS.enableAntlion) {
            registerMob(new EntityAntlion(world), LightLevel.hostile, getSpawnBiomes(EntityAntlion.class), exoPlateDrop);
        }

        if(jerConfig.JER_MOBS.enableAntlionGuardian) {
            registerMob(new EntityAntlionMiniBoss(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT), exoPlateDrop);
            registerRenderHook(EntityAntlionMiniBoss.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableAntlionOverlord) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(new ItemStack(ModBlocks.ANTLION_EGG)),
                    new LootDrop(EnumErebusMaterialsType.SOUL_CRYSTAL.createStack()),
                    new LootDrop(new ItemStack(ModItems.WAR_HAMMER))
            };
            registerMob(new EntityAntlionBoss(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT), drops);
            registerRenderHook(EntityAntlionBoss.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableBedBug) {
            registerMob(new EntityBedBug(world), LightLevel.hostile, allBiomes, new LootDrop(new ItemStack(Blocks.WOOL, 1, 0)));
            registerRenderHook(EntityBedBug.class, ((renderInfo, e) -> {
                GlStateManager.translate(0,-0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableBeetle) {
            registerMob(new EntityBeetle(world), LightLevel.hostile, getSpawnBiomes(EntityBeetle.class), exoPlateDrop);
            adjustBugRenderHookUp(EntityBeetle.class);
        }

        if(jerConfig.JER_MOBS.enableBeetleLarva) {
            List<LootDrop> larvaDrops = new ArrayList<>();
            LootDrop foodDrop = new LootDrop(new ItemStack(ModItems.EREBUS_FOOD, 1, 0), 1, 1, JEMConditional.isNotSquashed);
            foodDrop.smeltedItem = new ItemStack(ModItems.EREBUS_FOOD, 1, 1);
            larvaDrops.add(foodDrop);
            larvaDrops.add(new LootDrop(Items.SLIME_BALL, 1, 1, ((float)199/200), JEMConditional.isSquashed));
            larvaDrops.add(new LootDrop(Items.DIAMOND, 0, 1, ((float)1/200), JEMConditional.isSquashed, Conditional.rareDrop));
            registerMob(new EntityBeetleLarva(world), LightLevel.hostile, getSpawnBiomes(EntityBeetleLarva.class), larvaDrops);
            adjustBugRenderHookUp(EntityBeetleLarva.class);
        }

        if(jerConfig.JER_MOBS.enableBlackWidow) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.STRING, 0, 1, Conditional.affectedByLooting),
                    new LootDrop(Items.SPIDER_EYE, 0, 1, (float)1/3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.POISON_GLAND.createStack())
            };
            registerMob(new EntityBlackWidow(world), LightLevel.hostile, getSpawnBiomes(EntityBlackWidow.class), drops);
        }

        if(jerConfig.JER_MOBS.enableBogMaw) {
            registerMob(new EntityBogMaw(world), LightLevel.hostile, getSpawnBiomes(EntityBogMaw.class), new LootDrop(EnumErebusMaterialsType.BOGMAW_ROOT.createStack(), 0, 2, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableBombardierBeetle) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.GUNPOWDER, 1, 1),
                    new LootDrop(Items.BLAZE_POWDER, 1, 1),
                    exoPlateDrop
            };
            registerMob(new EntityBombardierBeetle(world), LightLevel.hostile, getSpawnBiomes(EntityBombardierBeetle.class), drops);
        }

        if(jerConfig.JER_MOBS.enableBombardierBeetleLarva) {
            List<LootDrop> larvaDrops = new ArrayList<>();
            LootDrop foodDrop = new LootDrop(new ItemStack(ModItems.EREBUS_FOOD, 1, 0), 1, 1, JEMConditional.isNotSquashed);
            foodDrop.smeltedItem = new ItemStack(ModItems.EREBUS_FOOD, 1, 1);
            larvaDrops.add(foodDrop);
            larvaDrops.add(new LootDrop(Items.SLIME_BALL, 1, 1, ((float)199/200), JEMConditional.isSquashed));
            larvaDrops.add(new LootDrop(Items.DIAMOND, 0, 1, ((float)1/200), JEMConditional.isSquashed));
            registerMob(new EntityBombardierBeetleLarva(world), LightLevel.hostile, getSpawnBiomes(EntityBombardierBeetleLarva.class), larvaDrops);
        }

        if(jerConfig.JER_MOBS.enableBotFly) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.FLY_WING.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.COMPOUND_EYES.createStack(), 0, 3, 0.25f, Conditional.affectedByLooting)
            };
            registerMob(new EntityBotFly(world), LightLevel.hostile, getSpawnBiomes(EntityBotFly.class), drops);
        }

        if(jerConfig.JER_MOBS.enableCentipede) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.BIO_VELOCITY.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.POISON_GLAND.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), 0, 1, 0.02f, Conditional.rareDrop)
            };
            registerMob(new EntityCentipede(world), LightLevel.hostile, getSpawnBiomes(EntityCentipede.class), drops);
        }

        if(jerConfig.JER_MOBS.enableChameleonTick) {
            registerMob(new EntityChameleonTick(world), LightLevel.hostile, getSpawnBiomes(EntityChameleonTick.class), new LootDrop(EnumErebusMaterialsType.CAMO_POWDER.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableCicada) {
            registerMob(new EntityCicada(world), LightLevel.hostile, getSpawnBiomes(EntityCicada.class), new LootDrop(EnumErebusMaterialsType.REPELLENT.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableCropWeevil) {
            LootDrop[] drops = new LootDrop[]{
                    new LootDrop(new ItemStack(ModBlocks.GIANT_FLOWER_STIGMA), 1, 3, 0.20f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.PUMPKIN_SEEDS), 1, 3, 0.20f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.MELON_SEEDS), 1, 3, 0.20f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.DYE, 1, 3), 1, 3, 0.20f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(ModItems.TURNIP), 1, 1, 0.015f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.NETHER_WART), 1, 1, 0.015f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.WHEAT), 1, 1, 0.015f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.REEDS), 1, 1, 0.015f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(EnumWood.BAMBOO.getSapling()), 1, 1, 0.01425f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.CARROT), 1, 1, 0.015f, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(Items.POTATO), 1, 1, 0.015f, Conditional.affectedByLooting)
            };
            registerMob(new EntityCropWeevil(world), LightLevel.hostile, getSpawnBiomes(EntityCropWeevil.class), drops);
            registerRenderHook(EntityCropWeevil.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableCrushroom) {
            registerMob(new EntityCrushroom(world), LightLevel.hostile, getSpawnBiomes(EntityCrushroom.class), new LootDrop(EnumErebusMaterialsType.HIDE_SHROOM.createStack(), 0, 2, Conditional.affectedByLooting));
            registerRenderHook(EntityCrushroom.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                GlStateManager.translate(-0.05,-0.8,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableDragonfly) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.DRAGONFLY_WING.createStack()),
                    new LootDrop(EnumErebusMaterialsType.COMPOUND_EYES.createStack(), 0, 1, 0.20f, Conditional.affectedByLooting),
                    new LootDrop(Items.ENDER_PEARL, 0, 1, 0.15f, Conditional.affectedByLooting)
            };
            registerMob(new EntityDragonfly(world), LightLevel.hostile, getSpawnBiomes(EntityDragonfly.class), drops);
            registerRenderHook(EntityDragonfly.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.5,1.5,1.5);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableFireAnt) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.MAGMA_CREAM, 1, 1, Conditional.affectedByLooting),
                    new LootDrop(Items.FIRE_CHARGE, 0, 1, 0.20f, Conditional.affectedByLooting)
            };
            registerMob(new EntityFireAnt(world), LightLevel.hostile, getSpawnBiomes(EntityFireAnt.class), drops);
            registerRenderHook(EntityFireAnt.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.1,1.1,1.1);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableFireAntSoldier) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.BLAZE_POWDER, 1, 1, Conditional.affectedByLooting),
                    new LootDrop(Items.BLAZE_ROD, 0, 1, 0.20f, Conditional.affectedByLooting)
            };
            registerMob(new EntityFireAntSoldier(world), LightLevel.hostile, getSpawnBiomes(EntityFireAntSoldier.class), drops);
        }

        if(jerConfig.JER_MOBS.enableFly) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.FLY_WING.createStack(), 0, 1, 0.10f),
                    new LootDrop(EnumErebusMaterialsType.COMPOUND_EYES.createStack(), 0, 1, 0.05f, Conditional.rareDrop)
            };
            registerMob(new EntityFly(world), LightLevel.hostile, getSpawnBiomes(EntityFly.class), drops);
            registerRenderHook(EntityFly.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableFungalWeevil) {
            List<LootDrop> drops = new ArrayList<>();
            drops.add(new LootDrop(new ItemStack(Blocks.BROWN_MUSHROOM), 0.1667f));
            drops.add(new LootDrop(new ItemStack(Blocks.RED_MUSHROOM), 0.1667f));
            float chance = (float) 2/3 * 1 / BiomeDecoratorFungalForest.MUSHROOMS.length;
            for(Block block : BiomeDecoratorFungalForest.MUSHROOMS) {
                drops.add(new LootDrop(new ItemStack(block), chance));
            }
            registerMob(new EntityFungalWeevil(world), LightLevel.hostile, getSpawnBiomes(EntityFungalWeevil.class), drops);
            registerRenderHook(EntitySolifuge.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.6,1.6,1.6);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableGlowWorm) {
            registerMob(new EntityGlowWorm(world), LightLevel.hostile, getSpawnBiomes(EntityGlowWorm.class), new LootDrop(EnumErebusMaterialsType.BIO_LUMINESCENCE.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableGrasshopper) {
            LootDrop foodDrop = new LootDrop(EnumFoodType.GRASSHOPPER_LEG_RAW.createStack(), 0, 3, Conditional.affectedByLooting);
            foodDrop.smeltedItem = EnumFoodType.GRASSHOPPER_LEG_COOKED.createStack();
            registerMob(new EntityGrasshopper(world), LightLevel.hostile, getSpawnBiomes(EntityGrasshopper.class), foodDrop);
        }

        if(jerConfig.JER_MOBS.enableHoneyPotAnt) {
            registerMob(new EntityHoneyPotAnt(world), LightLevel.hostile, getSpawnBiomes(EntityHoneyPotAnt.class), new LootDrop(EnumErebusMaterialsType.NECTAR.createStack(), 1, 8, 1f));
            adjustBugRenderHookUp(EntityHoneyPotAnt.class);
            registerRenderHook(EntityHoneyPotAnt.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableJumpingSpider) {
            registerMob(new EntityJumpingSpider(world), LightLevel.hostile, getSpawnBiomes(EntityJumpingSpider.class), new LootDrop(EnumErebusMaterialsType.POISON_GLAND.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableLavaWebSpider) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.FIRE_CHARGE, 1, 1, Conditional.affectedByLooting),
                    new LootDrop(Items.SPIDER_EYE, 0, 2, Conditional.affectedByLooting)
            };
            registerMob(new EntityLavaWebSpider(world), LightLevel.hostile, getSpawnBiomes(EntityLavaWebSpider.class), drops);
            registerRenderHook(EntityLavaWebSpider.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableLocust) {
            registerMob(new EntityLocust(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.SUBTERRANEAN_SAVANNAH), new LootDrop(EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableMagmaCrawler) {
            registerMob(new EntityMagmaCrawler(world), LightLevel.hostile, getSpawnBiomes(EntityMagmaCrawler.class), new LootDrop(EnumErebusMaterialsType.MAGMA_CRAWLER_EYE.createStack()));
        }

        if(jerConfig.JER_MOBS.enableMidgeSwarm) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.FLY_WING.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.COMPOUND_EYES.createStack(), 0, 3, 0.20f, Conditional.affectedByLooting)
            };
            registerMob(new EntityMidgeSwarm(world), LightLevel.hostile, getSpawnBiomes(EntityMidgeSwarm.class), drops);
        }

        if(jerConfig.JER_MOBS.enableMoneySpider) {
            List<String> spawnBiomes = new ArrayList<>();
            spawnBiomes.addAll(Arrays.asList(getSpawnBiomes(EntityLavaWebSpider.class)));
            spawnBiomes.addAll(Arrays.asList(getSpawnBiomes(EntityTarantula.class)));
            registerMob(new EntityMoneySpider(world), LightLevel.hostile, spawnBiomes.isEmpty() ? new String[] {"jer.any"} : spawnBiomes.toArray(new String[0]),
                    new LootDrop(Items.GOLD_INGOT, 0, 1, 0.10f, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableMosquito) {
            registerMob(new EntityMosquito(world), LightLevel.hostile, getSpawnBiomes(EntityMosquito.class), new LootDrop(ModItems.LIFE_BLOOD, 1, 5));
            registerRenderHook(EntityMosquito.class, ((renderInfo, e) -> {
                GlStateManager.translate(0,-0.5,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableMoth) {
            registerMob(new EntityMoth(world), LightLevel.hostile, getSpawnBiomes(EntityMoth.class), new LootDrop(Items.GLOWSTONE_DUST, 0.20f));
        }

        if(jerConfig.JER_MOBS.enablePondSkater) {
            registerMob(new EntityPondSkater(world), LightLevel.hostile, getSpawnBiomes(EntityPondSkater.class), new LootDrop(EnumErebusMaterialsType.HYDROFUGE.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enablePrayingMantis) {
            EntityPrayingMantis mantis = new EntityPrayingMantis(world);
            mantis.setAlpha(1.0f);
            registerMob(mantis, LightLevel.hostile, getSpawnBiomes(EntityPrayingMantis.class), new LootDrop(EnumErebusMaterialsType.CAMO_POWDER.createStack(), 0, 3, Conditional.affectedByLooting));
            adjustBugRenderHookDown(EntityPrayingMantis.class);
        }

        if(jerConfig.JER_MOBS.enablePunchroom) {
            registerMob(new EntityPunchroom(world), LightLevel.hostile, getSpawnBiomes(EntityPunchroom.class), new LootDrop(EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(), 1, 1, 0.20f, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableRhinoBeetle) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack(), 1, 2, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.RHINO_BEETLE_HORN.createStack(), 0, 1, 0.05f, Conditional.rareDrop)
            };
            registerMob(new EntityRhinoBeetle(world), LightLevel.hostile, getSpawnBiomes(EntityRhinoBeetle.class), drops);
            registerRenderHook(EntityRhinoBeetle.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                GlStateManager.translate(0,1.0,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableScorpion) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.POISON_GLAND.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.SCORPION_PINCER.createStack(), 0, 3, 0.0333f, Conditional.affectedByLooting, Conditional.rareDrop)
            };
            registerMob(new EntityScorpion(world), LightLevel.hostile, getSpawnBiomes(EntityScorpion.class), drops);
            adjustBugRenderHookDown(EntityScorpion.class);
        }

        if(jerConfig.JER_MOBS.enableScytodes) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(Items.STRING, 0, 3, Conditional.affectedByLooting),
                    new LootDrop(Items.SPIDER_EYE, 0, 2, Conditional.affectedByLooting)
            };
            registerMob(new EntityScytodes(world), LightLevel.hostile, getSpawnBiomes(EntityScytodes.class), drops);
            registerRenderHook(EntityScytodes.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableSolifuge) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.BIO_VELOCITY.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), 0, 1, 0.02f, Conditional.rareDrop)
            };
            registerMob(new EntitySolifuge(world), LightLevel.hostile, getSpawnBiomes(EntitySolifuge.class), drops);
            registerRenderHook(EntitySolifuge.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableStagBeetle) {
            List<LootDrop> drops = new ArrayList<>();
            drops.add(exoPlateDrop);
            LootDrop foodDrop = new LootDrop(ModItems.STAG_HEART_RAW, 0.1333f);
            foodDrop.smeltedItem = new ItemStack(ModItems.STAG_HEART_COOKED);
            drops.add(foodDrop);
            drops.add(new LootDrop(EnumErebusMaterialsType.STAG_BEETLE_MANDIBLES.createStack(), 0, 1, 0.0333f, Conditional.rareDrop));
            registerMob(new EntityStagBeetle(world), LightLevel.hostile, getSpawnBiomes(EntityStagBeetle.class), drops);
            registerRenderHook(EntityStagBeetle.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4, 1.4, 1.4);
                GlStateManager.translate(0, 1.0, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableTarantula) {
            List<LootDrop> drops = new ArrayList<>();
            LootDrop foodDrop = new LootDrop(EnumFoodType.TARANTULA_LEG_RAW.createStack(), 1, 1, Conditional.affectedByLooting);
            foodDrop.smeltedItem = EnumFoodType.TARANTULA_LEG_COOKED.createStack();
            drops.add(foodDrop);
            drops.add(new LootDrop(Items.SPIDER_EYE, 0, 1, Conditional.affectedByLooting));
            registerMob(new EntityTarantula(world), LightLevel.hostile, getSpawnBiomes(EntityTarantula.class), drops);
        }

        if(jerConfig.JER_MOBS.enableTarantulaBroodMother) {
            registerMob(new EntityTarantulaMiniboss(world), LightLevel.hostile, allBiomes, new LootDrop(new ItemStack(ModItems.SPIDER_T_SHIRT)));
            registerRenderHook(EntityTarantulaMiniboss.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4, 1.4, 1.4);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableTitanBeetle) {
            List<LootDrop> drops = new ArrayList<>();
            drops.add(exoPlateDrop);
            LootDrop foodDrop = new LootDrop(EnumFoodType.TITAN_CHOP_RAW.createStack());
            foodDrop.smeltedItem = EnumFoodType.TITAN_CHOP_COOKED.createStack();
            drops.add(foodDrop);
            registerMob(new EntityTitanBeetle(world), LightLevel.hostile, getSpawnBiomes(EntityTitanBeetle.class), drops);
            registerRenderHook(EntityTitanBeetle.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4, 1.4, 1.4);
                GlStateManager.translate(0, 1.0, 0);
                return renderInfo;
            }));
        }

        boolean isUmberGolemRegistered = false;
        if(jerConfig.JER_MOBS.enableUmberGolemMud) {
            EntityUmberGolemDungeonTypes umberGolem = new EntityUmberGolemDungeonTypes(world);
            umberGolem.setType((byte) 0);
            registerMob(umberGolem, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT),
                    new LootDrop(new ItemStack(ModItems.IDOLS, 1, umberGolem.getType())));
            isUmberGolemRegistered = true;
        }

        if(jerConfig.JER_MOBS.enableUmberGolemIron) {
            EntityUmberGolemDungeonTypes umberGolem = new EntityUmberGolemDungeonTypes(world);
            umberGolem.setType((byte) 1);
            registerMob(umberGolem, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT),
                    new LootDrop(new ItemStack(ModItems.IDOLS, 1, umberGolem.getType())));
            isUmberGolemRegistered = true;
        }

        if(jerConfig.JER_MOBS.enableUmberGolemGold) {
            EntityUmberGolemDungeonTypes umberGolem = new EntityUmberGolemDungeonTypes(world);
            umberGolem.setType((byte) 2);
            registerMob(umberGolem, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT),
                    new LootDrop(new ItemStack(ModItems.IDOLS, 1, umberGolem.getType())));
            isUmberGolemRegistered = true;
        }

        if(jerConfig.JER_MOBS.enableUmberGolemJade) {
            EntityUmberGolemDungeonTypes umberGolem = new EntityUmberGolemDungeonTypes(world);
            umberGolem.setType((byte) 3);
            registerMob(umberGolem, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(ModBiomes.VOLCANIC_DESERT),
                    new LootDrop(new ItemStack(ModItems.IDOLS, 1, umberGolem.getType())));
            isUmberGolemRegistered = true;
        }
        if(isUmberGolemRegistered) {
            registerRenderHook(EntityUmberGolemDungeonTypes.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.025, -0.4, 0);
                return renderInfo;
            }));
        }


        if(jerConfig.JER_MOBS.enableVelvetWorm) {
            registerMob(new EntityVelvetWorm(world), LightLevel.hostile, getSpawnBiomes(EntityVelvetWorm.class), new LootDrop(Items.SLIME_BALL, 1, 2, Conditional.affectedByLooting));
            registerRenderHook(EntityVelvetWorm.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.6,1.6,1.6);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableWasp) {
            registerMob(new EntityWasp(world), LightLevel.hostile, getSpawnBiomes(EntityWasp.class), new LootDrop(EnumErebusMaterialsType.WASP_STING.createStack(), 0, 3, Conditional.affectedByLooting));
        }

        if(jerConfig.JER_MOBS.enableWaspBoss) {
            EntityWasp waspBoss = new EntityWasp(world);
            waspBoss.setIsBoss((byte) 1);
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.WASP_STING.createStack(), 0, 3, Conditional.affectedByLooting),
                    new LootDrop(new ItemStack(ModItems.ANTI_VENOM_BOTTLE))
            };
            registerMob(waspBoss, LightLevel.hostile, getSpawnBiomes(EntityWasp.class), drops);
        }

        if(jerConfig.JER_MOBS.enableWoodlouse) {
            registerMob(new EntityWoodlouse(world), LightLevel.hostile, allBiomes, new LootDrop(EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 0, 3, Conditional.affectedByLooting));
            registerRenderHook(EntityWoodlouse.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableWorkerBee) {
            registerMob(new EntityWorkerBee(world), LightLevel.hostile, getSpawnBiomes(EntityWorkerBee.class), new LootDrop(EnumErebusMaterialsType.NECTAR.createStack(2)));
            adjustBugRenderHookUp(EntityWorkerBee.class);
        }

        if(jerConfig.JER_MOBS.enableZombieAnt) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.PLATE_ZOMBIE_ANT.createStack(), 0, 2, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.ANT_PHEROMONES.createStack(), 0.20f)
            };
            registerMob(new EntityZombieAnt(world), LightLevel.hostile, getSpawnBiomes(EntityZombieAnt.class), drops);
        }

        if(jerConfig.JER_MOBS.enableZombieAntSoldier) {
            LootDrop[] drops = new LootDrop[] {
                    new LootDrop(EnumErebusMaterialsType.PLATE_ZOMBIE_ANT.createStack(), 0, 2, Conditional.affectedByLooting),
                    new LootDrop(EnumErebusMaterialsType.ANT_PHEROMONES.createStack(), 0.10f),
                    new LootDrop(EnumErebusMaterialsType.TERPSISHROOM.createStack(), 0.10f)
            };
            registerMob(new EntityZombieAntSoldier(world), LightLevel.hostile, getSpawnBiomes(EntityZombieAntSoldier.class), drops);
        }
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.JER_PLANTS.enableCabbage) {
            registerPlant((Item & IPlantable) ModItems.CABBAGE_SEEDS,
                    new PlantDrop(new ItemStack(ModItems.CABBAGE_SEEDS), 0, 2),
                    new PlantDrop(EnumFoodType.CABBAGE.createStack(), 1, 1)
            );
        }

        if(jerConfig.JER_PLANTS.enableDarkFruit) {
            CustomPlantEntry darkFruit = new CustomPlantEntry(
                    EnumErebusMaterialsType.DARK_FRUIT_SEEDS.createStack(),
                    ModBlocks.DARK_FRUIT_VINE.getDefaultState(),
                    new PlantDrop(EnumErebusMaterialsType.DARK_FRUIT_SEEDS.createStack(), 0, 1),
                    new PlantDrop(EnumFoodType.DARK_FRUIT.createStack(), 0, 1)
            );
            darkFruit.setSoil(Blocks.AIR.getDefaultState());
            registerCustomPlant(darkFruit);
        }

        if(jerConfig.JER_PLANTS.enableHeartBerry) {
            CustomPlantEntry heartBerry = new CustomPlantEntry(
                    new ItemStack(ModBlocks.HEART_BERRY_BUSH),
                    ModBlocks.HEART_BERRY_BUSH.getDefaultState(),
                    new PlantDrop(new ItemStack(ModItems.HEART_BERRIES), 1, 1)
            );
            heartBerry.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(heartBerry);
        }

        if(jerConfig.JER_PLANTS.enableJadeBerry) {
            CustomPlantEntry jadeBerry = new CustomPlantEntry(
                    new ItemStack(ModBlocks.JADE_BERRY_BUSH),
                    ModBlocks.JADE_BERRY_BUSH.getDefaultState(),
                    new PlantDrop(EnumErebusMaterialsType.JADE_BERRIES.createStack(), 1, 1)
            );
            jadeBerry.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(jadeBerry);
        }

        if(jerConfig.JER_PLANTS.enableMandrake) {
            registerPlant((Item & IPlantable) ModItems.MANDRAKE_ROOT,
                    new PlantDrop(new ItemStack(ModItems.MANDRAKE_ROOT), 1, 3)
            );
        }

        if(jerConfig.JER_PLANTS.enableSwampBerry) {
            CustomPlantEntry swampBerry = new CustomPlantEntry(
                    new ItemStack(ModBlocks.SWAMP_BERRY_BUSH),
                    ModBlocks.SWAMP_BERRY_BUSH.getDefaultState(),
                    new PlantDrop(EnumFoodType.SWAMP_BERRIES.createStack(), 1, 1)
            );
            swampBerry.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(swampBerry);
        }

        if(jerConfig.JER_PLANTS.enableTurnip) {
            registerPlant((Item & IPlantable) ModItems.TURNIP,
                    new PlantDrop(new ItemStack(ModItems.TURNIP), 1, 3)
            );
        }
    }

    public void registerRenderOverrides() {
        RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, JERRenderGlowWorm::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaCrawler.class, JERRenderMagmaCrawler::new);
    }

    private void adjustBugRenderHookUp(Class<? extends EntityLivingBase> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(0, 0.4, 0);
            return renderInfo;
        }));
    }

    private void adjustBugRenderHookDown(Class<? extends EntityLivingBase> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(0, -0.4, 0);
            return renderInfo;
        }));
    }

    private String[] getSpawnBiomes(Class<? extends EntityLiving> entityClass) {
        THashSet<Biome> spawnBiomes = new THashSet<>();
        mobSpawns.forEach((biome, mobList) -> {
            if(mobList.contains(entityClass))
                spawnBiomes.add(biome);
        });
        return !spawnBiomes.isEmpty() ? BiomeHelper.getBiomeNamesForBiomes(spawnBiomes.toArray(new Biome[0])) : new String[] {"Check Spawns"};
    }

    private void registerErebusDungeon(String name, WeightedLootList lootList, int minRolls, int maxRolls) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name, lootList, minRolls, maxRolls);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final LootTable lootTable;

        public JERDungeonStrings(String name, WeightedLootList lootList, int minRolls, int maxRolls) {
            this.category = String.format("%s:%s", ModIds.EREBUS.MOD_ID, name);
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.EREBUS.MOD_ID, name);
            this.lootTable = getLootTable(lootList, minRolls, maxRolls);
        }

        private LootTable getLootTable(WeightedLootList lootList, int minRolls, int maxRolls) {
            List<LootEntry> lootEntries = new ArrayList<>();

            lootList.forEach((lootItemStack -> {
                try {
                    Item item = (Item) getFieldValue(lootItemStack, "item");

                    if(item != null) {
                        List<LootFunction> lootFunctions = new ArrayList<>();

                        String name = item.getRegistryName().toString();
                        short minDamage = (short) getFieldValue(lootItemStack, "minDamage");
                        short maxDamage = (short) getFieldValue(lootItemStack, "maxDamage");
                        byte minAmount = (byte) getFieldValue(lootItemStack, "minAmount");
                        byte maxAmount = (byte) getFieldValue(lootItemStack, "maxAmount");
                        short weight = (short) getFieldValue(lootItemStack, "weight");

                        lootFunctions.add(new SetCount(new LootCondition[0], new RandomValueRange(minAmount, maxAmount)));
                        if((minDamage > 0 && maxDamage > 0)) {
                            lootFunctions.add(new SetMetadata(new LootCondition[0], new RandomValueRange(minDamage, maxDamage)));
                        }
                        lootEntries.add(new LootEntryItem(item, weight, 0, lootFunctions.toArray(new LootFunction[0]), new LootCondition[0], name));
                    }
                } catch (Exception e) {
                    LogHelper.warn("Failed to get loot item for " + this.category);
                }
            }));

            return new LootTable(new LootPool[] {new LootPool(
                    lootEntries.toArray(new LootEntry[0]),
                    new LootCondition[0],
                    new RandomValueRange((float) minRolls, (float) maxRolls),
                    new RandomValueRange(0, 0),
                    this.category
            )});
        }

        private Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        }
    }

    private void populateMobSpawns() {
        mobSpawns = new THashMap<>();
        mobSpawns.put(ModBiomes.ELYSIAN_FIELDS, new THashSet<>(Sets.newHashSet(
                EntityWorkerBee.class,
                EntityFly.class,
                EntityDragonfly.class,
                EntityBeetle.class,
                EntityBeetleLarva.class,
                EntityBotFly.class,
                EntityGrasshopper.class,
                EntityMoth.class,
                EntityCropWeevil.class,
                EntityChameleonTick.class,
                EntityVelvetWorm.class,
                EntityCicada.class,
                EntityGlowWorm.class,
                EntityTitanBeetle.class
        )));

        mobSpawns.put(ModBiomes.FUNGAL_FOREST, new THashSet<>(Sets.newHashSet(
                EntityFungalWeevil.class,
                EntityCrushroom.class,
                EntityBlackAnt.class,
                EntityPunchroom.class,
                EntityStagBeetle.class,
                EntityZombieAnt.class,
                EntityZombieAntSoldier.class
        )));

        mobSpawns.put(ModBiomes.PETRIFIED_FOREST, new THashSet<>(Sets.newHashSet(
                EntityFly.class,
                EntityBotFly.class,
                EntityChameleonTick.class,
                EntityBlackWidow.class,
                EntityGlowWorm.class,
                EntityMoth.class,
                EntityScytodes.class,
                EntityJumpingSpider.class
        )));

        mobSpawns.put(ModBiomes.SUBMERGED_SWAMP, new THashSet<>(Sets.newHashSet(
                EntityDragonfly.class,
                EntityCentipede.class,
                EntityBeetleLarva.class,
                EntityBeetle.class,
                EntityJumpingSpider.class,
                EntityPondSkater.class,
                EntityBogMaw.class,
                EntityMosquito.class
        )));

        mobSpawns.put(ModBiomes.SUBTERRANEAN_SAVANNAH, new THashSet<>(Sets.newHashSet(
                EntityMosquito.class,
                EntityFly.class,
                EntityWasp.class,
                EntityBeetleLarva.class,
                EntityBeetle.class,
                EntityGrasshopper.class,
                EntityChameleonTick.class,
                EntityScytodes.class,
                EntityTarantula.class,
                EntityScorpion.class,
                EntityGlowWorm.class
        )));

        mobSpawns.put(ModBiomes.ULTERIOR_OUTBACK, new THashSet<>(Sets.newHashSet(
                EntityRhinoBeetle.class,
                EntityFly.class,
                EntityCentipede.class,
                EntityBlackWidow.class,
                EntityBotFly.class,
                EntityBeetleLarva.class,
                EntityHoneyPotAnt.class,
                EntityMidgeSwarm.class,
                EntityScytodes.class,
                EntitySolifuge.class,
                EntityTarantula.class,
                EntityChameleonTick.class,
                EntityScorpion.class
        )));

        mobSpawns.put(ModBiomes.UNDERGROUND_JUNGLE, new THashSet<>(Sets.newHashSet(
                EntityMosquito.class,
                EntityFly.class,
                EntityWasp.class,
                EntityCentipede.class,
                EntityBotFly.class,
                EntityBeetleLarva.class,
                EntityBeetle.class,
                EntityBombardierBeetle.class,
                EntityBombardierBeetleLarva.class,
                EntityJumpingSpider.class,
                EntityPrayingMantis.class,
                EntityScytodes.class,
                EntityTarantula.class,
                EntityChameleonTick.class,
                EntityVelvetWorm.class
        )));

        mobSpawns.put(ModBiomes.VOLCANIC_DESERT, new THashSet<>(Sets.newHashSet(
                EntityFly.class,
                EntityBotFly.class,
                EntityFireAnt.class,
                EntityFireAntSoldier.class,
                EntityBlackWidow.class,
                EntityAntlion.class,
                EntitySolifuge.class,
                EntityChameleonTick.class,
                EntityScorpion.class,
                EntityLavaWebSpider.class
        )));
    }
}
