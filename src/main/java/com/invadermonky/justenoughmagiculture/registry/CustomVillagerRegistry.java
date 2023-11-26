package com.invadermonky.justenoughmagiculture.registry;

import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.CustomVillagerEntry;

import java.util.LinkedList;
import java.util.List;

public class CustomVillagerRegistry {
    private static CustomVillagerRegistry instance;

    private List<CustomVillagerEntry> villagers;

    public static CustomVillagerRegistry getInstance() {
        return instance != null ? instance : (instance = new CustomVillagerRegistry());
    }

    private CustomVillagerRegistry() {
        this.villagers = new LinkedList<>();
    }

    public void addVillagerEntry(CustomVillagerEntry entry) {
        this.villagers.add(entry);
    }

    public List<CustomVillagerEntry> getVillagers() {
        return this.villagers;
    }

    public void clear() {
        villagers.clear();
    }
}
