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

package com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.composter;

import com.invadermonky.justenoughmagiculture.integrations.jei.JEICategories;
import com.invadermonky.justenoughmagiculture.integrations.jei.mods.JEIErebusPlugin;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ComposterCategory implements IRecipeCategory<ComposterWrapper> {
    private final IDrawable BACKGROUND;
    private final IDrawableAnimated FUEL;
    private final IDrawableAnimated COMPOST;

    public ComposterCategory() {
        ResourceLocation location = new ResourceLocation(ModIds.EREBUS.MOD_ID, "textures/gui/container/composter.png");
        this.BACKGROUND = JEICategories.getJeiHelpers().getGuiHelper().createDrawable(location, 55, 16, 85, 54);
        this.FUEL = JEICategories.getJeiHelpers().getGuiHelper().drawableBuilder(location, 176, 0, 16, 14).buildAnimated(400, IDrawableAnimated.StartDirection.TOP, true);
        this.COMPOST = JEICategories.getJeiHelpers().getGuiHelper().drawableBuilder(location, 176, 14, 30, 30).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    public String getUid() {
        return JEIErebusPlugin.EREBUS_COMPOSTER;
    }

    @Nonnull
    public String getTitle() {
        return I18n.format("jei.justenoughmagiculture:erebus_composter.title");
    }

    @Nonnull
    public String getModName() {
        return ModIds.EREBUS.MOD_NAME;
    }

    @Nonnull
    public IDrawable getBackground() {
        return this.BACKGROUND;
    }

    public void drawExtras(Minecraft minecraft) {
        this.FUEL.draw(minecraft, 1, 20);
        this.COMPOST.draw(minecraft, 21, 12);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull ComposterWrapper composterWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(1, true, 0, 36);
        guiItemStacks.init(2, false, 60, 18);
        guiItemStacks.set(ingredients);
    }
}
