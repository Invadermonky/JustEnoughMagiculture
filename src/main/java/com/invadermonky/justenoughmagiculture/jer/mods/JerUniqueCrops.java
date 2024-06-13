package com.invadermonky.justenoughmagiculture.jer.mods;

import com.bafomdad.uniquecrops.core.UCConfig;
import com.bafomdad.uniquecrops.crops.Musica;
import com.bafomdad.uniquecrops.crops.Normal;
import com.bafomdad.uniquecrops.init.UCItems;
import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JerUniqueCrops extends AbstractJerIntegration {
    private static JerUniqueCrops instance;

    public static JerUniqueCrops getInstance() {
        return instance == null ? instance = new JerUniqueCrops() : instance;
    }

    @Override
    public void initJerPlants() {
        if(!ConfigHandlerJEM.jer_crops.unique_crops)
            return;

        //=================================================================================
        //Unique Crops
        //Abstract
        if(UCConfig.cropAbstract) {
            JERIntegration.plantRegistry.register((Item & IPlantable) UCItems.seedsAbstract, new PlantDrop(getUCGeneric(22), 1, 1));
        }
        //Artisia
        if(UCConfig.cropArtisia) {
            registerUCCrop(UCItems.seedsArtisia, new ItemStack(Blocks.CRAFTING_TABLE));
        }
        //Cinderbella
        if(UCConfig.cropCinderbella) {
            registerUCCrop(UCItems.seedsCinderbella, 14);
        }
        //Cobblonia
        if(UCConfig.cropCobblonia) {
            registerUCCrop(UCItems.seedsCobblonia, new ItemStack(Blocks.COBBLESTONE));
        }
        //COllis
        if(UCConfig.cropCollis) {
            registerUCCrop(UCItems.seedsCollis, 6);
        }
        //Devil Snare
        if(UCConfig.cropDevilsnare) {
            registerUCCrop(UCItems.seedsDevilsnare, new ItemStack(Items.STICK));
        }
        //Dirigible
        if(UCConfig.cropDirigible) {
            registerUCCrop(UCItems.seedsDirigible, 2);
        }
        //Dyeius
        if(UCConfig.cropDyeius) {
            registerUCCrop(UCItems.seedsDyeius, new ItemStack(Items.DYE, 1, 0), 1, 3);
        }
        //Enderlily
        if(UCConfig.cropEnderlily) {
            registerUCCrop(UCItems.seedsEnderlily, 5, 2, 4);
        }
        //EULA
        if(UCConfig.cropEula) {
            registerUCCrop(UCItems.seedsEula, 23);
        }
        //Feroxia
        if(UCConfig.cropFeroxia) {
            registerUCCrop(UCItems.seedsFeroxia, 9, 2, 5);
        }
        //Invisibilia
        if(UCConfig.cropInvisibilia) {
            registerUCCrop(UCItems.seedsInvisibilia, 11, 1, 2);
        }
        //Knowledge
        if(UCConfig.cropKnowledge) {
            registerUCCrop(UCItems.seedsKnowledge, 1);
        }
        //Malleatoris
        if(UCConfig.cropMalleatoris) {
            registerUCCrop(UCItems.seedsMalleatoris, new ItemStack(Blocks.ANVIL));
        }
        //Mary Jane
        if(UCConfig.cropMaryjane) {
            registerUCCrop(UCItems.seedsMaryjane, 3);
        }
        //Merlinia
        if(UCConfig.cropMerlinia) {
            registerUCCrop(UCItems.seedsMerlinia, 4);
        }
        //Millennium
        if(UCConfig.cropMillennium) {
            registerUCCrop(UCItems.seedsMillennium, 17);
        }
        //Musica
        if(UCConfig.cropMusica) {
            try {
                List<PlantDrop> drops = new ArrayList<>();

                Field recordListField = Musica.class.getDeclaredField("recordlist");
                recordListField.setAccessible(true);
                Item[] recordList = (Item[]) recordListField.get(Musica.class);

                for (Item record : recordList) {
                    float chance = 1.0f / recordList.length;
                    drops.add(new PlantDrop(new ItemStack(record), chance));
                }
                JERIntegration.plantRegistry.register((Item & IPlantable) UCItems.seedsMusica, drops.toArray(new PlantDrop[0]));
            } catch (Exception e) {
                LogHelper.error("Failed to register Unique Crops " + UCItems.seedsMusica.toString());
            }
        }
        //Normal
        if(UCConfig.cropNormal) {
            try {
                List<PlantDrop> drops = new ArrayList<>();

                Field recordListField = Normal.class.getDeclaredField("croplist");
                recordListField.setAccessible(true);
                Item[] cropList = (Item[]) recordListField.get(Normal.class);

                for (Item crop : cropList) {
                    float chance = 1.0f / cropList.length;
                    drops.add(new PlantDrop(new ItemStack(crop), chance));
                }
                JERIntegration.plantRegistry.register((Item & IPlantable) UCItems.seedsNormal, drops.toArray(new PlantDrop[0]));
            } catch (Exception e) {
                LogHelper.error("Failed to register Unique Crops " + UCItems.seedsNormal);
            }
        }
        //Petramia
        if(UCConfig.cropPetramia) {
            registerUCCrop(UCItems.seedsPetramia, new ItemStack(Blocks.OBSIDIAN));
        }
        //Pixelsius
        if(UCConfig.cropPixelsius) {
            registerUCCrop(UCItems.seedsPixelsius, 26);
        }
        //Precision
        if(UCConfig.cropPrecision) {
            registerUCCrop(UCItems.seedsPrecision, 7);
        }
        //Wafflonia
        if(UCConfig.cropWafflonia) {
            registerUCCrop(UCItems.seedsWafflonia, new ItemStack(UCItems.waffle));
        }
        //Weeping Bells
        if(UCConfig.cropWeepingbells) {
            registerUCCrop(UCItems.seedsWeepingbells, 15);
        }
    }

    //=================================================================================
    // Helper methods go below here.

    private void registerUCCrop(Item seed, ItemStack drop, int minDrop, int maxDrop) {
        if(seed instanceof IPlantable) {
            PlantDrop[] drops = new PlantDrop[2];
            drops[0] = new PlantDrop(drop, minDrop, maxDrop);
            drops[1] = new PlantDrop(new ItemStack(seed), 1, 1);
            JERIntegration.plantRegistry.register((Item & IPlantable) seed, drops);
        }
    }

    private void registerUCCrop(Item seed, ItemStack drop) {
        registerUCCrop(seed, drop, 1, 1);
    }

    private void registerUCCrop(Item seed, int dropMeta, int minDrop, int maxDrop) {
        registerUCCrop(seed, getUCGeneric(dropMeta), minDrop, maxDrop);
    }

    private void registerUCCrop(Item seed, int dropMeta) {
        registerUCCrop(seed, dropMeta, 1, 1);
    }

    private ItemStack getUCGeneric(int meta) {
        return new ItemStack(UCItems.generic, 1, meta);
    }
}
