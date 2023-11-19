package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.girafi.waddles.Waddles;
import com.girafi.waddles.entity.EntityAdeliePenguin;
import com.girafi.waddles.init.PenguinRegistry;
import com.girafi.waddles.utils.BiomeDictionaryHelper;
import com.girafi.waddles.utils.ConfigurationHandler;
import com.google.common.base.CaseFormat;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigWaddles;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JERWaddles extends JERBase implements IJERIntegration {
    private final JEMConfigWaddles.JER jerConfig = JEMConfig.WADDLES.JUST_ENOUGH_RESOURCES;

    public JERWaddles(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerModEntities() {
        if(!jerConfig.enablePenguin)
            return;

        try {
            int minExp = 0;
            int maxExp = 0;
            if (ConfigurationHandler.dropExp) {
                minExp = 1;
                maxExp = 3;
            }

            ResourceLocation loot = ConfigurationHandler.dropFish ? Waddles.LOOT_ENTITIES_PENGUIN_FISH : Waddles.EMPTY;

            Field f = PenguinRegistry.class.getDeclaredField("biomes");
            f.setAccessible(true);
            Iterable<Biome> iterable = (Iterable<Biome>) f.get(new PenguinRegistry());
            List<Biome> biomes = new ArrayList<>();

            for (Biome biome : iterable) {
                biomes.add(biome);
            }

            registerMob(new EntityAdeliePenguin(world), LightLevel.any, minExp, maxExp, BiomeHelper.getBiomeNamesForBiomes(biomes.toArray(new Biome[0])), loot);
            registerRenderHook(EntityAdeliePenguin.class, (renderInfo, entity) -> {
                GlStateManager.scale(1.7, 1.7, 1.7);
                return renderInfo;
            });
        } catch (Exception ignored) {}
    }
}
