package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigFutureMC;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.plant.CustomPlantEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thedarkcolour.futuremc.config.FConfig;
import thedarkcolour.futuremc.entity.fish.cod.EntityCod;
import thedarkcolour.futuremc.entity.fish.pufferfish.EntityPufferfish;
import thedarkcolour.futuremc.entity.fish.salmon.EntitySalmon;
import thedarkcolour.futuremc.entity.fish.tropical.EntityTropicalFish;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import static com.invadermonky.justenoughmagiculture.util.ModIds.FUTUREMC;

public class JERFutureMC extends JERBase implements IJERIntegration {
    private static final JEMConfigFutureMC.JER jerConfig = JEMConfig.FUTURE_MC.JUST_ENOUGH_RESOURCES;

    public JERFutureMC(boolean enableJERMobs, boolean enableJERPlants) {
        if(enableJERMobs) registerModEntities();
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModEntities() {
        FConfig.UpdateAquatic.FishConfig fish = FConfig.INSTANCE.getUpdateAquatic().fish;

        if(jerConfig.JER_MOBS.enableCod && fish.cod.enabled) {
            ResourceLocation codTable = new ResourceLocation(FUTUREMC.MOD_ID, "entities/cod");
            registerMob(new EntityCod(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(FConfig.INSTANCE.getUpdateAquatic().fish.cod.validBiomes), codTable);
        }

        if(jerConfig.JER_MOBS.enablePufferfish && fish.pufferfish.enabled) {
            ResourceLocation pufferfishTable = new ResourceLocation(FUTUREMC.MOD_ID, "entities/pufferfish");
            registerMob(new EntityPufferfish(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(FConfig.INSTANCE.getUpdateAquatic().fish.pufferfish.validBiomes), pufferfishTable);
        }

        if(jerConfig.JER_MOBS.enableSalmon && fish.salmon.enabled) {
            ResourceLocation salmonTable = new ResourceLocation(FUTUREMC.MOD_ID, "entities/salmon");
            registerMob(new EntitySalmon(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(FConfig.INSTANCE.getUpdateAquatic().fish.salmon.validBiomes), salmonTable);
        }

        if(jerConfig.JER_MOBS.enableTropicalFish && fish.tropicalFish.enabled) {
            EntityTropicalFish tropicalFish = new EntityTropicalFish(world);
            tropicalFish.generateVariant();
            ResourceLocation tropicalFishTable = new ResourceLocation(FUTUREMC.MOD_ID, "entities/tropical_fish");
            registerMob(tropicalFish, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(FConfig.INSTANCE.getUpdateAquatic().fish.tropicalFish.validBiomes), tropicalFishTable);
        }
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.JER_PLANTS.enableSweetBerry && FConfig.INSTANCE.getVillageAndPillage().sweetBerryBush.enabled) {
            CustomPlantEntry sweetBerryEntry = new CustomPlantEntry(
                    new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()),
                    FBlocks.INSTANCE.getSWEET_BERRY_BUSH().getDefaultState(),
                    new PlantDrop(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), 1, 3));
            sweetBerryEntry.setSoil(Blocks.GRASS.getDefaultState());
            registerCustomPlant(sweetBerryEntry);
        }
    }
}
