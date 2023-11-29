package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.bobmowzie.mowziesmobs.server.block.BlockHandler;
import com.bobmowzie.mowziesmobs.server.config.ConfigHandler;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarako;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarakoana;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.MaskType;
import com.bobmowzie.mowziesmobs.server.entity.foliaath.EntityFoliaath;
import com.bobmowzie.mowziesmobs.server.entity.frostmaw.EntityFrostmaw;
import com.bobmowzie.mowziesmobs.server.entity.grottol.EntityGrottol;
import com.bobmowzie.mowziesmobs.server.entity.lantern.EntityLantern;
import com.bobmowzie.mowziesmobs.server.entity.naga.EntityNaga;
import com.bobmowzie.mowziesmobs.server.entity.wroughtnaut.EntityWroughtnaut;
import com.bobmowzie.mowziesmobs.server.item.ItemHandler;
import com.bobmowzie.mowziesmobs.server.loot.LootTableHandler;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigMowziesMobs;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.BasicTrade;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JERMowziesMobs extends JERBase implements IJERIntegration {
    JEMConfigMowziesMobs.JER jerConfig = JEMConfig.MOWZIES_MOBS.JUST_ENOUGH_RESOURCES;

    public JERMowziesMobs(boolean enableJERMobs, boolean enableJERVillagers) {
        if(enableJERMobs) registerModEntities();
        if(enableJERVillagers) registerModVillagers();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.JER_MOBS.enableBarako) {
            registerMob(new EntityBarako(world), LightLevel.any, getSpawnBiomes(ConfigHandler.MOBS.BARAKO.generationData.biomeData), LootTableHandler.BARAKO);
            registerRenderHook(EntityBarako.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.3,0);
                GlStateManager.scale(0.9,0.9,0.9);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableBarakoBliss) {
            EntityBarakoana bliss = new EntityBarakoana(world);
            bliss.setMask(MaskType.BLISS);
            LightLevel spawnLight = ConfigHandler.MOBS.BARAKOA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(bliss, spawnLight, getSpawnBiomes(ConfigHandler.MOBS.BARAKOA.spawnData.biomeData), LootTableHandler.BARAKOA_FEAR);
        }

        if(jerConfig.JER_MOBS.enableBarakoFear) {
            EntityBarakoana fear = new EntityBarakoana(world);
            fear.setMask(MaskType.FEAR);
            LightLevel spawnLight = ConfigHandler.MOBS.BARAKOA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(fear, spawnLight, getSpawnBiomes(ConfigHandler.MOBS.BARAKOA.spawnData.biomeData), LootTableHandler.BARAKOA_FEAR);
        }

        if(jerConfig.JER_MOBS.enableBarakoFury) {
            EntityBarakoana fury = new EntityBarakoana(world);
            fury.setMask(MaskType.FURY);
            LightLevel spawnLight = ConfigHandler.MOBS.BARAKOA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(fury, spawnLight, getSpawnBiomes(ConfigHandler.MOBS.BARAKOA.spawnData.biomeData), LootTableHandler.BARAKOA_FEAR);
        }

        if(jerConfig.JER_MOBS.enableBarakoMisery) {
            EntityBarakoana misery = new EntityBarakoana(world);
            misery.setMask(MaskType.MISERY);
            LightLevel spawnLight = ConfigHandler.MOBS.BARAKOA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(misery, spawnLight, getSpawnBiomes(ConfigHandler.MOBS.BARAKOA.spawnData.biomeData), LootTableHandler.BARAKOA_FEAR);
        }

        if(jerConfig.JER_MOBS.enableBarakoRage) {
            EntityBarakoana rage = new EntityBarakoana(world);
            rage.setMask(MaskType.RAGE);
            LightLevel spawnLight = ConfigHandler.MOBS.BARAKOA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(rage, spawnLight, getSpawnBiomes(ConfigHandler.MOBS.BARAKOA.spawnData.biomeData), LootTableHandler.BARAKOA_FEAR);
        }

        if(jerConfig.JER_MOBS.enableFerrousWroughtnaut) {
            registerMob(new EntityWroughtnaut(world), LightLevel.any, getSpawnBiomes(ConfigHandler.MOBS.FERROUS_WROUGHTNAUT.generationData.biomeData), LootTableHandler.FERROUS_WROUGHTNAUT);
            registerRenderHook(EntityWroughtnaut.class, ((renderInfo, e) -> {
                GlStateManager.scale(0.9,0.9,0.9);
                GlStateManager.translate(-0.1,-0.8,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableFoliaath) {
            LightLevel spawnLight = ConfigHandler.MOBS.FOLIAATH.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(new EntityFoliaath(world), spawnLight, getSpawnBiomes(ConfigHandler.MOBS.FOLIAATH.spawnData.biomeData), LootTableHandler.FOLIAATH);
            registerRenderHook(EntityFoliaath.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.5,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableFrostmaw) {
            registerMob(new EntityFrostmaw(world), LightLevel.any, getSpawnBiomes(ConfigHandler.MOBS.FROSTMAW.generationData.biomeData), LootTableHandler.FROSTMAW);
            registerRenderHook(EntityFrostmaw.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.4,-1.25,0);
                GlStateManager.scale(0.9,0.9,0.9);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableGrottol) {
            LightLevel spawnLight = ConfigHandler.MOBS.GROTTOL.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(new EntityGrottol(world), spawnLight, getSpawnBiomes(ConfigHandler.MOBS.GROTTOL.spawnData.biomeData), LootTableHandler.GROTTOL);
            registerRenderHook(EntityGrottol.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableLantern) {
            LightLevel spawnLight = ConfigHandler.MOBS.LANTERN.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(new EntityLantern(world), spawnLight, getSpawnBiomes(ConfigHandler.MOBS.LANTERN.spawnData.biomeData), LootTableHandler.LANTERN);
            registerRenderHook(EntityLantern.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableNaga) {
            LightLevel spawnLight = ConfigHandler.MOBS.NAGA.spawnData.needsDarkness ? LightLevel.hostile : LightLevel.any;
            registerMob(new EntityNaga(world), spawnLight, getSpawnBiomes(ConfigHandler.MOBS.NAGA.spawnData.biomeData), LootTableHandler.NAGA);
            registerRenderHook(EntityNaga.class, ((renderInfo, e) -> {
                GlStateManager.translate(0,0.5,0);
                GlStateManager.scale(1.5,1.5,1.5);
                return renderInfo;
            }));
        }
    }

    @Override
    public void registerModVillagers() {
        if(jerConfig.JER_VILLAGERS.enableBarako) {
            List<EntityVillager.ITradeList> trades = new ArrayList<>();
            trades.add(new BasicTrade(new ItemStack(ItemHandler.GRANT_SUNS_BLESSING, 1), new ItemStack(Blocks.GOLD_BLOCK, 7)));
            List<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>(1);
            allTrades.add(trades);
            EntityBarako barako = new EntityBarako(world);
            CustomVillagerRegistry.getInstance().addVillagerEntry(new CustomVillagerEntry(barako.getName(), 0, allTrades) {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return barako;
                }

                @Override
                public String getDisplayName() {
                    return barako.getDisplayName().getFormattedText();
                }
            });
        }

        if(jerConfig.JER_VILLAGERS.enableBarakoana) {
            List<EntityVillager.ITradeList> trades = new ArrayList<>();
            trades.add(new BasicTrade(new ItemStack(ItemHandler.BLOWGUN, 1), new ItemStack(Items.GOLD_INGOT, 2)));
            trades.add(new BasicTrade(new ItemStack(ItemHandler.DART, 8), new ItemStack(Items.DYE, 10, EnumDyeColor.BROWN.getDyeDamage())));
            trades.add(new BasicTrade(new ItemStack(ItemHandler.SPEAR, 1), new ItemStack(Items.GOLD_INGOT, 3)));
            trades.add(new BasicTrade(new ItemStack(Item.getItemFromBlock(BlockHandler.PAINTED_ACACIA), 2), new ItemStack(Items.GOLD_NUGGET, 5)));
            trades.add(new BasicTrade(new ItemStack(Item.getItemFromBlock(BlockHandler.PAINTED_ACACIA), 1), new ItemStack(Items.DYE, 16, EnumDyeColor.BROWN.getDyeDamage())));
            trades.add(new BasicTrade(new ItemStack(Items.COOKED_CHICKEN, 2), new ItemStack(Items.DYE, 10, EnumDyeColor.BROWN.getDyeDamage())));
            trades.add(new BasicTrade(new ItemStack(Items.COOKED_CHICKEN, 1), new ItemStack(Items.GOLD_NUGGET, 8)));
            trades.add(new BasicTrade(new ItemStack(Items.COOKED_PORKCHOP, 2), new ItemStack(Items.DYE, 14, EnumDyeColor.BROWN.getDyeDamage())));
            trades.add(new BasicTrade(new ItemStack(Items.COOKED_PORKCHOP, 1), new ItemStack(Items.GOLD_NUGGET, 9)));
            trades.add(new BasicTrade(new ItemStack(Items.GOLD_NUGGET, 5), new ItemStack(Items.MELON, 3)));
            trades.add(new BasicTrade(new ItemStack(Items.GOLD_NUGGET, 3), new ItemStack(Items.CHICKEN, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.GOLD_INGOT, 2), new ItemStack(Items.IRON_SWORD, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.IRON_HELMET, 1)));
            List<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>(1);
            allTrades.add(trades);

            EntityBarakoana trader = new EntityBarakoana(world);
            trader.setMask(MaskType.FURY);
            CustomVillagerRegistry.getInstance().addVillagerEntry(new CustomVillagerEntry(trader.toString(), 0, allTrades) {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return trader;
                }

                @Override
                public String getDisplayName() {
                    return trader.getDisplayName().getFormattedText();
                }
            });
        }
    }

    private String[] getSpawnBiomes(ConfigHandler.BiomeData biomeData) {
        List<String> spawnBiomes = new ArrayList<>();
        spawnBiomes.addAll(Arrays.asList((BiomeHelper.getBiomeNamesForTypes(biomeData.biomeTypes))));
        spawnBiomes.addAll(Arrays.asList(BiomeHelper.getBiomeNamesForBiomes(biomeData.biomeWhitelist)));
        spawnBiomes.removeAll(Arrays.asList(BiomeHelper.getBiomeNamesForBiomes(biomeData.biomeBlacklist)));
        return spawnBiomes.size() > 0 ? spawnBiomes.toArray(new String[0]) : new String[] {"jer.any"};
    }
}
