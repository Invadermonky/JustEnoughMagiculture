package com.invadermonky.justenoughmagiculture.registry;

import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagEntry;
import jeresources.util.MapKeys;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LootBagRegistry {
    private Map<String, LootBagEntry> registry;
    private static LootBagRegistry instance;

    public static LootBagRegistry getInstance() {
        return instance != null ? instance : (instance = new LootBagRegistry());
    }

    public LootBagRegistry() {
        registry = new LinkedHashMap<>();
    }

    public boolean registerLootBag(LootBagEntry entry) {
        String key = MapKeys.getKey(entry.getLootBag());
        if(key == null || registry.containsKey(key))
            return false;
        registry.put(key, entry);
        return true;
    }

    public String getNumStacks(LootBagEntry entry) {
        int max = entry.getMaxStacks();
        int min = entry.getMinStacks();
        return min == max ? I18n.format("jer.stacks", max) : I18n.format("jer.stacks", min + " - " + max);
    }

    public List<LootBagEntry> getAllLootBags() {
        return new ArrayList<>(registry.values());
    }
}
