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

import com.google.common.collect.ImmutableList;
import com.invadermonky.justenoughmagiculture.util.JEIHelper;
import erebus.recipes.SmoothieMakerRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SmoothieMakerWrapper implements IRecipeWrapper {
    private final SmoothieMakerRecipe RECIPE;

    public SmoothieMakerWrapper(SmoothieMakerRecipe recipe) {
        this.RECIPE = recipe;
    }

    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<List<ItemStack>> in = JEIHelper.getStacksList(this.RECIPE.getInputs(), 4);
        in.add(ImmutableList.of(this.RECIPE.getContainer()));
        ingredients.setInputLists(VanillaTypes.ITEM, in);
        ingredients.setOutput(VanillaTypes.ITEM, this.RECIPE.getOutput());

        List<List<FluidStack>> fluids = new ArrayList();
        FluidStack[] fluidStacks = this.RECIPE.getFluids();

        for(int i = 0; i < fluidStacks.length; i++) {
            FluidStack stack = fluidStacks[i];
            fluids.add(ImmutableList.of(stack));
        }

        ingredients.setInputLists(VanillaTypes.FLUID, fluids);
    }
}
