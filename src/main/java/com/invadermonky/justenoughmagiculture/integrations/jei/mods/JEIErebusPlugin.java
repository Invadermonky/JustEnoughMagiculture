package com.invadermonky.justenoughmagiculture.integrations.jei.mods;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.composter.ComposterCategory;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.composter.ComposterWrapper;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.offeringtable.OfferingAltarCategory;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.offeringtable.OfferingAltarWrapper;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.smoothiemaker.SmoothieMakerCategory;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.smoothiemaker.SmoothieMakerWrapper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import erebus.ModBlocks;
import erebus.recipes.ComposterRegistry;
import erebus.recipes.OfferingAltarRecipe;
import erebus.recipes.SmoothieMakerRecipe;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class JEIErebusPlugin {
    private static JEIErebusPlugin instance;
    private final boolean canLoad =  (!ModIds.MORETWEAKER.isLoaded || JEMConfig.EREBUS.JUST_ENOUGH_ITEMS.forceLoadCategories);

    public static final String EREBUS_COMPOSTER = JustEnoughMagiculture.MOD_ID + ".erebus.composter";
    public static final String EREBUS_OFFERING_ALTAR = JustEnoughMagiculture.MOD_ID + ".erebus.offering_altar";
    public static final String EREBUS_SMOOTHIE_MAKER = JustEnoughMagiculture.MOD_ID + ".erebus.smoothie_maker";

    public static JEIErebusPlugin getInstance() {
        return instance != null ? instance : (instance = new JEIErebusPlugin());
    }

    public void register(IModRegistry registry) {
        if(canLoad) {
            if(JEMConfig.EREBUS.JUST_ENOUGH_ITEMS.enableCompostCategory) {
                registry.addRecipeCatalyst(new ItemStack(ModBlocks.COMPOSTER), EREBUS_COMPOSTER);
                registry.handleRecipes(ItemStack.class, ComposterWrapper::new, EREBUS_COMPOSTER);
                NonNullList<ItemStack> compost = NonNullList.create();
                IForgeRegistry<Item> itemRegistry = GameRegistry.findRegistry(Item.class);
                for(Item item : itemRegistry.getValuesCollection()) {
                    CreativeTabs tab = item.getCreativeTab();
                    if(tab != null) {
                        item.getSubItems(tab, compost);
                    }
                }
                compost.removeIf((stack) -> {
                    ItemStack compostable = ComposterRegistry.isCompostable(stack);
                    return compostable == null || stack.isEmpty();
                });
                registry.addRecipes(compost, EREBUS_COMPOSTER);
            }

            if(JEMConfig.EREBUS.JUST_ENOUGH_ITEMS.enableOfferingAltarCategory) {
                registry.addRecipeCatalyst(new ItemStack(ModBlocks.ALTAR_OFFERING), EREBUS_OFFERING_ALTAR);
                registry.handleRecipes(OfferingAltarRecipe.class, OfferingAltarWrapper::new, EREBUS_OFFERING_ALTAR);
                registry.addRecipes(OfferingAltarRecipe.getRecipeList(), EREBUS_OFFERING_ALTAR);
            }

            if(JEMConfig.EREBUS.JUST_ENOUGH_ITEMS.enableSmoothieCategory) {
                registry.addRecipeCatalyst(new ItemStack(ModBlocks.SMOOTHIE_MAKER), EREBUS_SMOOTHIE_MAKER);
                registry.handleRecipes(SmoothieMakerRecipe.class, SmoothieMakerWrapper::new, EREBUS_SMOOTHIE_MAKER);
                registry.addRecipes(SmoothieMakerRecipe.getRecipeList(), EREBUS_SMOOTHIE_MAKER);
            }
        }
    }

    public void registerCategories(IRecipeCategoryRegistration registry) {
        if(canLoad) {
            registry.addRecipeCategories(new ComposterCategory(), new OfferingAltarCategory(), new SmoothieMakerCategory());
        }
    }
}
