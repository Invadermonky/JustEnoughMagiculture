package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigMinecraft;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomPlantRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.Reference;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.util.LootTableHelper;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class JERMinecraft extends JERBase implements IJERIntegration {
    private JEMConfigMinecraft.JER jerConfig = JEMConfig.MINECRAFT.JUST_ENOUGH_RESOURCES;

    public JERMinecraft(boolean enableJERMobs, boolean enableJERPlants) {
        if(enableJERMobs) registerModEntities();
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.JER_MOBS.enablePolarBear) {
            registerMob(new EntityPolarBear(world), LightLevel.any, 1, 3, BiomeHelper.getBiomeNamesForBiomes(Biomes.ICE_PLAINS, Biomes.MUTATED_ICE_FLATS), LootTableList.ENTITIES_POLAR_BEAR);
        }

        if(jerConfig.JER_MOBS.enableWither) {
            List<LootDrop> drops = LootTableHelper.toDrops(world, Reference.FakeTables.WITHER_FAKE_TABLE);
            registerMob(new EntityWither(world), LightLevel.any, drops.toArray(new LootDrop[0]));
        }
    }

    @Override
    public void registerModPlants() {
        CustomPlantRegistry registry = CustomPlantRegistry.getInstance();

        if(jerConfig.JER_PLANTS.enableBeetroot && Items.BEETROOT_SEEDS instanceof IPlantable) {
            registry.registerPlant(new PlantEntry(
                    (Item & IPlantable) Items.BEETROOT_SEEDS,
                    new PlantDrop(new ItemStack(Items.BEETROOT_SEEDS), 0, 3),
                    new PlantDrop(new ItemStack(Items.BEETROOT), 1, 1)
            ));
        }

        if(jerConfig.JER_PLANTS.enableNetherWart && Items.NETHER_WART instanceof IPlantable) {
            PlantEntry netherWart = new PlantEntry((Item & IPlantable) Items.NETHER_WART,
                    new PlantDrop(new ItemStack(Items.NETHER_WART), 2, 4));
            netherWart.setSoil(Blocks.SOUL_SAND.getDefaultState());
            registry.registerPlant(netherWart);
        }
    }
}
