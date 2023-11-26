package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigHarvestcraft;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.blocks.blocks.BlockBaseGarden;
import com.pam.harvestcraft.blocks.growables.BlockPamCrop;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

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
        if(jerConfig.enableGardens) registerHCGardens();
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

    private void registerHCGardens() {
        BlockRegistry.gardens.forEach((name,garden) -> {
            List<PlantDrop> plantDrops = new ArrayList<>();
            int totalDrops = BlockBaseGarden.drops.get(name).size();
            float chance = (float) Math.min(HarvestCraft.config.gardendropAmount, totalDrops) / totalDrops;
            for(ItemStack stack : BlockBaseGarden.drops.get(name)) {
                plantDrops.add(new PlantDrop(stack, chance));
            }
            registerCustomPlant(new PlantEntry(new ItemStack(garden, 1, 0), plantDrops.toArray(new PlantDrop[0])));
        });
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
