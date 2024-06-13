package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.blocks.growables.BlockPamCrop;
import com.pam.harvestcraft.config.ConfigHandler;
import jeresources.api.drop.PlantDrop;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import java.lang.reflect.Field;

public class JerHarvestcraft extends AbstractJerIntegration {
    private static JerHarvestcraft instance;

    public static JerHarvestcraft getInstance() {
        return instance == null ? instance = new JerHarvestcraft() : instance;
    }

    @Override
    public void initJerPlants() {
        /* TODO: Harvestcraft is broken. It's here if I want to try to fix it but honestly I don't care.
        if(ConfigHandlerJEM.jer_crops.harvestcraft)
            return;

        //=================================================================================
        //Harvestcraft
        for(BlockPamCrop crop : CropRegistry.getCrops().values()) {
            try {
                Field seedField = crop.getClass().getDeclaredField("seed");
                seedField.setAccessible(true);
                Item seed = (Item) seedField.get(crop);

                Field foodField = crop.getClass().getField("food");
                foodField.setAccessible(true);
                Item food = (Item) foodField.get(crop);
                PlantDrop[] drops;

                if(ConfigHandler.cropsdropSeeds) {
                    drops = new PlantDrop[2];
                    drops[0] = new PlantDrop(new ItemStack(food), 1, 1);
                    drops[1] = new PlantDrop(new ItemStack(seed), 0, 2);
                } else {
                    drops = new PlantDrop[1];
                    drops[0] = new PlantDrop(new ItemStack(food), 1, 3);
                }
                if(seed instanceof IPlantable) {
                    JERIntegration.plantRegistry.register((Item & IPlantable) seed, drops);
                } else {
                    JERIntegration.plantRegistry.register(new ItemStack(seed), drops);
                }
            } catch (Exception e) {
                LogHelper.error("Failed to register Harvestcraft " + crop.name);
            }
        }
        */
    }
}
