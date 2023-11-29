package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigAtum;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import com.teammetallurgy.atum.blocks.vegetation.BlockAnputsFingers;
import com.teammetallurgy.atum.entity.animal.*;
import com.teammetallurgy.atum.entity.bandit.EntityAssassin;
import com.teammetallurgy.atum.entity.bandit.EntityBarbarian;
import com.teammetallurgy.atum.entity.bandit.EntityBrigand;
import com.teammetallurgy.atum.entity.bandit.EntityNomad;
import com.teammetallurgy.atum.entity.efreet.EntitySunspeaker;
import com.teammetallurgy.atum.entity.stone.EntityStoneguard;
import com.teammetallurgy.atum.entity.stone.EntityStonewarden;
import com.teammetallurgy.atum.entity.undead.EntityBonestorm;
import com.teammetallurgy.atum.entity.undead.EntityForsaken;
import com.teammetallurgy.atum.entity.undead.EntityMummy;
import com.teammetallurgy.atum.entity.undead.EntityWraith;
import com.teammetallurgy.atum.init.AtumBiomes;
import com.teammetallurgy.atum.init.AtumBlocks;
import com.teammetallurgy.atum.init.AtumItems;
import com.teammetallurgy.atum.init.AtumLootTables;
import com.teammetallurgy.atum.items.ItemLoot;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JERAtum extends JERBase implements IJERIntegration {
    private static JERAtum instance;
    JEMConfigAtum.JER jerConfig = JEMConfig.ATUM.JUST_ENOUGH_RESOURCES;

    private JERAtum() {}

    public JERAtum(boolean enableJERDungeons, boolean enableJERMobs, boolean enableJERPlants, boolean enableJERVillagers) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        if(enableJERPlants) registerModPlants();
        if(enableJERVillagers) registerModVillagers();
    }

    public static JERAtum getInstance() {
        return instance != null ? instance : (instance = new JERAtum());
    }

    public void registerJEIInfoPages(IModRegistry registry) {
        List<ItemStack> dirtyItems = new ArrayList<>();
        dirtyItems.add(new ItemStack(AtumItems.DIRTY_COIN));
        List<ItemStack> cleanedItems = new ArrayList<>();

        for(ItemLoot.Quality quality : ItemLoot.Quality.values()) {
            for(ItemLoot.Type type : ItemLoot.Type.values()) {
                Item loot = Item.getByNameOrId(String.format("%s:loot_%s_%s", ModIds.ATUM.MOD_ID, quality, type));
                if(loot != null) {
                    if(quality.equals(ItemLoot.Quality.DIRTY)) {
                        dirtyItems.add(new ItemStack(loot));
                    } else {
                        cleanedItems.add(new ItemStack(loot));
                    }
                }
            }
        }

        registry.addIngredientInfo(dirtyItems, VanillaTypes.ITEM, "jei." + JustEnoughMagiculture.MOD_ALIAS + ":atum_dirty_loot.info");
        registry.addIngredientInfo(new ItemStack(AtumItems.GOLD_COIN), VanillaTypes.ITEM, "jei." + JustEnoughMagiculture.MOD_ALIAS + ":atum_gold_coin.info");
        if(cleanedItems.size() > 0) {
            registry.addIngredientInfo(cleanedItems, VanillaTypes.ITEM, "jei." + JustEnoughMagiculture.MOD_ALIAS + ":atum_clean_loot.info");
        }
    }

    @Override
    public void registerModDungeons() {
        registerAtumDungeon("relic_ore", AtumLootTables.RELIC);
        registerAtumDungeon("crate", AtumLootTables.CRATE);
        registerAtumDungeon("crate_bonus", AtumLootTables.CRATE_BONUS);
        registerAtumDungeon("girafi_tomb", AtumLootTables.GIRAFI_TOMB);
        registerAtumDungeon("lighthouse", AtumLootTables.LIGHTHOUSE);
        registerAtumDungeon("pharaoh", AtumLootTables.PHARAOH);
        registerAtumDungeon("pyramid_chest", AtumLootTables.PYRAMID_CHEST);
        registerAtumDungeon("sarcophagus_artifact", AtumLootTables.SARCOPHAGUS_ARTIFACT);
        registerAtumDungeon("tomb", AtumLootTables.TOMB_CHEST);
    }

    @Override
    public void registerModEntities() {
        registerAnimals();
        registerBandits();
        registerStoneConstructs();
        registerUndead();
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.JER_PLANTS.enableAnputsFingers) {
            try {
                Field agePropField = BlockAnputsFingers.class.getDeclaredField("ANPUTS_FINGERS_AGE");
                agePropField.setAccessible(true);
                PropertyInteger ageProp = (PropertyInteger) agePropField.get(BlockAnputsFingers.class);
                CustomPlantEntry plantEntry = new CustomPlantEntry(
                        new ItemStack(AtumItems.ANPUTS_FINGERS_SPORES),
                        (IPlantable) AtumItems.ANPUTS_FINGERS_SPORES,
                        ageProp,
                        new PlantDrop(new ItemStack(AtumItems.ANPUTS_FINGERS_SPORES), 1, 3)
                );
                plantEntry.setSoil(AtumBlocks.FERTILE_SOIL_TILLED.getDefaultState());
                registerCustomPlant(plantEntry);
            } catch(Exception e) {
                LogHelper.warn("Failed to register Anput's Fingers Spores plant entry.");
            }
        }

        if(jerConfig.JER_PLANTS.enableDate) {
            Item saplingItem = Item.getByNameOrId(ModIds.ATUM.MOD_ID + ":palm_sapling");
            if(saplingItem != null) {
                CustomPlantEntry plantEntry = new CustomPlantEntry(new ItemStack(saplingItem),
                        AtumBlocks.DATE_BLOCK.getDefaultState(),
                        new PlantDrop(new ItemStack(AtumItems.DATE), 1, 5)
                );
                plantEntry.setSoil(Blocks.AIR.getDefaultState());
                registerCustomPlant(plantEntry);
            }
        }

        if(jerConfig.JER_PLANTS.enableEmmer) {
            registerPlant((Item & IPlantable) AtumItems.EMMER_SEEDS,
                    AtumBlocks.FERTILE_SOIL_TILLED.getDefaultState(),
                    new PlantDrop(new ItemStack(AtumItems.EMMER_SEEDS), 0, 2),
                    new PlantDrop(new ItemStack(AtumItems.EMMER), 1, 1)
            );
        }

        if(jerConfig.JER_PLANTS.enableFlax) {
            registerPlant((Item & IPlantable) AtumItems.FLAX_SEEDS,
                    AtumBlocks.FERTILE_SOIL_TILLED.getDefaultState(),
                    new PlantDrop(new ItemStack(AtumItems.FLAX_SEEDS), 0, 2),
                    new PlantDrop(new ItemStack(AtumItems.FLAX), 1, 1)
            );
        }

        if(jerConfig.JER_PLANTS.enableOasisGrass) {
            PlantEntry plantEntry = new PlantEntry(new ItemStack(AtumBlocks.OASIS_GRASS, 1, 0),
                    new PlantDrop(new ItemStack(AtumItems.FLAX_SEEDS), 0.05625f),
                    new PlantDrop(new ItemStack(AtumItems.EMMER_SEEDS), 0.05625f),
                    new PlantDrop(new ItemStack(Items.MELON_SEEDS), 0.0125f)
            );
            plantEntry.setSoil(AtumBlocks.SAND.getDefaultState());
            registerCustomPlant(plantEntry);
        }
    }

    @Override
    public void registerModVillagers() {
        if(jerConfig.JER_VILLAGERS.enableSunspeaker) {

            try {
                EntitySunspeaker sunspeaker = new EntitySunspeaker(world);

                Field tradesField = EntitySunspeaker.class.getDeclaredField("TRADES");
                tradesField.setAccessible(true);
                List<EntityVillager.ITradeList[]> trades = (List<EntityVillager.ITradeList[]>) tradesField.get(EntitySunspeaker.class);

                List<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>();

                for(EntityVillager.ITradeList[] tradeLists : trades) {
                    List<EntityVillager.ITradeList> tierTrades = new ArrayList<>();
                    for(EntityVillager.ITradeList tradeList : tradeLists) {
                        tierTrades.add(tradeList);
                    }
                    allTrades.add(tierTrades);
                }

                CustomVillagerRegistry.getInstance().addVillagerEntry(new CustomVillagerEntry(sunspeaker.getName().toLowerCase(), 0, allTrades) {
                    @Override
                    public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                        return sunspeaker;
                    }

                    @Override
                    public String getDisplayName() {
                        return sunspeaker.getDisplayName().getFormattedText();
                    }
                });
            } catch (Exception e) {
                LogHelper.warn("Failed to register Sunspeaker villager.");
                e.printStackTrace();
            }
        }
    }

    private void registerAnimals() {
        if(jerConfig.JER_MOBS.enableCamel) {
            String[] spawnBiomes = BiomeHelper.getBiomeNamesForBiomes(AtumBiomes.DEAD_OASIS, AtumBiomes.OASIS, AtumBiomes.SAND_DUNES, AtumBiomes.SAND_PLAINS);
            registerMob(new EntityCamel(world), LightLevel.any, spawnBiomes, AtumLootTables.CAMEL);
        }

        if(jerConfig.JER_MOBS.enableDesertRabbit) {
            registerMob(new EntityDesertRabbit(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), LootTableList.ENTITIES_RABBIT);
        }

        if(jerConfig.JER_MOBS.enableDesertWolf) {
            String[] spawnBiomes = BiomeHelper.getBiomeNamesForBiomes(AtumBiomes.LIMESTONE_CRAGS, AtumBiomes.LIMESTONE_MOUNTAINS, AtumBiomes.SAND_HILLS);
            registerMob(new EntityDesertWolf(world), LightLevel.any, spawnBiomes, AtumLootTables.DESERT_WOLF);
        }

        if(jerConfig.JER_MOBS.enableDesertWolfAlpha) {
            try {
                //Increase scale to match alpha
                EntityDesertWolf alphaWolf = new EntityDesertWolf(world);
                Method setVariantMethod = alphaWolf.getClass().getDeclaredMethod("setVariant", int.class);
                setVariantMethod.setAccessible(true);
                setVariantMethod.invoke(alphaWolf, 1);

                String[] spawnBiomes = BiomeHelper.getBiomeNamesForBiomes(AtumBiomes.LIMESTONE_CRAGS, AtumBiomes.LIMESTONE_MOUNTAINS, AtumBiomes.SAND_HILLS);
                registerMob(alphaWolf, LightLevel.any, spawnBiomes, AtumLootTables.DESERT_WOLF_ALPHA);
            } catch(Exception e) {
                LogHelper.warn("Failed to register Alpha Desert Wolf.");
                e.printStackTrace();
            }
        }

        if(jerConfig.JER_MOBS.enableScarab) {
            registerMob(new EntityScarab(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.CAMEL);
        }

        if(jerConfig.JER_MOBS.enableTarantula) {
            registerMob(new EntityTarantula(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.TARANTULA);
        }
    }

    private void registerBandits() {
        if(jerConfig.JER_MOBS.enableAssassin) {
            registerMob(new EntityAssassin(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.ASSASSIN);
            adjustHumanoidRenderHook(EntityAssassin.class);
        }

        if(jerConfig.JER_MOBS.enableBarbarian) {
            registerMob(new EntityBarbarian(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.BARBARIAN);
            adjustHumanoidRenderHook(EntityBarbarian.class);
        }

        if(jerConfig.JER_MOBS.enableBrigand) {
            registerMob(new EntityBrigand(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.BRIGAND);
            adjustHumanoidRenderHook(EntityBrigand.class);
        }

        if(jerConfig.JER_MOBS.enableNomad) {
            registerMob(new EntityNomad(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.NOMAD);
            registerRenderHook(EntityNomad.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.1,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableSunspeaker) {
            registerMob(new EntitySunspeaker(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(AtumBiomes.LIMESTONE_MOUNTAINS), AtumLootTables.SUNSPEAKER);
            adjustHumanoidRenderHook(EntitySunspeaker.class);
        }
    }

    private void registerStoneConstructs() {
        if(jerConfig.JER_MOBS.enableStoneguard) {
            registerMob(new EntityStoneguard(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.STONEGUARD);
            adjustHumanoidRenderHook(EntityStoneguard.class);
        }

        if(jerConfig.JER_MOBS.enableStonewarden) {
            registerMob(new EntityStonewarden(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.STONEWARDEN);
            registerRenderHook(EntityStonewarden.class, ((renderInfo, e) -> {
                GlStateManager.translate(0,-0.6,0);
                return renderInfo;
            }));
        }
    }

    private void registerUndead() {
        if(jerConfig.JER_MOBS.enableBonestorm) {
            registerMob(new EntityBonestorm(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.BONESTORM);
            adjustHumanoidRenderHook(EntityBonestorm.class);
        }

        if(jerConfig.JER_MOBS.enableForsaken) {
            registerMob(new EntityForsaken(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.FORSAKEN);
            adjustHumanoidRenderHook(EntityForsaken.class);
        }

        if(jerConfig.JER_MOBS.enableMummy) {
            registerMob(new EntityMummy(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.MUMMY);
            adjustHumanoidRenderHook(EntityMummy.class);
        }

        if(jerConfig.JER_MOBS.enableWraith) {
            registerMob(new EntityWraith(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(AtumBiomes.BiomeTags.ATUM), AtumLootTables.WRAITH);
            adjustHumanoidRenderHook(EntityWraith.class);
        }
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.45,0);
            return renderInfo;
        }));
    }

    private void registerAtumDungeon(String name, ResourceLocation lootTable) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;

        public JERDungeonStrings(String name) {
            this.category = String.format("%s:%s", ModIds.ATUM.MOD_ID, name);
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.ATUM.MOD_ID, name);
        }
    }
}
