package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigPizzacraft;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.tiviacz.pizzacraft.init.ModBlocks;
import com.tiviacz.pizzacraft.init.ModItems;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public class JERPizzacraft extends JERBase implements IJERIntegration {
    private JEMConfigPizzacraft.JER jerConfig = JEMConfig.PIZZACRAFT.JUST_ENOUGH_RESOURCES;

    public JERPizzacraft(boolean enableJERPlants) {
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.enableBroccoli) {
            registerPlant((Item & IPlantable) ModItems.SEED_BROCCOLI,
                    new PlantDrop(new ItemStack(ModItems.SEED_BROCCOLI), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.BROCCOLI), 1, 2));
        }

        if(jerConfig.enableCorn) {
            registerPlant((Item & IPlantable) ModItems.SEED_CORN,
                    new PlantDrop(new ItemStack(ModItems.SEED_CORN), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.CORN), 1, 2));
        }

        if(jerConfig.enableCucumber){
            registerPlant((Item & IPlantable) ModItems.SEED_CUCUMBER,
                    new PlantDrop(new ItemStack(ModItems.SEED_CUCUMBER), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.CUCUMBER), 1, 2));
        }

        if(jerConfig.enableOliveLeaves) {
            CustomPlantEntry olives = new CustomPlantEntry(
                    new ItemStack(ModBlocks.OLIVE_SAPLING),
                    ModBlocks.OLIVE_LEAVES_GROWING.getDefaultState(),
                    new PlantDrop(new ItemStack(ModItems.OLIVE), 1, 2),
                    new PlantDrop(new ItemStack(ModItems.BLACK_OLIVE), 1, 2)
            );
            olives.setSoil(Blocks.AIR.getDefaultState());
            registerCustomPlant(olives);
        }

        if(jerConfig.enableOnion) {
            registerPlant((Item & IPlantable) ModItems.SEED_ONION,
                    new PlantDrop(new ItemStack(ModItems.SEED_ONION), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.ONION), 1, 2));
        }

        if(jerConfig.enablePepper) {
            registerPlant((Item & IPlantable) ModItems.SEED_PEPPER,
                    new PlantDrop(new ItemStack(ModItems.SEED_PEPPER), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.PEPPER), 1, 2));
        }

        if(jerConfig.enablePineapple) {
            registerPlant((Item & IPlantable) ModItems.SEED_PINEAPPLE,
                    new PlantDrop(new ItemStack(ModItems.SEED_PINEAPPLE), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.PINEAPPLE), 1, 2));
        }

        if(jerConfig.enableTomato) {
            registerPlant((Item & IPlantable) ModItems.SEED_TOMATO,
                    new PlantDrop(new ItemStack(ModItems.SEED_TOMATO), 0, 3),
                    new PlantDrop(new ItemStack(ModItems.TOMATO), 1, 2));
        }
    }
}
