package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigTwilightForest;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMLightLevel;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.RegistryBiomeEvent;
import twilightforest.entity.*;
import twilightforest.entity.boss.*;
import twilightforest.entity.passive.*;
import twilightforest.item.TFItems;

import static com.invadermonky.justenoughmagiculture.util.ModIds.TWILIGHT_FOREST;

public class JERTwilightForest extends JERBase implements IJERIntegration {
    private final JEMConfigTwilightForest.JER jerConfig = JEMConfig.TWILIGHT_FOREST.JUST_ENOUGH_RESOURCES;
    private final String[] tfBiomeType = BiomeHelper.getTypeNamesForTypes(RegistryBiomeEvent.TWILIGHT);
    
    public JERTwilightForest(boolean enableJERDungeons, boolean enableJEREntities) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJEREntities) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        registerTFDungeonLoot("aurora_cache");
        registerTFDungeonLoot("aurora_room");
        registerTFDungeonLoot("basement");
        registerTFDungeonLoot("darktower_cache");
        registerTFDungeonLoot("darktower_key");
        registerTFDungeonLoot("graveyard");
        registerTFDungeonLoot("hedge_maze");
        registerTFDungeonLoot("hill_1");
        registerTFDungeonLoot("hill_2");
        registerTFDungeonLoot("hill_3");
        registerTFDungeonLoot("labyrinth_dead_end");
        registerTFDungeonLoot("labyrinth_room");
        registerTFDungeonLoot("labyrinth_vault");
        registerTFDungeonLoot("stronghold_cache");
        registerTFDungeonLoot("stronghold_room");
        registerTFDungeonLoot("tower_library");
        registerTFDungeonLoot("tower_room");
        registerTFDungeonLoot("tree_cache");
        registerTFDungeonLoot("troll_garden");
        registerTFDungeonLoot("troll_vault");
    }

    @Override
    public void registerModEntities() {
        registerBosses();
        registerMobs();
        registerPassive();
    }

    private void registerBosses() {
        if(jerConfig.enableHydra) {
            EntityTFHydra hydra = new EntityTFHydra(world);
            EntityTFHydraHead hydraHead = new EntityTFHydraHead(world);
            registerMob(hydraHead, LightLevel.hostile, tfBiomeType, hydra.getLootTable());
            registerRenderHook(hydraHead.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.6, 0.6, 0.6);
                GlStateManager.translate(-0.05, -1.0, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableKnightPhantom) {
            EntityTFKnightPhantom knightPhantom = new EntityTFKnightPhantom(world);
            knightPhantom.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(TFItems.knightmetal_sword));
            knightPhantom.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(TFItems.phantom_chestplate));
            knightPhantom.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(TFItems.phantom_helmet));
            registerMob(knightPhantom, LightLevel.hostile, tfBiomeType, (new JERDungeonStrings("stronghold_boss")).lootTable);
            adjustHumanoidRenderHook(knightPhantom.getClass());
        }

        if(jerConfig.enableTwilightLich) {
            EntityTFLich lich = new EntityTFLich(world);
            registerMob(lich, LightLevel.hostile, tfBiomeType, lich.getLootTable());
            adjustLargeHumanoidRenderHook(lich.getClass());
        }

        if(jerConfig.enableMinoshroom) {
            EntityTFMinoshroom minoshroom = new EntityTFMinoshroom(world);
            registerMob(minoshroom, LightLevel.hostile, tfBiomeType, minoshroom.getLootTable());
        }

        if(jerConfig.enableNaga) {
            EntityTFNaga naga = new EntityTFNaga(world);
            registerMob(naga, LightLevel.any, tfBiomeType, naga.getLootTable());
            registerRenderHook(naga.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.05, -0.8, 0);
                return renderInfo;
            }));
        }

        // Quest Ram registered as dungeon treasure due to render entity crash and that it's a quest not a kill target.
        if(jerConfig.enableQuestingRam) {
            registerDungeonLoot(
                    TWILIGHT_FOREST.MOD_ID + ":entities/quest_ram_rewards",
                    "dungeon." + JustEnoughMagiculture.MOD_ALIAS + ":" + TWILIGHT_FOREST.MOD_ID + ".quest_ram_rewards",
                    new ResourceLocation(TWILIGHT_FOREST.MOD_ID, "entities/questing_ram_rewards")
            );
        }

        // TODO: Fix render crash. No clue how to do this.
        // EntityTFSnowQueen snowQueen = new EntityTFSnowQueen(world);
        // registerMob(snowQueen, LightLevel.hostile, tfBiomeType, snowQueen.getLootTable());

        if(jerConfig.enableUrGhast) {
            EntityTFUrGhast urGhast = new EntityTFUrGhast(world);
            registerMob(urGhast, LightLevel.hostile, tfBiomeType, (new JERDungeonStrings("darktower_boss")).lootTable);
            registerRenderHook(urGhast.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.25, 0.25, 0.25);
                GlStateManager.translate(-0.25, -1.75, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableYetiAlpha) {
            EntityTFYetiAlpha yetiAlpha = new EntityTFYetiAlpha(world);
            registerMob(yetiAlpha, LightLevel.hostile, tfBiomeType, yetiAlpha.getLootTable());
            adjustHugeHumanoidRenderHook(yetiAlpha.getClass());
        }
    }

    private void registerMobs() {
        if(jerConfig.enableArmoredGiant) {
            EntityTFArmoredGiant armoredGiant = new EntityTFArmoredGiant(world);
            armoredGiant.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            armoredGiant.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            armoredGiant.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            armoredGiant.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
            armoredGiant.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
            registerMob(armoredGiant, LightLevel.hostile, tfBiomeType, armoredGiant.getLootTable());
        }

        if(jerConfig.enableBlockGoblin) {
            EntityTFBlockGoblin blockGoblin = new EntityTFBlockGoblin(world);
            registerMob(blockGoblin, LightLevel.hostile, tfBiomeType, blockGoblin.getLootTable());
            adjustHumanoidRenderHook(blockGoblin.getClass());
        }

        if(jerConfig.enableDeathTome) {
            EntityTFDeathTome deathTome = new EntityTFDeathTome(world);
            registerMob(deathTome, LightLevel.hostile, tfBiomeType, deathTome.getLootTable());
            adjustHumanoidRenderHook(deathTome.getClass());
        }

        if(jerConfig.enableFireBeetle) {
            EntityTFFireBeetle fireBeetle = new EntityTFFireBeetle(world);
            registerMob(fireBeetle, LightLevel.hostile, tfBiomeType, fireBeetle.getLootTable());
        }

        if(jerConfig.enableGiantMiner) {
            EntityTFGiantMiner giantMiner = new EntityTFGiantMiner(world);
            giantMiner.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_PICKAXE));
            registerMob(giantMiner, LightLevel.hostile, tfBiomeType, giantMiner.getLootTable());
            adjustHugeHumanoidRenderHook(giantMiner.getClass());
        }

        if(jerConfig.enableGoblinKnight) {
            EntityTFGoblinKnightUpper goblinKnightUpper = new EntityTFGoblinKnightUpper(world);
            registerMob(goblinKnightUpper, LightLevel.hostile, tfBiomeType, goblinKnightUpper.getLootTable());
        }

        if(jerConfig.enableHedgeSpider) {
            EntityTFHedgeSpider hedgeSpider = new EntityTFHedgeSpider(world);
            registerMob(hedgeSpider, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/hedge_spider"));
        }

        if(jerConfig.enableHelmetCrab) {
            EntityTFHelmetCrab helmetCrab = new EntityTFHelmetCrab(world);
            registerMob(helmetCrab, LightLevel.hostile, tfBiomeType, helmetCrab.getLootTable());
        }

        if(jerConfig.enableHostileWolf) {
            EntityTFHostileWolf hostileWolf = new EntityTFHostileWolf(world);
            registerMob(hostileWolf, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/hostile_wolf"));
        }

        if(jerConfig.enableUnstableIceCore) {
            EntityTFIceExploder iceExploder = new EntityTFIceExploder(world);
            registerMob(iceExploder, LightLevel.hostile, tfBiomeType, iceExploder.getLootTable());
            registerRenderHook(iceExploder.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.1, -0.5, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableStableIceCore) {
            EntityTFIceShooter iceShooter = new EntityTFIceShooter(world);
            registerMob(iceShooter, LightLevel.hostile, tfBiomeType, iceShooter.getLootTable());
            registerRenderHook(iceShooter.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.1, -0.5, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableKingSpider) {
            EntityTFKingSpider kingSpider = new EntityTFKingSpider(world);
            registerMob(kingSpider, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/king_spider"));
            registerRenderHook(kingSpider.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.9, 0.9, 0.9);
                GlStateManager.translate(0.0, -0.5, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableKobold) {
            EntityTFKobold kobold = new EntityTFKobold(world);
            registerMob(kobold, LightLevel.hostile, tfBiomeType, kobold.getLootTable());
            adjustHumanoidRenderHook(kobold.getClass());
        }

        if(jerConfig.enableMazeSlime) {
            EntityTFMazeSlime mazeSlime = new EntityTFMazeSlime(world);
            registerMob(mazeSlime, LightLevel.hostile, 1, 3, tfBiomeType, mazeSlime.getLootTable());
        }

        if(jerConfig.enableCarminiteGhasting) {
            EntityTFMiniGhast miniGhast = new EntityTFMiniGhast(world);
            registerMob(miniGhast, LightLevel.hostile, tfBiomeType, miniGhast.getLootTable());
            registerRenderHook(miniGhast.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(0, 2.25, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMInotaur) {
            EntityTFMinotaur minotaur = new EntityTFMinotaur(world);
            minotaur.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
            registerMob(minotaur, LightLevel.hostile, tfBiomeType, minotaur.getLootTable());
            adjustHumanoidRenderHook(minotaur.getClass());
        }

        if(jerConfig.enableMistWolf) {
            EntityTFMistWolf mistWolf = new EntityTFMistWolf(world);
            registerMob(mistWolf, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/mist_wolf"));
            registerRenderHook(mistWolf.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.7, 0.7, 0.7);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMosquitoSwarm) {
            EntityTFMosquitoSwarm mosquitoSwarm = new EntityTFMosquitoSwarm(world);
            registerMob(mosquitoSwarm, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/mosquito_swarm"));
            registerRenderHook(mosquitoSwarm.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.9, 0.9, 0.9);
                GlStateManager.translate(-0.05, -0.8, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enablePinchBeetle) {
            EntityTFPinchBeetle pinchBeetle = new EntityTFPinchBeetle(world);
            registerMob(pinchBeetle, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/pinch_beetle"));
        }

        if(jerConfig.enableRedcapGoblin) {
            EntityTFRedcap redcap = new EntityTFRedcap(world);
            registerMob(redcap, LightLevel.hostile, tfBiomeType, redcap.getLootTable());
            adjustHumanoidRenderHook(redcap.getClass());
        }

        if(jerConfig.enableRedcapSapper) {
            EntityTFRedcapSapper redcapSapper = new EntityTFRedcapSapper(world);
            registerMob(redcapSapper, LightLevel.hostile, tfBiomeType, redcapSapper.getLootTable());
        }

        if(jerConfig.enableSkeletonDruid) {
            EntityTFSkeletonDruid skeletonDruid = new EntityTFSkeletonDruid(world);
            registerMob(skeletonDruid, LightLevel.hostile, tfBiomeType, skeletonDruid.getLootTable());
        }

        if(jerConfig.enableSlimeBeetle) {
            EntityTFSlimeBeetle slimeBeetle = new EntityTFSlimeBeetle(world);
            registerMob(slimeBeetle, LightLevel.hostile, tfBiomeType, slimeBeetle.getLootTable());
        }

        if(jerConfig.enableSnowGuardian) {
            EntityTFSnowGuardian snowGuardian = new EntityTFSnowGuardian(world);
            snowGuardian.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(TFItems.ironwood_sword));
            snowGuardian.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(TFItems.ironwood_helmet));
            snowGuardian.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(TFItems.ironwood_chestplate));
            registerMob(snowGuardian, LightLevel.hostile, tfBiomeType, snowGuardian.getLootTable());
            adjustHumanoidRenderHook(snowGuardian.getClass());
        }

        if(jerConfig.enableSwarmSpider) {
            EntityTFSwarmSpider swarmSpider = new EntityTFSwarmSpider(world);
            registerMob(swarmSpider, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/swarm_spider"));
        }

        if(jerConfig.enableCarminiteBroodling) {
            EntityTFTowerBroodling towerBroodling = new EntityTFTowerBroodling(world);
            registerMob(towerBroodling, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/tower_broodling"));
        }

        if(jerConfig.enableCarminiteGhast) {
            EntityTFTowerGhast towerGhast = new EntityTFTowerGhast(world);
            registerMob(towerGhast, LightLevel.hostile, tfBiomeType, TwilightForestMod.prefix("entities/tower_ghast"));
            registerRenderHook(towerGhast.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(0, -2.75, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableCarminiteGolem) {
            EntityTFTowerGolem towerGolem = new EntityTFTowerGolem(world);
            registerMob(towerGolem, LightLevel.hostile, tfBiomeType, towerGolem.getLootTable());
            adjustLargeHumanoidRenderHook(towerGolem.getClass());
        }

        if(jerConfig.enableTowerwoodBorer) {
            EntityTFTowerTermite towerTermite = new EntityTFTowerTermite(world);
            registerMob(towerTermite, LightLevel.hostile, tfBiomeType, towerTermite.getLootTable());
        }

        if(jerConfig.enableCaveTroll) {
            EntityTFTroll troll = new EntityTFTroll(world);
            registerMob(troll, LightLevel.hostile, tfBiomeType, troll.getLootTable());
            adjustLargeHumanoidRenderHook(troll.getClass());
        }

        if(jerConfig.enableWinterWolf) {
            EntityTFWinterWolf winterWolf = new EntityTFWinterWolf(world);
            registerMob(winterWolf, LightLevel.hostile, tfBiomeType, winterWolf.getLootTable());
            registerRenderHook(winterWolf.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.7, 0.7, 0.7);
                return renderInfo;
            }));
        }

        if(jerConfig.enableTwilightWraith) {
            EntityTFWraith wraith = new EntityTFWraith(world);
            registerMob(wraith, LightLevel.hostile, tfBiomeType, wraith.getLootTable());
            adjustHumanoidRenderHook(wraith.getClass());
        }

        if(jerConfig.enableYeti) {
            EntityTFYeti yeti = new EntityTFYeti(world);
            registerMob(yeti, LightLevel.any, tfBiomeType, yeti.getLootTable());
            adjustLargeHumanoidRenderHook(yeti.getClass());
        }
    }

    private void registerPassive() {
        if(jerConfig.enableBighornSheep) {
            //Bighorn Sheep Variants
            for (EnumDyeColor color : EnumDyeColor.values()) {
                EntityTFBighorn bighorn = new EntityTFBighorn(world);
                bighorn.setFleeceColor(color);
                ResourceLocation bighornTable = new ResourceLocation(TWILIGHT_FOREST.MOD_ID, "entities/bighorn_sheep/" + color.getName());
                registerMob(bighorn, JEMLightLevel.animal, tfBiomeType, bighornTable);
            }
        }

        if(jerConfig.enableWildBoar) {
            EntityTFBoar boar = new EntityTFBoar(world);
            registerMob(boar, JEMLightLevel.animal, tfBiomeType, boar.getLootTable());
        }

        if(jerConfig.enableDwarfRabbit) {
            EntityTFBunny bunny = new EntityTFBunny(world);
            registerMob(bunny, JEMLightLevel.animal, tfBiomeType, bunny.getLootTable());
            registerRenderHook(bunny.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(0.0, -0.25, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableWildDeer) {
            EntityTFDeer deer = new EntityTFDeer(world);
            registerMob(deer, JEMLightLevel.animal, tfBiomeType, deer.getLootTable());
        }

        if(jerConfig.enableFirefly) {
            EntityTFMobileFirefly firefly = new EntityTFMobileFirefly(world);
            registerMob(firefly, JEMLightLevel.animal, tfBiomeType, new ResourceLocation(TWILIGHT_FOREST.MOD_ID, "entities/mobile_firefly"));
        }

        if(jerConfig.enablePenguin) {
            EntityTFPenguin penguin = new EntityTFPenguin(world);
            registerMob(penguin, JEMLightLevel.animal, tfBiomeType, penguin.getLootTable());
        }

        if(jerConfig.enableForestRaven) {
            EntityTFRaven raven = new EntityTFRaven(world);
            registerMob(raven, JEMLightLevel.animal, tfBiomeType, raven.getLootTable());
        }

        if(jerConfig.enableForestSquirrel) {
            EntityTFSquirrel squirrel = new EntityTFSquirrel(world);
            registerMob(squirrel, JEMLightLevel.animal, tfBiomeType, squirrel.getLootTable());
            registerRenderHook(squirrel.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(0.0, -0.25, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableTinyBird) {
            EntityTFTinyBird tinyBird = new EntityTFTinyBird(world);
            registerMob(tinyBird, JEMLightLevel.animal, tfBiomeType, tinyBird.getLootTable());
        }
    }
    
    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05, -0.5, 0);
            return renderInfo;
        }));
    }

    private void adjustLargeHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.6, 0);
            return renderInfo;
        }));
    }

    private void adjustHugeHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.1,-1.6,0);
            return renderInfo;
        }));
    }

    private void registerTFDungeonLoot(String name) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String dungeon) {
            this.category = String.format("%s:structures/%s", TWILIGHT_FOREST.MOD_ID, dungeon);
            this.unlocName = StringHelper.getDungeonTranslationKey(TWILIGHT_FOREST.MOD_ID, dungeon);
            this.lootTable = new ResourceLocation(String.format("%s/%s", category, dungeon));
        }
    }
}
