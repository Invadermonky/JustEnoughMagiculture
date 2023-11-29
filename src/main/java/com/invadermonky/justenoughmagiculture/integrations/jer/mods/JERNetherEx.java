package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigNetherEx;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.BasicTrade;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.*;
import jeresources.api.conditionals.LightLevel;
import logictechcorp.libraryex.utility.FileHelper;
import logictechcorp.netherex.NetherEx;
import logictechcorp.netherex.NetherExConfig;
import logictechcorp.netherex.entity.boss.EntityGhastQueen;
import logictechcorp.netherex.entity.monster.*;
import logictechcorp.netherex.entity.neutral.EntityGoldGolem;
import logictechcorp.netherex.entity.neutral.EntityMogus;
import logictechcorp.netherex.entity.neutral.EntitySalamander;
import logictechcorp.netherex.entity.passive.EntityPigtificate;
import logictechcorp.netherex.entity.passive.EntityPigtificateLeader;
import logictechcorp.netherex.init.*;
import logictechcorp.netherex.village.PigtificateProfession;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JERNetherEx extends JERBase implements IJERIntegration {
    private final JEMConfigNetherEx.JER jerConfig = JEMConfig.NETHEREX.JUST_ENOUGH_RESOURCES;

    public JERNetherEx(boolean enableJERDungeons, boolean enableJERMobs, boolean enableJERVillagers) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        if(enableJERVillagers) registerModVillagers();
    }

    @Override
    public void registerModDungeons() {
        registerDungeonLoot(
                ModIds.NETHEREX.MOD_ID + ":chest/ghast_queen_shrine",
                StringHelper.getDungeonTranslationKey(ModIds.NETHEREX.MOD_ID,"ghast_queen_shrine"),
                new ResourceLocation(ModIds.NETHEREX.MOD_ID, "chest/temple_rare"));

        registerDungeonLoot(
                ModIds.NETHEREX.MOD_ID + ":chest/base_village",
                StringHelper.getDungeonTranslationKey(ModIds.NETHEREX.MOD_ID, "base_village"),
                new ResourceLocation(ModIds.NETHEREX.MOD_ID, "chest/base_village"));
    }

    @Override
    public void registerModEntities() {
        registerHostile();
        registerNeutral();
        registerPassive();
    }

    @Override
    public void registerModVillagers() {
        CustomVillagerRegistry registry = CustomVillagerRegistry.getInstance();

        if(jerConfig.JER_VILLAGERS.enablePigtificateArmorsmith) registry.addVillagerEntry(getPigtificateArmorsmith());
        if(jerConfig.JER_VILLAGERS.enablePigtificateBrewer) registry.addVillagerEntry(getPigtificateBrewer());
        if(jerConfig.JER_VILLAGERS.enablePigtificateChief) registry.addVillagerEntry(getPigtificateChief());
        if(jerConfig.JER_VILLAGERS.enablePigtificateEnchanter) registry.addVillagerEntry(getPigtificateEnchanter());
        if(jerConfig.JER_VILLAGERS.enablePigtificateGatherer) registry.addVillagerEntry(getPigtificateGatherer());
        if(jerConfig.JER_VILLAGERS.enablePigtificateHunter) registry.addVillagerEntry(getPigtificateHunter());
        if(jerConfig.JER_VILLAGERS.enablePigtificateNincompoop) registry.addVillagerEntry(getPigtificateNincompoop());
        if(jerConfig.JER_VILLAGERS.enablePigtificateScavenger) registry.addVillagerEntry(getPigtificateScavenger());
        if(jerConfig.JER_VILLAGERS.enablePigtificateToolsmith) registry.addVillagerEntry(getPigtificateToolsmith());
    }

    private void registerHostile() {
        //Boss
        if(jerConfig.JER_MOBS.enableGhastQueen) {
            EntityGhastQueen ghastQueen = new EntityGhastQueen(world);
            registerMob(ghastQueen, LightLevel.hostile, new String[]{"None"}, NetherExLootTables.GHAST_QUEEN);
            registerRenderHook(ghastQueen.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.5,0.5,0.5);
                GlStateManager.translate(-0.05, -1.5, 0);
                return renderInfo;
            }));
        }

        //Mobs
        if(jerConfig.JER_MOBS.enableBrute) {
            registerMob(new EntityBrute(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.ARCTIC_ABYSS), NetherExLootTables.BRUTE);
            adjustHumanoidRenderHook(EntityBrute.class);
        }

        if(jerConfig.JER_MOBS.enableCoolmarSpider) {
            registerMob(new EntityCoolmarSpider(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.ARCTIC_ABYSS), NetherExLootTables.COOLMAR_SPIDER);
            adjustHumanoidRenderHook(EntityCoolmarSpider.class);
        }

        if(jerConfig.JER_MOBS.enableEmber) {
            registerMob(new EntityEmber(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.TORRID_WASTELAND), NetherExLootTables.EMBER);
        }

        if(jerConfig.JER_MOBS.enableFrost) {
            registerMob(new EntityFrost(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.ARCTIC_ABYSS), NetherExLootTables.FROST);
            adjustHumanoidRenderHook(EntityFrost.class);
        }

        if(jerConfig.JER_MOBS.enableGhastling) {
            registerMob(new EntityGhastling(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes("NETHER"), NetherExLootTables.GHASTLING);
            registerRenderHook(EntityGhastling.class, ((renderInfo, e) -> {
                GlStateManager.scale(0.6, 0.6, 0.6);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableNethermite) {
            registerMob(new EntityNethermite(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes("NETHER"), NetherExLootTables.NETHERMITE);
            registerRenderHook(EntityNethermite.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.5, 1.5, 1.5);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableSpinout) {
            registerMob(new EntitySpinout(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.RUTHLESS_SANDS), NetherExLootTables.SPINOUT);
            adjustHumanoidRenderHook(EntitySpinout.class);
        }

        if(jerConfig.JER_MOBS.enableSpore) {
            registerMob(new EntitySpore(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.FUNGI_FOREST), NetherExLootTables.SPORE);
            adjustHumanoidRenderHook(EntitySpore.class);
        }

        if(jerConfig.JER_MOBS.enableSporeCreeper) {
            registerMob(new EntitySporeCreeper(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.FUNGI_FOREST), NetherExLootTables.SPORE_CREEPER);
            adjustHumanoidRenderHook(EntitySporeCreeper.class);
        }

        if(jerConfig.JER_MOBS.enableWight) {
            registerMob(new EntityWight(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.ARCTIC_ABYSS), NetherExLootTables.WIGHT);
            adjustHumanoidRenderHook(EntityWight.class);
        }
    }

    private void registerNeutral() {
        if(jerConfig.JER_MOBS.enableGoldGolem) {
            EntityGoldGolem goldGolem = new EntityGoldGolem(world);
            registerMob(goldGolem, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.GOLD_GOLEM);
        }

        if(jerConfig.JER_MOBS.enableMogusBrown) {
            EntityMogus mogusBrown = new EntityMogus(world);
            mogusBrown.setType(EntityMogus.Type.BROWN);
            registerMob(mogusBrown, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.FUNGI_FOREST), NetherExLootTables.BROWN_MOGUS);
        }

        if(jerConfig.JER_MOBS.enableMogusRed) {
            EntityMogus mogusRed = new EntityMogus(world);
            mogusRed.setType(EntityMogus.Type.RED);
            registerMob(mogusRed, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.FUNGI_FOREST), NetherExLootTables.RED_MOGUS);
        }

        if(jerConfig.JER_MOBS.enableMogusWhite) {
            EntityMogus mogusWhite = new EntityMogus(world);
            mogusWhite.setType(EntityMogus.Type.WHITE);
            registerMob(mogusWhite, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.FUNGI_FOREST), NetherExLootTables.WHITE_MOGUS);
        }

        if(jerConfig.JER_MOBS.enableSalamanderBlack) {
            EntitySalamander salamanderBlack = new EntitySalamander(world);
            salamanderBlack.setType(EntitySalamander.Type.BLACK);
            registerMob(salamanderBlack, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.TORRID_WASTELAND), NetherExLootTables.BLACK_SALAMANDER);
        }

        if(jerConfig.JER_MOBS.enableSalamanderOrange) {
            EntitySalamander salamanderOrange = new EntitySalamander(world);
            salamanderOrange.setType(EntitySalamander.Type.ORANGE);
            registerMob(salamanderOrange, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(NetherExBiomes.TORRID_WASTELAND), NetherExLootTables.ORANGE_SALAMANDER);
        }
    }

    private void registerPassive() {
        if(jerConfig.JER_MOBS.enablePigtificateVillagers) {
            //Forager
            EntityPigtificate pigtificateGatherer = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
            pigtificateGatherer.setCareer(NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource("gatherer")));
            registerMob(pigtificateGatherer, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_GATHERER);

            EntityPigtificate pigtificateHunter = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
            pigtificateHunter.setCareer(NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource("hunter")));
            registerMob(pigtificateHunter, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_HUNTER);

            EntityPigtificate pigtificateScavenger = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
            pigtificateScavenger.setCareer(NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource("scavenger")));
            registerMob(pigtificateScavenger, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_SCAVENGER);

            //Smith
            EntityPigtificate pigtificateArmorsmith = new EntityPigtificate(world, NetherExPigtificates.SMITH);
            pigtificateArmorsmith.setCareer(NetherExPigtificates.SMITH.getCareer(NetherEx.getResource("armorsmith")));
            registerMob(pigtificateArmorsmith, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_ARMORSMITH);

            EntityPigtificate pigtificateToolsmith = new EntityPigtificate(world, NetherExPigtificates.SMITH);
            pigtificateToolsmith.setCareer(NetherExPigtificates.SMITH.getCareer(NetherEx.getResource("toolsmith")));
            registerMob(pigtificateToolsmith, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_TOOLSMITH);

            //Sorcerer
            EntityPigtificate pigtificateSorcerer = new EntityPigtificate(world, NetherExPigtificates.SORCERER);
            pigtificateSorcerer.setCareer(NetherExPigtificates.SORCERER.getCareer(NetherEx.getResource("brewer")));
            registerMob(pigtificateSorcerer, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_BREWER);

            EntityPigtificate pigtificateEnchanter = new EntityPigtificate(world, NetherExPigtificates.SORCERER);
            pigtificateEnchanter.setCareer(NetherExPigtificates.SORCERER.getCareer(NetherEx.getResource("enchanter")));
            registerMob(pigtificateEnchanter, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_ENCHANTER);

            //Dimwit
            EntityPigtificate pigtificateDimwit = new EntityPigtificate(world, NetherExPigtificates.DIMWIT);
            pigtificateDimwit.setCareer(NetherExPigtificates.DIMWIT.getCareer(NetherEx.getResource("nincompoop")));
            registerMob(pigtificateDimwit, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_NINCOMPOOP);
        }

        //Leader
        if(jerConfig.JER_MOBS.enablePigtificateLeader) {
            EntityPigtificateLeader pigtificateLeader = new EntityPigtificateLeader(world);
            pigtificateLeader.setProfession(NetherExPigtificates.LEADER);
            pigtificateLeader.setCareer(NetherExPigtificates.LEADER.getCareer(NetherEx.getResource("chief")));
            registerMob(pigtificateLeader, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), NetherExLootTables.PIGTIFICATE_CHIEF);
        }
        adjustHumanoidRenderHook(EntityPigtificate.class);
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> entity) {
        registerRenderHook(entity, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05, -0.45, 0);
            return renderInfo;
        }));
    }

    private CustomVillagerEntry getPigtificateArmorsmith() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "armorsmith";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.ORANGE_SALAMANDER_HIDE, 2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(NetherExItems.BLACK_SALAMANDER_HIDE, 2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1), new ItemStack(NetherExItems.WITHER_BONE, 12)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHER_BONE, 2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.ORANGE_SALAMANDER_HIDE_HELMET), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.ORANGE_SALAMANDER_HIDE_CHESTPLATE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.ORANGE_SALAMANDER_HIDE_LEGGINGS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.ORANGE_SALAMANDER_HIDE_BOOTS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.BLACK_SALAMANDER_HIDE_HELMET), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.BLACK_SALAMANDER_HIDE_CHESTPLATE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.BLACK_SALAMANDER_HIDE_LEGGINGS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(NetherExItems.BLACK_SALAMANDER_HIDE_BOOTS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.WITHER_BONE_HELMET), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHER_BONE_CHESTPLATE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHER_BONE_LEGGINGS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHER_BONE_BOOTS), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.SMITH.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.SMITH);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateBrewer() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "brewer";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.SPORE, 4), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(Items.MAGMA_CREAM, 4), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(Items.GLASS_BOTTLE, 5), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(ItemHelper.getRandomPotion(), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 9)),
                    new BasicTrade(ItemHelper.getRandomPotion(), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(ItemHelper.getRandomPotion(), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.SORCERER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.SORCERER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateChief() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "chief";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();
        }

        PigtificateProfession.Career career = NetherExPigtificates.LEADER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificateLeader entity = new EntityPigtificateLeader(world);
                entity.setProfession(NetherExPigtificates.LEADER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateEnchanter() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "enchanter";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.BOOK,1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 7)),
                    new BasicTrade(new ItemStack(Items.GLOWSTONE_DUST, 4), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2), new ItemStack(Items.BLAZE_ROD, 6)),
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(6), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4, 16))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(8), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,20)),
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(8), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 20))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.EXPERIENCE_BOTTLE, 3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 12)),
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(10), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 30)),
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(10), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 30)),
                    new BasicTrade(ItemHelper.getRandomlyEnchantedBook(10), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 30))
            )));


        }

        PigtificateProfession.Career career = NetherExPigtificates.SORCERER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.SORCERER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateGatherer() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "gatherer";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.CONGEALED_MAGMA_CREAM, 2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(NetherExBlocks.BROWN_ELDER_MUSHROOM, 3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExBlocks.RED_ELDER_MUSHROOM, 3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1), new ItemStack(NetherExItems.WITHER_BONE, 48))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.ENOKI_MUSHROOM, 3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3), new ItemStack(Items.QUARTZ, 14))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.NETHER_WART, 4), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateHunter() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "hunter";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.ROTTEN_FLESH, 15), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(Items.SPIDER_EYE, 6), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1)),
                    new BasicTrade(new ItemStack(Items.MAGMA_CREAM, 2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.GHAST_MEAT_COOKED, 7), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2), new ItemStack(NetherExItems.GHAST_MEAT_RAW, 1))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.ENDER_PEARL, 4), new ItemStack(NetherExItems.GHAST_MEAT_RAW, 6)),
                    new BasicTrade(new ItemStack(Items.BLAZE_ROD, 4), new ItemStack(NetherExItems.GHAST_MEAT_RAW, 6))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateNincompoop() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "nincompoop";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();
        }

        PigtificateProfession.Career career = NetherExPigtificates.DIMWIT.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.DIMWIT);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateScavenger() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "scavenger";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Blocks.COBBLESTONE,10), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1)),
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1), new ItemStack(Blocks.STONE, 24)),
                    new BasicTrade(new ItemStack(Blocks.DIRT,3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1)),
                    new BasicTrade(new ItemStack(Blocks.GRAVEL,12), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Blocks.LOG,3), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,6)),
                    new BasicTrade(new ItemStack(Items.IRON_INGOT,2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1)),
                    new BasicTrade(new ItemStack(Items.COAL,5, 1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1)),
                    new BasicTrade(new ItemStack(Items.COAL,5, 1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,1))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.DIAMOND, 1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,6)),
                    new BasicTrade(new ItemStack(Items.BOOK,2), new ItemStack(NetherExItems.AMETHYST_CRYSTAL,3))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.FORAGER.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.FORAGER);
                entity.setCareer(career);
                return entity;
            }
        };
    }

    private CustomVillagerEntry getPigtificateToolsmith() {
        List<List<EntityVillager.ITradeList>> allTrades;
        String careerName = "toolsmith";

        if(NetherExConfig.entity.pigtificate.useGlobalTradeConfigs) {
            allTrades = readPigtificateTradeConfigs(careerName);
        } else {
            allTrades = new ArrayList<>();

            //Tier 1
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3), new ItemStack(Items.GOLD_INGOT, 3)),
                    new BasicTrade(new ItemStack(Items.STONE_SWORD), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(Items.STONE_PICKAXE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(Items.STONE_SHOVEL), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2)),
                    new BasicTrade(new ItemStack(Items.STONE_HOE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 1))
            )));

            //Tier 2
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(Items.DIAMOND, 1), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(Items.IRON_SWORD), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(Items.IRON_PICKAXE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(Items.IRON_SHOVEL), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(Items.IRON_HOE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 2))
            )));

            //Tier 3
            allTrades.add(new ArrayList<>(Sets.newHashSet(
                    new BasicTrade(new ItemStack(NetherExItems.WITHERED_AMEDIAN_SWORD), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHERED_AMEDIAN_PICKAXE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHERED_AMEDIAN_SHOVEL), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHERED_AMEDIAN_HOE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(NetherExItems.WITHERED_AMEDIAN_HAMMER), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 6)),
                    new BasicTrade(new ItemStack(NetherExItems.BLAZED_AMEDIAN_SWORD), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4, 7)),
                    new BasicTrade(new ItemStack(NetherExItems.BLAZED_AMEDIAN_PICKAXE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.BLAZED_AMEDIAN_SHOVEL), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(NetherExItems.BLAZED_AMEDIAN_HOE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(NetherExItems.BLAZED_AMEDIAN_HAMMER), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 6)),
                    new BasicTrade(new ItemStack(NetherExItems.FROSTED_AMEDIAN_SWORD), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.FROSTED_AMEDIAN_PICKAXE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 5)),
                    new BasicTrade(new ItemStack(NetherExItems.FROSTED_AMEDIAN_SHOVEL), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 4)),
                    new BasicTrade(new ItemStack(NetherExItems.FROSTED_AMEDIAN_HOE), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 3)),
                    new BasicTrade(new ItemStack(NetherExItems.FROSTED_AMEDIAN_HAMMER), new ItemStack(NetherExItems.AMETHYST_CRYSTAL, 6))
            )));
        }

        PigtificateProfession.Career career = NetherExPigtificates.SMITH.getCareer(NetherEx.getResource(careerName));
        String name = NetherEx.getResource("pigtificate_" + careerName).toString();

        return new CustomVillagerEntry(name, career.getId(), allTrades) {
            @Override
            public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                EntityPigtificate entity = new EntityPigtificate(world, NetherExPigtificates.SMITH);
                entity.setCareer(career);
                return entity;
            }
        };
    }
    
    private List<List<EntityVillager.ITradeList>> readPigtificateTradeConfigs(String careerName) {
        Path tradeConfigPath = Paths.get(Loader.instance().getConfigDir().toString(), NetherEx.MOD_ID, "pigtificate_trades", NetherEx.MOD_ID, careerName + ".json");
        List<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>();

        try {
            if (!Files.exists(tradeConfigPath))
                return allTrades;

            LogHelper.info("Reading Pigtificate trade config. " + tradeConfigPath);

            File configFile = tradeConfigPath.toFile();
            if (FileHelper.getFileExtension(configFile).equals("json") && Files.isReadable(tradeConfigPath)) {
                String fileText = FileUtils.readFileToString(configFile, Charset.defaultCharset()).trim();
                JsonObject jsonObject = new Gson().fromJson(fileText, JsonObject.class);

                PigtificateProfession profession = NetherExRegistries.PIGTIFICATE_PROFESSIONS.getValue(new ResourceLocation(jsonObject.get("profession").getAsString()));
                PigtificateProfession.Career career = profession.getCareer(new ResourceLocation(jsonObject.get("career").getAsString()));

                JsonArray tradeConfigs = jsonObject.get("trades").getAsJsonArray();

                for(JsonElement trade : tradeConfigs) {
                    ItemStack output = ItemHelper.getItemStack(trade.getAsJsonObject().get("output").getAsJsonObject());
                    ItemStack input1 = ItemHelper.getItemStack(trade.getAsJsonObject().get("inputOne").getAsJsonObject());
                    ItemStack input2 = trade.getAsJsonObject().has("inputTwo") ? ItemHelper.getItemStack(trade.getAsJsonObject().get("inputTwo").getAsJsonObject()) : ItemStack.EMPTY;
                    int tradeLevel = trade.getAsJsonObject().has("tradeLevel") ? trade.getAsJsonObject().get("tradeLevel").getAsInt() : 1;

                    if(allTrades.size() < tradeLevel) {
                        for(int i = allTrades.size(); i < tradeLevel; i++) {
                            allTrades.add(i, new ArrayList<>());
                        }
                    }
                    (allTrades.get(tradeLevel - 1)).add(new BasicTrade(output, input1, input2));
                }


            }
        } catch (Exception e) {
            LogHelper.warn("Error reading Pigtificate trade config. " + tradeConfigPath);
        }
        return allTrades;
    }
}
