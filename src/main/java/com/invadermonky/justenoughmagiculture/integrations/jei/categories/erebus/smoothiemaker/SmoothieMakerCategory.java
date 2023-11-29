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

package com.invadermonky.justenoughmagiculture.integrations.jei.categories.erebus.smoothiemaker;

import com.invadermonky.justenoughmagiculture.integrations.jei.JEICategories;
import com.invadermonky.justenoughmagiculture.integrations.jei.mods.JEIErebusPlugin;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class SmoothieMakerCategory implements IRecipeCategory<SmoothieMakerWrapper> {
    private final IDrawable BACKGROUND;
    private final String localizedName;
    private final IDrawable fluidMeter;
    private final IDrawableAnimated progress;

    public SmoothieMakerCategory() {
        ResourceLocation location = new ResourceLocation("erebus", "textures/gui/container/smoothie_maker.png");
        this.BACKGROUND = JEICategories.getJeiHelpers().getGuiHelper().createDrawable(location, 7, 5, 162, 75);
        this.localizedName = I18n.format("tile.erebus.smoothie_maker.name");
        this.fluidMeter = JEICategories.getJeiHelpers().getGuiHelper().createDrawable(location, 176, 38, 9, 73);
        this.progress = JEICategories.getJeiHelpers().getGuiHelper().drawableBuilder(location, 176, 0, 73, 36).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Nonnull
    public String getUid() {
        return JEIErebusPlugin.EREBUS_SMOOTHIE_MAKER;
    }

    @Nonnull
    public String getTitle() {
        return I18n.format("jei.justenoughmagiculture:erebus_smoothie_maker.title");
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
        this.progress.draw(minecraft, 44, 21);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, @Nonnull SmoothieMakerWrapper smoothieMakerWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 39, 3);
        guiItemStacks.init(1, true, 60, 24);
        guiItemStacks.init(3, true, 84, 24);
        guiItemStacks.init(4, true, 105, 3);
        guiItemStacks.init(5, true, 72, 3);
        guiItemStacks.init(6, false, 72, 57);
        guiItemStacks.set(ingredients);
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        guiFluidStacks.init(7, true, 1, 1, 9, 73, 16000, true, this.fluidMeter);
        guiFluidStacks.init(8, true, 18, 1, 9, 73, 16000, true, this.fluidMeter);
        guiFluidStacks.init(9, true, 135, 1, 9, 73, 16000, true, this.fluidMeter);
        guiFluidStacks.init(10, true, 152, 1, 9, 73, 16000, true, this.fluidMeter);
        guiFluidStacks.set(ingredients);
    }
}
