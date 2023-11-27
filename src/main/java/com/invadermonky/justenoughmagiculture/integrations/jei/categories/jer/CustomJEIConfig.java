package com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.init.InitIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.plant.CustomPlantWrapper;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.CustomVillagerWrapper;
import com.invadermonky.justenoughmagiculture.registry.CustomPlantRegistry;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import jeresources.entry.PlantEntry;
import jeresources.entry.VillagerEntry;
import jeresources.jei.JEIConfig;
import jeresources.jei.plant.PlantWrapper;
import jeresources.jei.villager.VillagerWrapper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.collect.ListMultiMap;
import mezz.jei.collect.SetMultiMap;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomJEIConfig {
    /**
     * Loaded using ASM. Inserted at the end of jeresources.jei.JEIConfig#register.
     *
     * @param registry JEI IModRegistry post-JER register.
     */
    public static void injectRegister(IModRegistry registry) {
        LogHelper.info("Registering Custom JER entries.");

        if(registerCustomPlants(registry)) {
            registry.addRecipes(CustomPlantRegistry.getInstance().getAllPlants(), JEIConfig.PLANT);
        } else {
            LogHelper.warn("failed to register custom plants.");
        }

        if(registerCustomVillagers(registry)) {
            removeBuggedVillagers(registry);
            registry.addRecipes(CustomVillagerRegistry.getInstance().getVillagers(), JEIConfig.VILLAGER);
        } else {
            LogHelper.warn("Failed to register custom villagers.");
        }

        InitIntegration.lateInit();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public static boolean registerCustomPlants(IModRegistry registry) {
        try {
            Field recipeHandlerClassesField = registry.getClass().getDeclaredField("recipeHandlerClasses");
            Field recipeHandlersField = registry.getClass().getDeclaredField("recipeHandlers");

            recipeHandlerClassesField.setAccessible(true);
            recipeHandlersField.setAccessible(true);

            SetMultiMap<String, Class<?>> recipeHandlerClasses = (SetMultiMap<String, Class<?>>) recipeHandlerClassesField.get(registry);
            ListMultiMap<String, IRecipeHandler<?>> recipeHandlers = (ListMultiMap<String, IRecipeHandler<?>>) recipeHandlersField.get(registry);

            if(!recipeHandlerClasses.contains(JEIConfig.PLANT, PlantEntry.class)) {
                throw new NoSuchFieldException();
            }

            @ParametersAreNonnullByDefault
            IRecipeHandler<PlantEntry> recipeHandler = new IRecipeHandler<PlantEntry>() {
                @Override
                public Class<PlantEntry> getRecipeClass() {
                    return PlantEntry.class;
                }

                @Override
                public String getRecipeCategoryUid(PlantEntry plantEntry) {
                    return JEIConfig.PLANT;
                }

                @Override
                public IRecipeWrapper getRecipeWrapper(PlantEntry plantEntry) {
                    return new CustomPlantWrapper(plantEntry);
                }

                @Override
                public boolean isRecipeValid(PlantEntry plantEntry) {
                    return true;
                }
            };

            List<IRecipeHandler<?>> plantHandlers = recipeHandlers.get(JEIConfig.PLANT);

            if(plantHandlers.size() > 0) {
                plantHandlers.removeIf(handler -> handler.getRecipeWrapper(null) instanceof PlantWrapper);
            }

            plantHandlers.add(recipeHandler);
            return true;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            LogHelper.error("Could not access ModRegistry, custom plants will not work properly.");
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public static boolean registerCustomVillagers(IModRegistry registry) {
        try {
            Field recipeHandlerClassesField = registry.getClass().getDeclaredField("recipeHandlerClasses");
            Field recipeHandlersField = registry.getClass().getDeclaredField("recipeHandlers");

            recipeHandlerClassesField.setAccessible(true);
            recipeHandlersField.setAccessible(true);

            SetMultiMap<String, Class<?>> recipeHandlerClasses = (SetMultiMap<String, Class<?>>) recipeHandlerClassesField.get(registry);
            ListMultiMap<String, IRecipeHandler<?>> recipeHandlers = (ListMultiMap<String, IRecipeHandler<?>>) recipeHandlersField.get(registry);

            if(!recipeHandlerClasses.contains(JEIConfig.VILLAGER, VillagerEntry.class)) {
                throw new NoSuchFieldException();
            }

            IRecipeHandler<VillagerEntry> recipeHandler = new IRecipeHandler<VillagerEntry>() {
                @Override
                public Class<VillagerEntry> getRecipeClass() {
                    return VillagerEntry.class;
                }

                @Override
                public String getRecipeCategoryUid(VillagerEntry entry) {
                    return JEIConfig.VILLAGER;
                }

                @Override
                public IRecipeWrapper getRecipeWrapper(VillagerEntry entry) {
                    return new CustomVillagerWrapper(entry);
                }

                @Override
                public boolean isRecipeValid(VillagerEntry entry) {
                    return true;
                }
            };

            List<IRecipeHandler<?>> villagerHandlers = recipeHandlers.get(JEIConfig.VILLAGER);

            if(villagerHandlers.size() > 0) {
                villagerHandlers.removeIf(handler -> handler.getRecipeWrapper(null) instanceof VillagerWrapper);
            }

            villagerHandlers.add(recipeHandler);
            return true;

        } catch(NoSuchFieldException | IllegalAccessException e) {
            LogHelper.error("Could not access ModRegistry, custom villager trades will not work properly.");
        }
        return false;
    }

    public static void removeBuggedVillagers(IModRegistry registry) {
        try {
            Field recipesField = registry.getClass().getDeclaredField("recipes");
            recipesField.setAccessible(true);
            ListMultiMap<String,Object> recipes = (ListMultiMap<String,Object>) recipesField.get(registry);

            if(recipes.containsKey(JEIConfig.VILLAGER)) {
                Object villagerEntriesObj = recipes.get(JEIConfig.VILLAGER);

                ArrayList<VillagerEntry> villagerEntries = (ArrayList<VillagerEntry>) villagerEntriesObj;
                ArrayList<String> removeVillagers = new ArrayList<>();
                ArrayList<VillagerEntry> toRemove = new ArrayList<>();

                if(ModIds.RATS.isLoaded && JEMConfig.RATS.fixJERVillagers) {
                    removeVillagers.addAll(Arrays.asList("pet_shop_owner", "plague_doctor"));
                }

                for(VillagerEntry entry : villagerEntries) {
                    if(removeVillagers.contains(entry.getName()))
                        toRemove.add(entry);
                }
                villagerEntries.removeAll(toRemove);
            }
        } catch (Exception e) {
            LogHelper.warn("Failed to remove erroring villager trades. Some villagers may not display correctly.");
            e.printStackTrace();
        }
    }
}
