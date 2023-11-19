package com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag;

import com.invadermonky.justenoughmagiculture.registry.LootBagRegistry;
import jeresources.util.Font;
import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class LootBagWrapper implements IRecipeWrapper, ITooltipCallback<ItemStack> {
    public final LootBagEntry entry;

    public LootBagWrapper(LootBagEntry entry) {
        this.entry = entry;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, entry.getLootBag());
        ingredients.setOutputs(VanillaTypes.ITEM, this.entry.getItemStacks(null));
    }

    public int amountOfItems(IFocus<ItemStack> focus) {
        return this.entry.getItemStacks(focus).size();
    }

    public List<ItemStack> getItems(IFocus<ItemStack> focus, int slot, int slots) {
        List<ItemStack> list = this.entry.getItemStacks(focus).subList(slot, slot + 1);
        for (int n = 1; n < (amountOfItems(focus) / slots) + 1; n++)
            list.add(this.amountOfItems(focus) <= slot + slots * n ? null : this.entry.getItemStacks(focus).get(slot + slots * n));
        list.removeIf(Objects::isNull);
        return list;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        Font.normal.print(this.entry.getLootBag().getDisplayName(), 60, 7);
        Font.small.print(LootBagRegistry.getInstance().getNumStacks(this.entry), 60, 20);
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack itemStack, List<String> tooltip) {
        if(slotIndex > 0)
            tooltip.add(this.entry.getBagDrop(itemStack).toString());
    }
}
