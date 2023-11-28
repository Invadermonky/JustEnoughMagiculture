package com.invadermonky.justenoughmagiculture.integrations.jei;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.init.InitIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagCategory;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagEntry;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagWrapper;
import com.invadermonky.justenoughmagiculture.integrations.jer.mods.JERAtum;
import com.invadermonky.justenoughmagiculture.registry.LootBagRegistry;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEICategories implements IModPlugin {
    public static final String LOOT_BAG = JustEnoughMagiculture.MOD_ID + ".loot_bag";
    private static IJeiHelpers jeiHelpers;
    private static IJeiRuntime jeiRuntime;

    @Override
    public void register(IModRegistry registry) {
        InitIntegration.jeiInit();

        if(JEMConfig.MODULE_JEI.enableLootBagCategory) {
            registry.handleRecipes(LootBagEntry.class, LootBagWrapper::new, LOOT_BAG);
            registry.addRecipes(LootBagRegistry.getInstance().getAllLootBags(), LOOT_BAG);
        }

        registerInfoPages(registry);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JEICategories.jeiRuntime = jeiRuntime;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        JEICategories.jeiHelpers = registry.getJeiHelpers();
        registry.addRecipeCategories(new LootBagCategory());
    }

    public static IJeiHelpers getJeiHelpers() {
        return JEICategories.jeiHelpers;
    }

    private void registerInfoPages(IModRegistry registry) {
        if(ModIds.ATUM.isLoaded && JEMConfig.ATUM.enableJEIInfoPages) {
            JERAtum.getInstance().registerJEIInfoPages(registry);
        }
    }
}
