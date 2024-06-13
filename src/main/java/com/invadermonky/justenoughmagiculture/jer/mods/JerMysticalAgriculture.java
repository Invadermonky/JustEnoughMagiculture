package com.invadermonky.justenoughmagiculture.jer.mods;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import jeresources.api.drop.PlantDrop;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public class JerMysticalAgriculture extends AbstractJerIntegration {
    private static JerMysticalAgriculture instance;

    public static JerMysticalAgriculture getInstance() {
        return instance == null ? instance = new JerMysticalAgriculture() : instance;
    }

    @Override
    public void initJerPlants() {
        if(!ConfigHandlerJEM.jer_crops.mystical_agriculture)
            return;

        int cropDrops = 1 + ((ModConfig.confEssenceChance > 0) ? 1 : 0);
        int seedDrops = 1 + ((ModConfig.confSeedChance > 0) ? 1 : 0);
        PlantDrop[] drops = new PlantDrop[2];

        //Tier 1
        drops[0] = new PlantDrop(new ItemStack(ModItems.itemInferiumEssence), 1, cropDrops);
        drops[1] = new PlantDrop(new ItemStack(ModItems.itemTier1InferiumSeeds), 1, seedDrops);
        JERIntegration.plantRegistry.register((Item & IPlantable) ModItems.itemTier1InferiumSeeds, drops);
        //Tier 2
        drops[0] = new PlantDrop(new ItemStack(ModItems.itemInferiumEssence), 2, cropDrops + 1);
        drops[1] = new PlantDrop(new ItemStack(ModItems.itemTier2InferiumSeeds), 1, seedDrops);
        JERIntegration.plantRegistry.register((Item & IPlantable) ModItems.itemTier2InferiumSeeds, drops);
        //Tier 3
        drops[0] = new PlantDrop(new ItemStack(ModItems.itemInferiumEssence), 3, cropDrops + 2);
        drops[1] = new PlantDrop(new ItemStack(ModItems.itemTier3InferiumSeeds), 1, seedDrops);
        JERIntegration.plantRegistry.register((Item & IPlantable) ModItems.itemTier3InferiumSeeds, drops);
        //Tier 4
        drops[0] = new PlantDrop(new ItemStack(ModItems.itemInferiumEssence), 4, cropDrops + 3);
        drops[1] = new PlantDrop(new ItemStack(ModItems.itemTier4InferiumSeeds), 1, seedDrops);
        JERIntegration.plantRegistry.register((Item & IPlantable) ModItems.itemTier4InferiumSeeds, drops);
        //Tier 5
        drops[0] = new PlantDrop(new ItemStack(ModItems.itemInferiumEssence), 5, cropDrops + 4);
        drops[1] = new PlantDrop(new ItemStack(ModItems.itemTier5InferiumSeeds), 1, seedDrops);
        JERIntegration.plantRegistry.register((Item & IPlantable) ModItems.itemTier5InferiumSeeds, drops);

        if(ModConfig.confFertilizedEssence && ModConfig.confFertilizedEssenceChance > 0) {
            drops = new PlantDrop[3];
            drops[2] = new PlantDrop(new ItemStack(ModItems.itemFertilizedEssence), 1, 1);
        }
        for(CropType.Type crop : CropType.Type.values()) {
            if(crop.isEnabled()) {
                drops[0] = new PlantDrop(new ItemStack(crop.getCrop()), 1, cropDrops);
                drops[1] = new PlantDrop(new ItemStack(crop.getSeed()), 1, seedDrops);
                if(crop.getSeed() != null) {
                    JERIntegration.plantRegistry.register((Item & IPlantable) crop.getSeed(), drops);
                }
            }
        }
    }
}
