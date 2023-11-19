package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import cofh.thermalfoundation.entity.monster.EntityBasalz;
import cofh.thermalfoundation.entity.monster.EntityBlitz;
import cofh.thermalfoundation.entity.monster.EntityBlizz;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigThermalFoundation;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMLightLevel;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import gnu.trove.set.hash.THashSet;
import jeresources.api.render.IMobRenderHook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.lang.reflect.Field;

public class JERThermalFoundation extends JERBase implements IJERIntegration {
    private final JEMConfigThermalFoundation.JER jerConfig = JEMConfig.THERMAL_FOUNDATION.JUST_ENOUGH_RESOURCES;

    public JERThermalFoundation(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableBasalzMob) registerBasalz();
        if(jerConfig.enableBlitzMob) registerBlitz();
        if(jerConfig.enableBlizzMob) registerBlizz();
    }

    protected void registerBasalz() {
        try{
            Field f = EntityBasalz.class.getDeclaredField("spawnLightLevel");
            f.setAccessible(true);
            int lightLevel = (int) f.get(EntityBasalz.class);

            THashSet<Biome> biomes = new THashSet<>();
            biomes.addAll(BiomeDictionary.getBiomes(Type.MOUNTAIN));
            biomes.addAll(BiomeDictionary.getBiomes(Type.WASTELAND));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.NETHER));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.END));

            ResourceLocation lootTable = new ResourceLocation(ModIds.THERMALFOUNDATION.MOD_ID, "entities/basalz");

            registerMob(new EntityBasalz(world), JEMLightLevel.custom(lightLevel, false), BiomeHelper.getBiomeNamesForBiomes(biomes.toArray(new Biome[0])), lootTable);
            adjustElementalRenderHook(EntityBasalz.class);
        } catch (Exception ignored) {}
    }

    protected void registerBlitz() {
        try{
            Field f = EntityBlitz.class.getDeclaredField("spawnLightLevel");
            f.setAccessible(true);
            int lightLevel = (int) f.get(EntityBlitz.class);

            THashSet<Biome> biomes = new THashSet<>();
            biomes.addAll(BiomeDictionary.getBiomes(Type.SANDY));
            biomes.addAll(BiomeDictionary.getBiomes(Type.SAVANNA));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.NETHER));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.END));

            ResourceLocation lootTable = new ResourceLocation(ModIds.THERMALFOUNDATION.MOD_ID, "entities/blitz");

            registerMob(new EntityBlitz(world), JEMLightLevel.custom(lightLevel, false), BiomeHelper.getBiomeNamesForBiomes(biomes.toArray(new Biome[0])), lootTable);
            adjustElementalRenderHook(EntityBlitz.class);
        } catch (Exception ignored) {}
    }

    private void registerBlizz() {
        try{
            Field f = EntityBlizz.class.getDeclaredField("spawnLightLevel");
            f.setAccessible(true);
            int lightLevel = (int) f.get(EntityBlizz.class);

            THashSet<Biome> biomes = new THashSet<>();
            biomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
            biomes.addAll(BiomeDictionary.getBiomes(Type.SNOWY));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.NETHER));
            biomes.removeAll(BiomeDictionary.getBiomes(Type.END));

            ResourceLocation lootTable = new ResourceLocation(ModIds.THERMALFOUNDATION.MOD_ID, "entities/blizz");

            registerMob(new EntityBlizz(world), JEMLightLevel.custom(lightLevel, false), BiomeHelper.getBiomeNamesForBiomes(biomes.toArray(new Biome[0])), lootTable);
            adjustElementalRenderHook(EntityBlizz.class);
        } catch (Exception ignored) {}
    }

    private void adjustElementalRenderHook(Class<? extends EntityLiving> entity) {
        final IMobRenderHook RENDER_HOOK = (renderInfo, e) -> {
            GlStateManager.translate(0, -0.3, 0);
            return renderInfo;
        };
        registerRenderHook(entity, RENDER_HOOK);
    }

}
