package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigHarvestcraft;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.plant.CustomPlantEntry;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.blocks.growables.BlockPamCrop;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public class JERHarvestcraft extends JERBase implements IJERIntegration {
    private final JEMConfigHarvestcraft.JER jerConfig = JEMConfig.HARVESTCRAFT.JUST_ENOUGH_RESOURCES;
    private static JERHarvestcraft instance;

    private JERHarvestcraft() {}

    public JERHarvestcraft(boolean enableJERPlants) {
        if(enableJERPlants) registerModPlants();
    }

    public static JERHarvestcraft getInstance() {
        return instance == null ? instance = new JERHarvestcraft() : instance;
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.enableCrops) registerHCCrops();
        if(jerConfig.enableFruit) registerHCFruit();
        if(jerConfig.enableHarvestLogs) registerHCHarvestLogs();
    }

    private void registerHCCrops() {
        for(BlockPamCrop crop : CropRegistry.getCrops().values()) {
            Item seeds = crop.getSeed();
            Item food = crop.getCrop();
            PlantDrop[] drops;

            if(seeds == food) {
                drops = new PlantDrop[] {new PlantDrop(new ItemStack(seeds), 1, 4)};
            } else {
                drops = new PlantDrop[] {
                        new PlantDrop(new ItemStack(seeds), 1, 1),
                        new PlantDrop(new ItemStack(food), 0, 2)
                };
            }

            if(seeds instanceof IPlantable)
                registerPlant((Item & IPlantable) seeds, drops);
            else
                registerPlant(new ItemStack(seeds), drops);
        }
    }

    private void registerHCFruit() {
        for(BlockPamFruit fruit : FruitRegistry.fruits) {
            CustomPlantEntry entry = new CustomPlantEntry(
                    new ItemStack(fruit.getSapling()),
                    fruit.getDefaultState(),
                    new PlantDrop(new ItemStack(fruit.getFruitItem()), 1, 1)
            );
            entry.setSoil(Blocks.AIR.getDefaultState());
            registerCustomPlant(entry);
        }
    }

    private void registerHCHarvestLogs() {
        for(BlockPamFruitLog log : FruitRegistry.logs.values()) {
            CustomPlantEntry entry = new CustomPlantEntry(
                    new ItemStack(log.getSapling()),
                    log.getDefaultState(),
                    new PlantDrop(new ItemStack(log.getFruitItem()), 1, 1)
            );
            entry.setSoil(log.getDefaultState());
            registerCustomPlant(entry);
        }
    }
}
