package com.invadermonky.justenoughmagiculture.util.modhelpers;

import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntitySeaSerpent;
import com.github.alexthe666.iceandfire.item.*;
import com.github.alexthe666.iceandfire.loot.CustomizeToDragon;
import com.github.alexthe666.iceandfire.loot.CustomizeToSeaSerpent;
import com.invadermonky.justenoughmagiculture.init.InitIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMConditional;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import jeresources.api.drop.LootDrop;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.*;

public class IaFLootHelper {
    public static List<LootDrop> toDrops(EntityLivingBase entity, LootTable table) {
        List<LootDrop> drops = new ArrayList<>();
        LootTableManager manager = InitIntegration.manager;
        getPools(table).forEach((pool) -> {
            float totalWeight = (float) getEntries(pool).stream().mapToInt((entry) -> entry.getEffectiveWeight(0.0F)).sum();
            List<LootCondition> poolConditions = getConditions(pool);
            getEntries(pool).stream()
                    .filter((entry) -> entry instanceof LootEntryItem)
                    .map((entry) -> (LootEntryItem) entry)
                    .map((entry) -> new CustomLootDrop(entity, getItem(entry), (float) entry.getEffectiveWeight(0.0F) / totalWeight, getConditions(entry), getFunctions(entry)))
                    .map((drop) -> drop.addLootConditions(poolConditions)).forEach(drops::add);
            getEntries(pool).stream()
                    .filter((entry) -> entry instanceof LootEntryTable)
                    .map((entry) -> (LootEntryTable) entry)
                    .map((entry) -> toDrops(entity, manager.getLootTableFromLocation(getTable(entry)))).forEach(drops::addAll);
        });
        drops.removeIf(Objects::isNull);
        return drops;
    }

    public static ResourceLocation getTable(LootEntryTable lootEntry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntryTable.class, lootEntry, "field_186371_a");
    }

    public static RandomValueRange getMetaRange(SetMetadata function) {
        return ObfuscationReflectionHelper.getPrivateValue(SetMetadata.class, function, "field_186573_b");
    }

    public static List<LootPool> getPools(LootTable table) {
        return ObfuscationReflectionHelper.getPrivateValue(LootTable.class, table, "field_186466_c");
    }

    public static List<LootCondition> getConditions(LootPool pool) {
        return ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "field_186454_b");
    }

    public static List<LootEntry> getEntries(LootPool pool) {
        return ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "field_186453_a");
    }

    public static Item getItem(LootEntryItem lootEntry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntryItem.class, lootEntry, "field_186368_a");
    }

    public static LootCondition[] getConditions(LootEntry lootEntry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntry.class, lootEntry, "field_186366_e");
    }

    public static LootFunction[] getFunctions(LootEntryItem lootEntry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntryItem.class, lootEntry, "field_186369_b");
    }

    private static class CustomLootDrop extends LootDrop {
        public CustomLootDrop(EntityLivingBase entity, Item item, float chance, LootFunction... lootFunctions) {
            super(new ItemStack(item), chance);
            enchanted = false;
            addLootFunctions(lootFunctions, entity);
        }

        public CustomLootDrop(EntityLivingBase entity, Item item, float chance, LootCondition[] lootConditions, LootFunction... lootFunctions) {
            this(entity, item, chance, lootFunctions);
            addLootConditions(lootConditions);
        }

        public void addLootFunctions(LootFunction[] lootFunctions, EntityLivingBase entity) {
            this.addLootFunctions(Arrays.asList(lootFunctions), entity);
        }

        public void addLootFunctions(Collection<LootFunction> lootFunctions, EntityLivingBase entity) {
            lootFunctions.forEach(function -> addLootFunction(function, entity));
        }

        public void addLootFunction(LootFunction function, EntityLivingBase entity) {
            if (!ModIds.ICE_AND_FIRE.isLoaded || !applyIceAndFireFunctions(function, entity))
                super.addLootFunction(function);
        }

        public boolean applyIceAndFireFunctions(LootFunction condition, EntityLivingBase entity) {
            if (condition instanceof CustomizeToDragon && entity instanceof EntityDragonBase) {
                EntityDragonBase dragon = (EntityDragonBase)entity;
                if (item.getItem() == IafItemRegistry.dragonbone) {
                    addConditional(JEMConditional.dependsOnAge);
                    minDrop = 1;
                    maxDrop = 64;
                    chance = 1.0f;
                }
                else if (item.getItem() instanceof ItemDragonScales) {
                    addConditional(JEMConditional.dependsOnAge);
                    minDrop = 0;
                    maxDrop = 64;
                    chance = 1.0f;
                    item = new ItemStack(dragon.getVariantScale(dragon.getVariant()), item.getCount(), item.getMetadata());
                }
                else if (item.getItem() instanceof ItemDragonEgg) {
                    addConditional(JEMConditional.isAdult);
                    chance = 1.0f;
                    item = new ItemStack(dragon.getVariantEgg(dragon.getVariant()), item.getCount(), item.getMetadata());
                }
                else if (item.getItem() instanceof ItemDragonFlesh) {
                    addConditional(JEMConditional.dependsOnAge);
                    minDrop = 1;
                    maxDrop = 64;
                    chance = 1.0f;
                    item = new ItemStack(dragon.dragonType == DragonType.FIRE ? IafItemRegistry.fire_dragon_flesh : IafItemRegistry.ice_dragon_flesh, item.getCount(), item.getMetadata());
                }
                else if (item.getItem() instanceof ItemDragonSkull) {
                    ItemStack newSkull = new ItemStack(dragon.dragonType == DragonType.FIRE ? IafItemRegistry.dragon_skull : IafItemRegistry.dragon_skull, item.getCount(), item.getMetadata());
                    newSkull.setTagCompound(item.getTagCompound());
                    item = newSkull;
                }
                else if (item.getItem() == IafItemRegistry.fire_dragon_blood || item.getItem() == IafItemRegistry.ice_dragon_blood) {
                    addConditional(JEMConditional.requiresBottle);
                    chance = 1.0f;
                    item = new ItemStack(dragon.dragonType == DragonType.FIRE ? IafItemRegistry.fire_dragon_blood : IafItemRegistry.ice_dragon_blood, item.getCount(), item.getMetadata());
                }
                else if (item.getItem() == IafItemRegistry.fire_dragon_heart || item.getItem() == IafItemRegistry.ice_dragon_heart) {
                    chance = 1.0f;
                    item = new ItemStack(dragon.dragonType == DragonType.FIRE ? IafItemRegistry.fire_dragon_heart : IafItemRegistry.ice_dragon_heart, item.getCount(), item.getMetadata());
                }
            }
            else if (condition instanceof CustomizeToSeaSerpent && entity instanceof EntitySeaSerpent) {
                EntitySeaSerpent seaSerpent = (EntitySeaSerpent) entity;
                if (item.getItem() instanceof ItemSeaSerpentScales) {
                    addConditional(JEMConditional.affectedByAncient);
                    addConditional(JEMConditional.dependsOnSize);
                    chance = 1.0f;
                    minDrop = 1;
                    maxDrop = (int) (5.5f * 3.0f);
                    item = new ItemStack(seaSerpent.getEnum().scale, item.getCount(), item.getMetadata());
                }
                else if (item.getItem() == IafItemRegistry.sea_serpent_fang) {
                    addConditional(JEMConditional.affectedByAncient);
                    addConditional(JEMConditional.dependsOnSize);
                    chance = 1.0f;
                    minDrop = 1;
                    maxDrop = (int) (5.5f * 2.0f);
                }
            }
            else return false;
            return true;
        }
    }
}
