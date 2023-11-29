package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigRustic;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rustic.common.blocks.ModBlocks;
import rustic.common.blocks.crops.BlockLeavesApple;
import rustic.common.blocks.crops.Herbs;
import rustic.common.items.ModItems;

public class JERRustic extends JERBase implements IJERIntegration {
    private static JEMConfigRustic.JER jerConfig = JEMConfig.RUSTIC.JUST_ENOUGH_RESOURCES;

    public JERRustic(boolean enableJERPlants) {
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModPlants() {
        registerCrops();
        registerHerbs();
        registerOther();
    }

    private void registerCrops() {
        if(jerConfig.enableGrapes) {
            CustomPlantEntry grapes = new CustomPlantEntry(
                    new ItemStack(ModBlocks.GRAPE_STEM),
                    new PlantDrop(new ItemStack(ModItems.GRAPES), 1, 1)
            );
            registerCustomPlant(grapes);
        }
        if(jerConfig.enableChiliPeppers) {
            CustomPlantEntry chiliPeppers = new CustomPlantEntry(
                    new ItemStack(ModItems.CHILI_PEPPER_SEEDS),
                    ModBlocks.CHILI_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(ModItems.CHILI_PEPPER), 1, 1)
            );
            registerCustomPlant(chiliPeppers);
        }
        if(jerConfig.enableTomatoes) {
            CustomPlantEntry tomatoes = new CustomPlantEntry(
                    new ItemStack(ModItems.TOMATO_SEEDS),
                    ModBlocks.TOMATO_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(ModItems.TOMATO), 1, 1)
            );
            registerCustomPlant(tomatoes);
        }
    }

    private void registerHerbs() {
        if(jerConfig.enableAloeVera) {
            CustomPlantEntry aloeVera = new CustomPlantEntry(
                    new ItemStack(Herbs.ALOE_VERA),
                    new PlantDrop(new ItemStack(Herbs.ALOE_VERA), 1, 3)
            );
            aloeVera.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(aloeVera);
        }

        if(jerConfig.enableBloodOrchid) {
            CustomPlantEntry bloodOrchid = new CustomPlantEntry(
                    new ItemStack(Herbs.BLOOD_ORCHID),
                    new PlantDrop(new ItemStack(Herbs.BLOOD_ORCHID), 1, 3)
            );
            bloodOrchid.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(bloodOrchid);
        }

        if(jerConfig.enableChamomile) {
            CustomPlantEntry chamomile = new CustomPlantEntry(
                    new ItemStack(Herbs.CHAMOMILE),
                    new PlantDrop(new ItemStack(Herbs.CHAMOMILE), 1, 3)
            );
            chamomile.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(chamomile);
        }

        if(jerConfig.enableCloudsbuff) {
            CustomPlantEntry cloudsbuff = new CustomPlantEntry(
                    new ItemStack(Herbs.CLOUDSBLUFF),
                    Herbs.CLOUDSBLUFF_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(Herbs.CLOUDSBLUFF), 1, 3)
            );
            cloudsbuff.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(cloudsbuff);
        }

        if(jerConfig.enableCohosh) {
            CustomPlantEntry cohosh = new CustomPlantEntry(
                    new ItemStack(Herbs.COHOSH),
                    new PlantDrop(new ItemStack(Herbs.COHOSH), 1, 3)
            );
            cohosh.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(cohosh);
        }

        if(jerConfig.enableCoreRoot) {
            CustomPlantEntry coreRoot = new CustomPlantEntry(
                    new ItemStack(Herbs.CORE_ROOT),
                    Herbs.CORE_ROOT_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(Herbs.CORE_ROOT), 1, 3)
            );
            coreRoot.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(coreRoot);
        }

        if(jerConfig.enableDeathstalk) {
            CustomPlantEntry deathstalk = new CustomPlantEntry(
                    new ItemStack(Herbs.DEATHSTALK),
                    new PlantDrop(new ItemStack(Herbs.DEATHSTALK), 1, 3)
            );
            deathstalk.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(deathstalk);
        }

        if(jerConfig.enableGinseng) {
            CustomPlantEntry ginseng = new CustomPlantEntry(
                    new ItemStack(Herbs.GINSENG),
                    Herbs.GINSENG_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(Herbs.GINSENG), 1, 3)
            );
            ginseng.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(ginseng);
        }

        if(jerConfig.enableHorsetail) {
            CustomPlantEntry horsetail = new CustomPlantEntry(
                    new ItemStack(Herbs.HORSETAIL),
                    new PlantDrop(new ItemStack(Herbs.HORSETAIL), 1, 3)
            );
            horsetail.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(horsetail);
        }

        if(jerConfig.enableMarshMallow) {
            CustomPlantEntry marshMallow = new CustomPlantEntry(
                    new ItemStack(Herbs.MARSH_MALLOW),
                    Herbs.MARSH_MALLOW_CROP.getDefaultState(),
                    new PlantDrop(new ItemStack(Herbs.MARSH_MALLOW), 1, 3)
            );
            marshMallow.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(marshMallow);
        }

        if(jerConfig.enableMooncap) {
            CustomPlantEntry mooncap = new CustomPlantEntry(
                    new ItemStack(Herbs.MOONCAP),
                    new PlantDrop(new ItemStack(Herbs.MOONCAP), 1, 3)
            );
            mooncap.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(mooncap);
        }

        if(jerConfig.enableWindThistle) {
            CustomPlantEntry windThistle = new CustomPlantEntry(
                    new ItemStack(Herbs.WIND_THISTLE),
                    new PlantDrop(new ItemStack(Herbs.WIND_THISTLE), 1, 3)
            );
            windThistle.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(windThistle);
        }
    }

    private void registerOther() {
        if(jerConfig.enableAppleLeaves) {
            CustomPlantEntry appleSapling = new CustomPlantEntry(
                    new ItemStack(ModBlocks.APPLE_SAPLING),
                    null,
                    BlockLeavesApple.AGE,
                    ModBlocks.APPLE_LEAVES.getDefaultState(),
                    new PlantDrop(new ItemStack(Items.APPLE), 1, 1)
            );
            appleSapling.setSoil(Blocks.AIR.getDefaultState());
            registerCustomPlant(appleSapling);
        }

        if(jerConfig.enableAppleSeeds) {
            CustomPlantEntry appleSeeds = new CustomPlantEntry(
                    new ItemStack(ModBlocks.APPLE_SEEDS),
                    new PlantDrop(new ItemStack(ModBlocks.APPLE_SAPLING), 1, 1)
            );
            appleSeeds.setSoil(Blocks.DIRT.getDefaultState());
            registerCustomPlant(appleSeeds);
        }
    }
}
