package com.invadermonky.justenoughmagiculture.init;

import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.invadermonky.justenoughmagiculture.jer.mods.*;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import gnu.trove.map.hash.THashMap;
import jeresources.api.*;
import net.minecraft.world.World;

import java.lang.reflect.Method;

public class JERIntegration {
    @JERPlugin
    public static IJERAPI jerapi;

    public static World jerWorld;
    public static IDungeonRegistry dungeonRegistry;
    public static IMobRegistry mobRegistry;
    public static IPlantRegistry plantRegistry;

    private static THashMap<ModIds,Class<? extends AbstractJerIntegration>> jerIntegrations = new THashMap<>(ModIds.values().length);

    public static void init() {
        jerWorld = jerapi.getWorld();
        dungeonRegistry = jerapi.getDungeonRegistry();
        mobRegistry = jerapi.getMobRegistry();
        plantRegistry = jerapi.getPlantRegistry();

        jerIntegrations.put(ModIds.ad_inferos, JerAdInferos.class);
        jerIntegrations.put(ModIds.ars_magica, JerArsMagica.class);
        jerIntegrations.put(ModIds.astral_sorcery, JerAstralSorcery.class);
        jerIntegrations.put(ModIds.deadly_monsters, JerDeadlyMonsters.class);
        jerIntegrations.put(ModIds.embers, JerEmbers.class);
        jerIntegrations.put(ModIds.ender_zoo, JerEnderZoo.class);
        jerIntegrations.put(ModIds.harvestcraft, JerHarvestcraft.class);
        jerIntegrations.put(ModIds.mystical_agriculture, JerMysticalAgriculture.class);
        jerIntegrations.put(ModIds.thaumcraft, JerThaumcraft.class);
        jerIntegrations.put(ModIds.unique_crops, JerUniqueCrops.class);

        initDungeons();
        initEntities();
        initPlants();
    }

    private static void initDungeons() {
        jerIntegrations.forEach((mod,clazz) -> {
            try {
                if(mod.isLoaded) {
                    LogHelper.debug("Loading JER Dungeon Entries for " + mod.modId);
                    AbstractJerIntegration jerIntegration = getInstance(clazz);
                    if (jerIntegration != null) {
                        jerIntegration.initJerDungeons();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initEntities() {
        jerIntegrations.forEach((mod,clazz) -> {
            try {
                if(mod.isLoaded) {
                    LogHelper.debug("Loading JER Entity Entries for " + mod.modId);
                    AbstractJerIntegration jerIntegration = getInstance(clazz);
                    if (jerIntegration != null) {
                        jerIntegration.initJerEntities();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initPlants() {
        jerIntegrations.forEach((mod,clazz) -> {
            try {
                if(mod.isLoaded) {
                    LogHelper.debug("Loading JER Plant Entries for " + mod.modId);
                    AbstractJerIntegration jerIntegration = getInstance(clazz);
                    if (jerIntegration != null) {
                        jerIntegration.initJerPlants();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static AbstractJerIntegration getInstance(Class<? extends AbstractJerIntegration> clazz) {
        try {
            Method method = clazz.getMethod("getInstance");
            return  (AbstractJerIntegration) method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
