package com.invadermonky.justenoughmagiculture.registry;

import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.util.MapKeys;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomPlantRegistry {
    private Map<String, PlantEntry> registry;
    private static CustomPlantRegistry instance;

    public static CustomPlantRegistry getInstance() {
        return instance != null ? instance : (instance = new CustomPlantRegistry());
    }


    public CustomPlantRegistry() {
        registry = new LinkedHashMap<>();
    }

    public boolean registerPlant(PlantEntry entry) {
        String key = MapKeys.getKey(entry.getPlantItemStack());
        if (key == null || contains(key))
            return false;
        registry.put(key, entry);
        return true;
    }

    private boolean contains(String key) {
        return registry.containsKey(key);
    }

    public List<PlantEntry> getAllPlants() {
        return new ArrayList<>(registry.values());
    }

    public void addDrops(ItemStack itemStack, PlantDrop[] drops) {
        String key = MapKeys.getKey(itemStack);
        if (key == null || contains(key))
            for (PlantDrop drop : drops)
                registry.get(key).add(drop);
    }

    public void clear() {
        registry.clear();
    }
}
