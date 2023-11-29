package com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager;

import jeresources.entry.VillagerEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class CustomVillagerEntry extends VillagerEntry {
    public CustomVillagerEntry(String name, int career, List<List<EntityVillager.ITradeList>> tradesLists) {
        super(name, 0, career, tradesLists);
    }

    @Override
    public String getDisplayName() {
        return "entity." + getName() + ".name";
    }

    public abstract EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException;

    public float getRenderScale()
    {
        return 36f;
    }
}
