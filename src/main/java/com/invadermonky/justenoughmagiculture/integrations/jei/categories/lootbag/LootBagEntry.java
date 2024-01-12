package com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag;

import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import mezz.jei.api.recipe.IFocus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;
import java.util.stream.Collectors;

public class LootBagEntry {
    private final ItemStack lootBag;
    private final List<LootDrop> drops = new ArrayList<>();
    private final int minStacks;
    private final int maxStacks;

    public LootBagEntry(ItemStack lootBag, LootTable lootTable) {
        this.lootBag = lootBag;
        float[] minStacks = new float[] {0.0F};
        float[] maxStacks = new float[] {0.0F};
        LootTableManager manager = LootTableHelper.getManager();
        this.handleTable(lootTable, manager, minStacks, maxStacks);
        this.minStacks = MathHelper.floor(minStacks[0]);
        this.maxStacks = MathHelper.floor(maxStacks[0]);
    }

    private void handleTable(LootTable lootTable, LootTableManager manager, float[] tmpMinStacks, float[] tmpMaxStacks) {

        List<LootPool> pools = LootTableHelper.getPools(lootTable);
        for(LootPool pool : pools) {
            tmpMinStacks[0] += pool.getRolls().getMin();
            tmpMaxStacks[0] += pool.getRolls().getMax() + pool.getBonusRolls().getMax();
            final float totalWeight = LootTableHelper.getEntries(pool).stream().mapToInt(entry -> entry.getEffectiveWeight(0)).sum();

            List<LootEntry> entries = LootTableHelper.getEntries(pool);
            for(LootEntry entry : entries) {
                if(entry instanceof LootEntryItem) {
                    LootEntryItem entryItem = (LootEntryItem) entry;
                    drops.add(new LootDrop(LootTableHelper.getItem(entryItem), entryItem.getEffectiveWeight(0) / totalWeight, LootTableHelper.getFunctions(entryItem)));
                } else if(entry instanceof LootEntryTable) {
                    LootEntryTable entryTable = (LootEntryTable) entry;
                    LootTable table = manager.getLootTableFromLocation(entryTable.table);
                    handleTable(table, manager, tmpMinStacks, tmpMaxStacks);
                }
            }
        }
    }

    public boolean containsItem(ItemStack itemStack) {
        return drops.stream().anyMatch(drop -> drop.item.isItemEqual(itemStack));
    }

    public ItemStack getLootBag() {
        return this.lootBag;
    }

    public List<ItemStack> getItemStacks(IFocus<ItemStack> focus) {
        return drops.stream().map(drop -> drop.item)
                .filter(stack -> focus == null || ItemStack.areItemStacksEqual(ItemHandlerHelper.copyStackWithSize(stack, focus.getValue().getCount()), focus.getValue()))
                .collect(Collectors.toList());
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public int getMinStacks() {
        return minStacks;
    }

    public LootDrop getBagDrop(ItemStack ingredient) {
        return drops.stream().filter(drop -> ItemStack.areItemsEqual(drop.item, ingredient)).findFirst().orElse(null);
    }}
