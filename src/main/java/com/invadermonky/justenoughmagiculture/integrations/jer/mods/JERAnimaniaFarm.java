package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.chickens.EntityChickBase;
import com.animania.addons.farm.common.entity.chickens.EntityHenBase;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCowAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCowFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCowHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCowHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCowHolstein;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityBullJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCowJersey;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.cows.EntityCalfBase;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.EntityKidBase;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityDoeFainting;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityBuckKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityDoeKiko;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityBuckKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityDoeKinder;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityDoePygmy;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.EntityFoalBase;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.entity.pigs.EntityPigletBase;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityHogDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntitySowHampshire;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.EntityLambBase;
import com.animania.addons.farm.common.entity.sheep.EntityRamBase;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityEweDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityRamDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.addons.farm.config.FarmConfig;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.chickens.RenderHenBaseJER;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.pigs.*;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigAnimaniaFarm;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMLightLevel;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class JERAnimaniaFarm extends JERBase implements IJERIntegration {
    private static JERAnimaniaFarm instance;
    private static final JEMConfigAnimaniaFarm.JER.Animals jerConfig = JEMConfig.ANIMANIA_FARM.JUST_ENOUGH_RESOURCES.ANIMALS;
    private static final boolean registerMales = JEMConfig.ANIMANIA_FARM.JUST_ENOUGH_RESOURCES.registerMaleAnimals;
    private static final boolean registerFemales = JEMConfig.ANIMANIA_FARM.JUST_ENOUGH_RESOURCES.registerFemaleAnimals;
    private static final boolean invertCows = JEMConfig.ANIMANIA_FARM.JUST_ENOUGH_RESOURCES.invertCows;

    private JERAnimaniaFarm() {}

    public JERAnimaniaFarm(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    public static JERAnimaniaFarm getInstance() {
        return instance == null ? instance = new JERAnimaniaFarm() : instance;
    }

    @Override
    public void registerModEntities() {
        registerChickens();
        registerCows();
        registerGoats();
        registerDraftHorses();
        registerPigs();
        registerSheep();
    }

    private void registerChickens() {
        if(jerConfig.enableChickenLeghorn) {
            if(registerMales) {
                EntityRoosterLeghorn roosterLeghorn = new EntityRoosterLeghorn(world);
                registerMob(roosterLeghorn, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenLeghornBiomeTypes), getChickenLootTable(roosterLeghorn));
            }
            if(registerFemales) {
                EntityHenLeghorn henLeghorn = new EntityHenLeghorn(world);
                registerMob(henLeghorn, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenLeghornBiomeTypes), getChickenLootTable(henLeghorn));
            }
        }

        if(jerConfig.enableChickenOrpington) {
            if(registerMales) {
                EntityRoosterOrpington roosterOrpington = new EntityRoosterOrpington(world);
                registerMob(roosterOrpington, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenOrpingtonBiomeTypes), getChickenLootTable(roosterOrpington));
            }
            if(registerFemales) {
                EntityHenOrpington henOrpington = new EntityHenOrpington(world);
                registerMob(henOrpington, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenOrpingtonBiomeTypes), getChickenLootTable(henOrpington));
            }
        }

        if(jerConfig.enableChickenPlymouthRock) {
            if(registerMales) {
                EntityRoosterPlymouthRock roosterPlymouthRock = new EntityRoosterPlymouthRock(world);
                registerMob(roosterPlymouthRock, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenPlymouthRockBiomeTypes), getChickenLootTable(roosterPlymouthRock));
            }
            if(registerFemales) {
                EntityHenPlymouthRock henPlymouthRock = new EntityHenPlymouthRock(world);
                registerMob(henPlymouthRock, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenPlymouthRockBiomeTypes), getChickenLootTable(henPlymouthRock));
            }
        }

        if(jerConfig.enableChickenRhodeIslandRed) {
            if(registerMales) {
                EntityRoosterRhodeIslandRed roosterRhodeIslandRed = new EntityRoosterRhodeIslandRed(world);
                registerMob(roosterRhodeIslandRed, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenRhodeIslandRedBiomeTypes), getChickenLootTable(roosterRhodeIslandRed));
            }
            if(registerFemales) {
                EntityHenRhodeIslandRed henRhodeIslandRed = new EntityHenRhodeIslandRed(world);
                registerMob(henRhodeIslandRed, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenRhodeIslandRedBiomeTypes), getChickenLootTable(henRhodeIslandRed));
            }
        }

        if(jerConfig.enableChickenWyandotte) {
            if(registerMales) {
                EntityRoosterWyandotte roosterWyandotte = new EntityRoosterWyandotte(world);
                registerMob(roosterWyandotte, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenWyandotteBiomeTypes), getChickenLootTable(roosterWyandotte));
            }
            if(registerFemales) {
                EntityHenWyandotte henWyandotte = new EntityHenWyandotte(world);
                registerMob(henWyandotte, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.chickenWyandotteBiomeTypes), getChickenLootTable(henWyandotte));
            }
        }

        if(registerFemales) {
            registerRenderHook(EntityHenBase.class, ((renderInfo, e) -> {
                GlStateManager.translate(0.21, 0.28, 0);
                return renderInfo;
            }));
        }
    }

    private void registerCows() {
        boolean registerCowMales = (registerMales && registerFemales) || (registerMales && !invertCows);
        boolean registerCowFemales = registerFemales || (registerMales && invertCows);

        if(jerConfig.enableCowAngus) {
            if(registerCowMales) {
                EntityBullAngus bullAngus = new EntityBullAngus(world);
                registerMob(bullAngus, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowAngusBiomeTypes), getCowLootTable(bullAngus));
            }
            if(registerCowFemales) {
                EntityCowAngus cowAngus = new EntityCowAngus(world);
                registerMob(cowAngus, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowAngusBiomeTypes), getCowLootTable(cowAngus));
            }
        }

        if(jerConfig.enableCowFriesian) {
            if(registerCowMales) {
                EntityBullFriesian bullFriesian = new EntityBullFriesian(world);
                registerMob(bullFriesian, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowFriesianBiomeTypes), getCowLootTable(bullFriesian));
            }
            if(registerCowFemales) {
                EntityCowFriesian cowFriesian = new EntityCowFriesian(world);
                registerMob(cowFriesian, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowFriesianBiomeTypes), getCowLootTable(cowFriesian));
            }
        }

        if(jerConfig.enableCowHereford) {
            if(registerCowMales) {
                EntityBullHereford bullHereford = new EntityBullHereford(world);
                registerMob(bullHereford, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHerefordBiomeTypes), getCowLootTable(bullHereford));
            }
            if(registerCowFemales) {
                EntityCowHereford cowHereford = new EntityCowHereford(world);
                registerMob(cowHereford, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHerefordBiomeTypes), getCowLootTable(cowHereford));
            }
        }

        if(jerConfig.enableCowHighland) {
            if(registerCowMales) {
                EntityBullHighland bullHighland = new EntityBullHighland(world);
                registerMob(bullHighland, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHighlandBiomeTypes), getCowLootTable(bullHighland));
            }
            if(registerCowFemales) {
                EntityCowHighland cowHighland = new EntityCowHighland(world);
                registerMob(cowHighland, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHighlandBiomeTypes), getCowLootTable(cowHighland));
            }
        }

        if(jerConfig.enableCowHolstein) {
            if(registerCowMales) {
                EntityBullHolstein bullHolstein = new EntityBullHolstein(world);
                registerMob(bullHolstein, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHolsteinBiomeTypes), getCowLootTable(bullHolstein));
            }
            if(registerCowFemales) {
                EntityCowHolstein cowHolstein = new EntityCowHolstein(world);
                registerMob(cowHolstein, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowHolsteinBiomeTypes), getCowLootTable(cowHolstein));
            }
        }

        if(jerConfig.enableCowJersey) {
            if(registerCowMales) {
                EntityBullJersey bullJersey = new EntityBullJersey(world);
                registerMob(bullJersey, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowJerseyBiomeTypes), getCowLootTable(bullJersey));
            }
            if(registerCowFemales) {
                EntityCowJersey cowJersey = new EntityCowJersey(world);
                registerMob(cowJersey, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowJerseyBiomeTypes), getCowLootTable(cowJersey));
            }
        }

        if(jerConfig.enableCowLonghorn) {
            if(registerCowMales) {
                EntityBullLonghorn bullLonghorn = new EntityBullLonghorn(world);
                registerMob(bullLonghorn, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowLonghornBiomeTypes), getCowLootTable(bullLonghorn));
            }
            if(registerCowFemales) {
                EntityCowLonghorn cowLonghorn = new EntityCowLonghorn(world);
                registerMob(cowLonghorn, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowLonghornBiomeTypes), getCowLootTable(cowLonghorn));
            }
        }

        if(jerConfig.enableCowMooshroom) {
            if(registerCowMales) {
                EntityBullMooshroom bullMooshroom = new EntityBullMooshroom(world);
                registerMob(bullMooshroom, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowMooshroomBiomeTypes), getCowLootTable(bullMooshroom));
            }
            if(registerCowFemales) {
                EntityCowMooshroom cowMooshroom = new EntityCowMooshroom(world);
                registerMob(cowMooshroom, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.cowMooshroomBiomeTypes), getCowLootTable(cowMooshroom));
            }
        }
    }

    private void registerGoats() {
        if(jerConfig.enableGoatAlpine) {
            if(registerMales) {
                EntityBuckAlpine buckAlpine = new EntityBuckAlpine(world);
                registerMob(buckAlpine, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatAlpineBiomeTypes), getGoatLootTable(buckAlpine));
            }
            if(registerFemales) {
                EntityDoeAlpine doeAlpine = new EntityDoeAlpine(world);
                registerMob(doeAlpine, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatAlpineBiomeTypes), getGoatLootTable(doeAlpine));
            }
        }

        if(jerConfig.enableGoatAngora) {
            if(registerMales) {
                EntityBuckAngora buckAngora = new EntityBuckAngora(world);
                registerMob(buckAngora, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatAngoraBiomeTypes), getGoatLootTable(buckAngora));
            }
            if(registerFemales) {
                EntityDoeAngora doeAngora = new EntityDoeAngora(world);
                registerMob(doeAngora, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatAngoraBiomeTypes), getGoatLootTable(doeAngora));
            }
        }

        if(jerConfig.enableGoatFainting) {
            if(registerMales) {
                EntityBuckFainting buckFainting = new EntityBuckFainting(world);
                registerMob(buckFainting, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatFaintingBiomeTypes), getGoatLootTable(buckFainting));
            }
            if(registerFemales) {
                EntityDoeFainting doeFainting = new EntityDoeFainting(world);
                registerMob(doeFainting, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatFaintingBiomeTypes), getGoatLootTable(doeFainting));
            }
        }

        if(jerConfig.enableGoatKiko) {
            if(registerMales) {
                EntityBuckKiko buckKiko = new EntityBuckKiko(world);
                registerMob(buckKiko, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatKikoBiomeTypes), getGoatLootTable(buckKiko));
            }
            if(registerFemales) {
                EntityDoeKiko doeKiko = new EntityDoeKiko(world);
                registerMob(doeKiko, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatKikoBiomeTypes), getGoatLootTable(doeKiko));
            }
        }

        if(jerConfig.enableGoatKinder) {
            if(registerMales) {
                EntityBuckKinder buckKinder = new EntityBuckKinder(world);
                registerMob(buckKinder, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatKinderBiomeTypes), getGoatLootTable(buckKinder));
            }
            if(registerFemales) {
                EntityDoeKinder doeKinder = new EntityDoeKinder(world);
                registerMob(doeKinder, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatKinderBiomeTypes), getGoatLootTable(doeKinder));
            }
        }

        if(jerConfig.enableGoatNigerianDwarf) {
            if(registerMales) {
                EntityBuckNigerianDwarf buckNigerianDwarf = new EntityBuckNigerianDwarf(world);
                registerMob(buckNigerianDwarf, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatNigerianDwarfBiomeTypes), getGoatLootTable(buckNigerianDwarf));
            }
            if(registerFemales) {
                EntityDoeNigerianDwarf doeNigerianDwarf = new EntityDoeNigerianDwarf(world);
                registerMob(doeNigerianDwarf, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatNigerianDwarfBiomeTypes), getGoatLootTable(doeNigerianDwarf));
            }
        }

        if(jerConfig.enableGoatPygmy) {
            if(registerMales) {
                EntityBuckPygmy buckPygmy = new EntityBuckPygmy(world);
                registerMob(buckPygmy, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatPygmyBiomeTypes), getGoatLootTable(buckPygmy));
            }
            if(registerFemales) {
                EntityDoePygmy doePygmy = new EntityDoePygmy(world);
                registerMob(doePygmy, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.goatPygmyBiomeTypes), getGoatLootTable(doePygmy));
            }
        }
    }

    private void registerDraftHorses() {
        if(jerConfig.enableDraftHorseStallion) {
            EntityStallionDraftHorse stallionDraftHorse = new EntityStallionDraftHorse(world);
            registerMob(stallionDraftHorse, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.draftHorseBiomeTypes), getHorseLootTable(stallionDraftHorse));
        }
        if(jerConfig.enableDraftHorseMare) {
            EntityMareDraftHorse mareDraftHorse = new EntityMareDraftHorse(world);
            registerMob(mareDraftHorse, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.draftHorseBiomeTypes), getHorseLootTable(mareDraftHorse));
        }
    }

    private void registerPigs() {
        if(jerConfig.enablePigDuroc) {
            if(registerMales) {
                EntityHogDuroc hogDuroc = new EntityHogDuroc(world);
                registerMob(hogDuroc, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigDurocBiomeTypes), getPigLootTable(hogDuroc));
            }
            if(registerFemales) {
                EntitySowDuroc sowDuroc = new EntitySowDuroc(world);
                registerMob(sowDuroc, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigDurocBiomeTypes), getPigLootTable(sowDuroc));
            }
        }

        if(jerConfig.enablePigHampshire) {
            if(registerMales) {
                EntityHogHampshire hogHampshire = new EntityHogHampshire(world);
                registerMob(hogHampshire, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigHampshireBiomeTypes), getPigLootTable(hogHampshire));
            }
            if(registerFemales) {
                EntitySowHampshire sowHampshire = new EntitySowHampshire(world);
                registerMob(sowHampshire, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigHampshireBiomeTypes), getPigLootTable(sowHampshire));
            }
        }

        if(jerConfig.enablePigLargeBlack) {
            if(registerMales) {
                EntityHogLargeBlack hogLargeBlack = new EntityHogLargeBlack(world);
                registerMob(hogLargeBlack, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigLargeBlackBiomeTypes), getPigLootTable(hogLargeBlack));
            }
            if(registerFemales) {
                EntitySowLargeBlack sowLargeBlack = new EntitySowLargeBlack(world);
                registerMob(sowLargeBlack, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigLargeBlackBiomeTypes), getPigLootTable(sowLargeBlack));
            }
        }

        if(jerConfig.enablePigLargeWhite) {
            if(registerMales) {
                EntityHogLargeWhite hogLargeWhite = new EntityHogLargeWhite(world);
                registerMob(hogLargeWhite, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigLargeWhiteBiomeTypes), getPigLootTable(hogLargeWhite));
            }
            if(registerFemales) {
                EntitySowLargeWhite sowLargeWhite = new EntitySowLargeWhite(world);
                registerMob(sowLargeWhite, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigLargeWhiteBiomeTypes), getPigLootTable(sowLargeWhite));
            }
        }

        if(jerConfig.enablePigOldSpot) {
            if(registerMales) {
                EntityHogOldSpot hogOldSpot = new EntityHogOldSpot(world);
                registerMob(hogOldSpot, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigOldSpotBiomeTypes), getPigLootTable(hogOldSpot));
            }
            if(registerFemales) {
                EntitySowOldSpot sowOldSpot = new EntitySowOldSpot(world);
                registerMob(sowOldSpot, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigOldSpotBiomeTypes), getPigLootTable(sowOldSpot));
            }
        }

        if(jerConfig.enablePigYorkshire) {
            if(registerMales) {
                EntityHogYorkshire hogYorkshire = new EntityHogYorkshire(world);
                registerMob(hogYorkshire, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigYorkshireBiomeTypes), getPigLootTable(hogYorkshire));
            }
            if(registerFemales) {
                EntitySowYorkshire sowYorkshire = new EntitySowYorkshire(world);
                registerMob(sowYorkshire, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.pigYorkshireBiomeTypes), getPigLootTable(sowYorkshire));
            }
        }
    }

    private void registerSheep() {
        if(jerConfig.enableSheepDorper) {
            if(registerMales) {
                EntityRamDorper ramDorper = new EntityRamDorper(world);
                registerMob(ramDorper, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepDorperBiomeTypes), getSheepLootTable(ramDorper));
            }
            if(registerFemales) {
                EntityEweDorper eweDorper = new EntityEweDorper(world);
                registerMob(eweDorper, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepDorperBiomeTypes), getSheepLootTable(eweDorper));
            }
        }

        if(jerConfig.enableSheepDorset) {
            if(registerMales) {
                EntityRamDorset ramDorset = new EntityRamDorset(world);
                registerMob(ramDorset, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes), getSheepLootTable(ramDorset));
            }
            if(registerFemales) {
                EntityEweDorset eweDorset = new EntityEweDorset(world);
                registerMob(eweDorset, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes), getSheepLootTable(eweDorset));
            }
        }

        if(jerConfig.enableSheepFriesian) {
            if(registerMales) {
                EntityRamFriesian ramFriesian = new EntityRamFriesian(world);
                registerMob(ramFriesian, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepFriesianBiomeTypes), getSheepLootTable(ramFriesian));
            }
            if(registerFemales) {
                EntityEweFriesian eweFriesian = new EntityEweFriesian(world);
                registerMob(eweFriesian, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepFriesianBiomeTypes), getSheepLootTable(eweFriesian));
            }
        }

        if(jerConfig.enableSheepJacob) {
            if(registerMales) {
                EntityRamJacob ramJacob = new EntityRamJacob(world);
                registerMob(ramJacob, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepJacobBiomeTypes), getSheepLootTable(ramJacob));
            }
            if(registerFemales) {
                EntityEweJacob eweJacob = new EntityEweJacob(world);
                registerMob(eweJacob, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepJacobBiomeTypes), getSheepLootTable(eweJacob));
            }
        }

        if(jerConfig.enableSheepMerino) {
            if(registerMales) {
                EntityRamMerino ramMerino = new EntityRamMerino(world);
                registerMob(ramMerino, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepMerinoBiomeTypes), getSheepLootTable(ramMerino));
            }
            if(registerFemales) {
                EntityEweMerino eweMerino = new EntityEweMerino(world);
                registerMob(eweMerino, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepMerinoBiomeTypes), getSheepLootTable(eweMerino));
            }
        }

        if(jerConfig.enableSheepSuffolk) {
            if(registerMales) {
                EntityRamSuffolk ramSuffolk = new EntityRamSuffolk(world);
                registerMob(ramSuffolk, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepSuffolkBiomeTypes), getSheepLootTable(ramSuffolk));
            }
            if(registerFemales) {
                EntityEweSuffolk eweSuffolk = new EntityEweSuffolk(world);
                registerMob(eweSuffolk, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(FarmConfig.settings.spawning_and_breeding.sheepSuffolkBiomeTypes), getSheepLootTable(eweSuffolk));
            }
        }

        if(registerMales) {
            registerRenderHook(EntityRamBase.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.25, 1.25, 1.25);
                return renderInfo;
            }));
        }
    }

    public void registerRenderOverrides() {
        //Chickens
        RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, RenderHenBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, RenderHenBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, RenderHenBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class, RenderHenBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, RenderHenBaseJER.FACTORY);

        //Pigs
        RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, RenderHogDurocJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, RenderSowDurocJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, RenderHogHampshireJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, RenderSowHampshireJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, RenderHogLargeBlackJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, RenderSowLargeBlackJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, RenderSowLargeWhiteJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, RenderHogLargeWhiteJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, RenderHogOldSpotJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, RenderSowOldSpotJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, RenderHogYorkshireJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, RenderSowYorkshireJER.FACTORY);
    }

    protected ResourceLocation getChickenLootTable(EntityAnimaniaChicken entity) {
        return entity instanceof EntityChickBase ? null : (entity.type.isPrime ? new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "chicken_prime") : new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "chicken_regular"));
    }

    protected ResourceLocation getCowLootTable(EntityAnimaniaCow entity) {
        return entity instanceof EntityCalfBase ? null : (entity.cowType.isPrime ? new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "cow_prime") : new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "cow_regular"));
    }

    protected ResourceLocation getGoatLootTable(EntityAnimaniaGoat entity) {
        return entity instanceof EntityKidBase ? null : (entity.goatType.isPrime ? new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "goat_prime") : new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "goat_regular"));
    }

    protected ResourceLocation getHorseLootTable(EntityAnimaniaHorse entity) {
        return entity instanceof EntityFoalBase ? null : new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "horse");
    }

    protected ResourceLocation getPigLootTable(EntityAnimaniaPig entity) {
        //I hate pigs
        boolean isPrime = false;
        if(     entity instanceof EntityHogDuroc || entity instanceof EntitySowDuroc ||
                entity instanceof EntityHogHampshire || entity instanceof EntitySowHampshire ||
                entity instanceof EntityHogLargeBlack || entity instanceof EntitySowLargeBlack ||
                entity instanceof EntityHogOldSpot || entity instanceof EntitySowOldSpot) {
            isPrime = true;
        }
        return entity instanceof EntityPigletBase ? null : (isPrime ? new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "pig_prime") : new ResourceLocation("farm/" + ModIds.ANIMANIA.MOD_ID, "pig_regular"));
    }

    protected ResourceLocation getSheepLootTable(EntityAnimaniaSheep entity) {
        return entity instanceof EntityLambBase ? null : (entity.sheepType.isPrime ? new ResourceLocation("farm/animania", "sheep_prime") : new ResourceLocation("farm/animania", "sheep_regular"));
    }
}
