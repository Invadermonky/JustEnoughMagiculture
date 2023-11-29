/*
    Code adapted from from MoreTweaker version 1.0.19 written by noeppinoeppi licensed under MIT License.

    The MIT License Copyright (c) <year> <copyright holders> Permission is hereby granted, free of charge, to any
    personobtaining a copy of this software and associated documentation files (the "Software"), to deal in the
    Software without restriction, including without limitation the rights to use, copy, modify, merge, publish,
    distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished
    to do so, subject to the following conditions: The above copyright notice and this permission notice shall be
    included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY
    OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
    DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.offeringtable;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.integrations.jei.JEICategories;
import com.invadermonky.justenoughmagiculture.integrations.jei.mods.JEIErebusPlugin;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class OfferingAltarCategory implements IRecipeCategory<OfferingAltarWrapper> {
    private final IDrawable BACKGROUND;

    public OfferingAltarCategory() {
        ResourceLocation location = new ResourceLocation(JustEnoughMagiculture.MOD_ID, "textures/gui/erebus/offering_altar.png");
        this.BACKGROUND = JEICategories.getJeiHelpers().getGuiHelper().createDrawable(location, 0, 0, 100, 37);
    }

    @Nonnull
    public String getUid() {
        return JEIErebusPlugin.EREBUS_OFFERING_ALTAR;
    }

    @Nonnull
    public String getTitle() {
        return I18n.format("jei.justenoughmagiculture:erebus_offering_altar.title");
    }

    @Nonnull
    public String getModName() {
        return ModIds.EREBUS.MOD_NAME;
    }

    @Nonnull
    public IDrawable getBackground() {
        return this.BACKGROUND;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull OfferingAltarWrapper offeringAltarWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(1, true, 0, 20);
        guiItemStacks.init(2, true, 19, 10);
        guiItemStacks.init(3, false, 79, 10);
        guiItemStacks.set(ingredients);
    }
}
