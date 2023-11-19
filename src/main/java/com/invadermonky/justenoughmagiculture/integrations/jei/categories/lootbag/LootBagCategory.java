package com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.integrations.jei.JEICategories;
import com.invadermonky.justenoughmagiculture.util.Reference;
import jeresources.config.Settings;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LootBagCategory implements IRecipeCategory<LootBagWrapper> {
    protected static final int ITEMS_PER_ROW = 8;
    protected static final int ITEMS_PER_COLUMN = 4;
    protected static int SPACING_X = 166 / ITEMS_PER_ROW;
    protected static int SPACING_Y = 80 / ITEMS_PER_COLUMN;
    protected static int ITEMS_PER_PAGE = ITEMS_PER_COLUMN * ITEMS_PER_ROW;

    public LootBagCategory() {}

    @Override
    public String getUid() {
        return JEICategories.LOOT_BAG;
    }

    @Override
    public String getTitle() {
        return I18n.format("jei." + JustEnoughMagiculture.MOD_ID + ":loot_bag.title");
    }

    @Override
    public String getModName() {
        return JustEnoughMagiculture.MOD_NAME;
    }

    @Override
    public IDrawable getBackground() {
        return Reference.JEI.LOOT_BAG;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return JEICategories.getJeiHelpers().getGuiHelper().createDrawable(Reference.JEI.TABS, 16, 0, 16, 16);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull LootBagWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        int x = 6;
        int y = 44;

        recipeLayout.getItemStacks().init(0, true, 22, 10);

        for(int i = 0; i < Math.min(ITEMS_PER_PAGE, ingredients.getOutputs(VanillaTypes.ITEM).size()); i++) {
            recipeLayout.getItemStacks().init(i + 1, false, x, y);
            x += SPACING_X;
            if (x >= 6 + SPACING_X * Settings.ITEMS_PER_ROW * 2) {
                x = 6;
                y += SPACING_Y;
            }
        }

        recipeLayout.getItemStacks().addTooltipCallback(recipeWrapper);
        IFocus<ItemStack> focus = (IFocus<ItemStack>) recipeLayout.getFocus();
        int slots = Math.min(recipeWrapper.amountOfItems(focus), ITEMS_PER_PAGE);

        recipeLayout.getItemStacks().set(ingredients);

        for(int i = 0; i < slots; i++) {
            recipeLayout.getItemStacks().set(i + 1, recipeWrapper.getItems(focus, i, slots));
        }
    }
}
