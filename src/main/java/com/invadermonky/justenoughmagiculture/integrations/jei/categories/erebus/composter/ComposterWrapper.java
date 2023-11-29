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

import com.google.common.collect.ImmutableList;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.recipes.ComposterRegistry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ComposterWrapper implements IRecipeWrapper {
    private final ItemStack COMPOST;
    private final ItemStack OUTPUT;

    public ComposterWrapper(ItemStack compost) {
        this.COMPOST = compost.copy();
        ItemStack stack = ComposterRegistry.isCompostable(compost);
        if (stack == null || stack.isEmpty()) {
            stack = new ItemStack(ModItems.COMPOST);
        }

        this.OUTPUT = stack;
    }

    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<List<ItemStack>> in = new ArrayList();
        in.add(ImmutableList.of(this.COMPOST.copy()));
        in.add(ImmutableList.of(new ItemStack(ModBlocks.WALL_PLANTS, 1, 7), new ItemStack(ModBlocks.WALL_PLANTS_CULTIVATED, 1, 7)));
        ingredients.setInputLists(VanillaTypes.ITEM, in);
        ingredients.setOutput(VanillaTypes.ITEM, this.OUTPUT.copy());
    }
}
