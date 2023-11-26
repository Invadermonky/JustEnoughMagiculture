package com.invadermonky.justenoughmagiculture.util;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class BiomeHelper {
    public static String[] getBiomeNamesForBiomes(Biome... biomes) {
        List<String> biomeNames = new ArrayList<>();

        if(!JEMConfig.MODULE_JER.enableJERSpawnBiomes)
            return new String[] {"jer.any"};

        for(Biome biome : biomes) {
            biomeNames.add(WordUtils.capitalizeFully(biome.getBiomeName()));
        }

        return !biomeNames.isEmpty() ? biomeNames.toArray(new String[0]) : new String[] {"jer.any"};
    }

    public static String[] getBiomeNamesForBiomes(String... biomes) {
        List<String> biomeNames = new ArrayList<>();

        if(!JEMConfig.MODULE_JER.enableJERSpawnBiomes)
            return new String[] {"jer.any"};

        for(String biome : biomes) {
            if(biome != null && ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biome))) {
                String validBiome = WordUtils.capitalizeFully(ForgeRegistries.BIOMES.getValue(new ResourceLocation(biome)).getBiomeName());
                biomeNames.add(validBiome);
            }
        }

        return !biomeNames.isEmpty() ? biomeNames.toArray(new String[0]) : new String[] {"jer.any"};
    }

    public static String[] getBiomeNamesForTypes(String... types) {
        ArrayList<Biome> biomeNames = new ArrayList<>();
        for(String typeStr : types) {
            Type type = Type.getType(typeStr);
            biomeNames.addAll(BiomeDictionary.getBiomes(type));
        }
        return getBiomeNamesForBiomes(biomeNames.toArray(new Biome[0]));
    }

    public static String[] getBiomeNamesForTypes(Type... types) {
        ArrayList<Biome> biomeNames = new ArrayList<>();
        for(Type type : types) {
            biomeNames.addAll(BiomeDictionary.getBiomes(type));
        }
        return getBiomeNamesForBiomes(biomeNames.toArray(new Biome[0]));
    }

    public static String[] getTypeNamesForTypes(Type... types) {
        List<String> typeNames = new ArrayList<>();

        if(!JEMConfig.MODULE_JER.enableJERSpawnBiomes)
            return new String[] {"jer.any"};

        for(Type type : types) {
            typeNames.add(WordUtils.capitalizeFully(type.getName()));
        }
        return !typeNames.isEmpty() ? typeNames.toArray(new String[0]) : new String[] {"jer.any"};
    }

    public static String[] getBiomeNames(String[] validBiomeTypes) {
        return getBiomeNames(validBiomeTypes, new String[0]);
    }

    public static String[] getBiomeNames(ConfigHelper.MobJER mobJER) {
        return getBiomeNames(mobJER.validBiomeTypes, mobJER.invalidBiomeTypes);
    }

    public static String[] getBiomeNames(String[] validBiomeTypes, String[] invalidBiomeTypes) {
        ArrayList<Biome> spawnBiomes = new ArrayList<>();

        for(String typeStr : validBiomeTypes) {
            Type type = Type.getType(typeStr);
            spawnBiomes.addAll(BiomeDictionary.getBiomes(type));
        }

        for(String typeStr : invalidBiomeTypes) {
            Type type = Type.getType(typeStr);
            spawnBiomes.removeAll(BiomeDictionary.getBiomes(type));
        }

        if(spawnBiomes.isEmpty())
            return new String[] {"None"};
        else if(spawnBiomes.size() > 30)
            return new String[] {"jer.any"};

        return BiomeHelper.getBiomeNamesForBiomes(spawnBiomes.toArray(new Biome[0]));
    }
}
