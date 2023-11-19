package com.invadermonky.justenoughmagiculture.registry;

import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.CustomPigtificateEntry;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.CustomVillagerEntry;

import java.util.LinkedList;
import java.util.List;

public class CustomVillagerRegistry {
    private static CustomVillagerRegistry instance;

    private List<CustomVillagerEntry> villagers;
    private List<CustomPigtificateEntry> pigtificates;

    public static CustomVillagerRegistry getInstance() {
        return instance != null ? instance : (instance = new CustomVillagerRegistry());
    }

    private CustomVillagerRegistry() {
        this.villagers = new LinkedList<>();
        this.pigtificates = new LinkedList<>();
    }

    public void addVillagerEntry(CustomVillagerEntry entry) {
        this.villagers.add(entry);
    }

    public void addPigtificateEntry(CustomPigtificateEntry entry) {
        this.pigtificates.add(entry);
    }

    public List<CustomVillagerEntry> getVillagers() {
        return this.villagers;
    }

    public List<CustomPigtificateEntry> getPigtificates() {
        return this.pigtificates;
    }

    public void clear() {
        villagers.clear();
        pigtificates.clear();
    }
}
