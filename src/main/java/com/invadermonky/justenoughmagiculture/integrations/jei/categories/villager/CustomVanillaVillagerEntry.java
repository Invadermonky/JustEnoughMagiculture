package com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager;

import net.minecraft.entity.passive.EntityVillager;

import java.util.List;

public abstract class CustomVanillaVillagerEntry extends CustomVillagerEntry {

    public CustomVanillaVillagerEntry(String name, int career, List<List<EntityVillager.ITradeList>> tradesLists) {
        super(name, career, tradesLists);
    }
}
