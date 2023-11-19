package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.github.voxelfriend.rusticthaumaturgy.common.items.ModItemsRT;
import com.github.voxelfriend.rusticthaumaturgy.configuration.RTConfiguration;
import com.github.voxelfriend.rusticthaumaturgy.core.RusticThaumaturgy;
import com.github.voxelfriend.rusticthaumaturgy.reference.Names;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigRusticThaumaturgy;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigThaumicAugmentation;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.drop.PlantDrop;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public class JERRusticThaumaturgy extends JERBase implements IJERIntegration {
    private static final JEMConfigRusticThaumaturgy.JER jerConfig = JEMConfig.RUSTIC_THAUMATURGY.JUST_ENOUGH_RESOURCES;

    public JERRusticThaumaturgy(boolean enableJERPlants) {
        if(enableJERPlants) registerModPlants();
    }

    @Override
    public void registerModPlants() {
        if(jerConfig.enableCindermote) {
            registerPlant((Item & IPlantable) ModItemsRT.CINDERMOTE_SEEDS,
                    new PlantDrop(new ItemStack(ModItemsRT.CINDERMOTE_SEEDS), 0, 2),
                    new PlantDrop(new ItemStack(ModItemsRT.CINDERMOTE), 1, 2)
            );
        }

        if(jerConfig.enableShimmerpetal) {
            registerPlant((Item & IPlantable) ModItemsRT.SHIMMERPETAL_BULB,
                    new PlantDrop(new ItemStack(ModItemsRT.SHIMMERPETAL_BULB), 0, 2),
                    new PlantDrop(new ItemStack(ModItemsRT.SHIMMERPETAL), 1, 2)
            );
        }

        if(jerConfig.enableViscap) {
            registerPlant((Item & IPlantable) ModItemsRT.VISCAP_SPORES,
                    new PlantDrop(new ItemStack(ModItemsRT.VISCAP_SPORES), 0, 2),
                    new PlantDrop(new ItemStack(ModItemsRT.VISCAP), 1, 2)
            );
        }
    }
}
