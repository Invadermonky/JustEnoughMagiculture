package com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag;

import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import mezz.jei.api.recipe.IFocus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LootBagEntry {
    private final ItemStack lootBag;
    private final Set<LootDrop> drops = new TreeSet<>();
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
        LootTableHelper.getPools(lootTable).forEach(
                pool -> {
                    tmpMinStacks[0] += pool.getRolls().getMin();
                    tmpMaxStacks[0] += pool.getRolls().getMax() + pool.getBonusRolls().getMax();
                    final float totalWeight = LootTableHelper.getEntries(pool).stream().mapToInt(entry -> entry.getEffectiveWeight(0)).sum();
                    LootTableHelper.getEntries(pool).stream()
                            .filter(entry -> entry instanceof LootEntryItem).map(entry -> (LootEntryItem) entry)
                            .map(entry -> new LootDrop(LootTableHelper.getItem(entry), entry.getEffectiveWeight(0) / totalWeight, LootTableHelper.getFunctions(entry))).forEach(drops::add);

                    LootTableHelper.getEntries(pool).stream()
                            .filter(entry -> entry instanceof LootEntryTable).map(entry -> (LootEntryTable) entry)
                            .map(entry -> manager.getLootTableFromLocation(entry.table))
                            .forEach(table -> handleTable(table, manager, tmpMinStacks, tmpMaxStacks));
                }
        );
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
