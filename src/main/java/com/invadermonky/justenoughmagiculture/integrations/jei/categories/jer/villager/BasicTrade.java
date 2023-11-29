package com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BasicTrade implements EntityVillager.ITradeList {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack output;

    public BasicTrade(ItemStack output, ItemStack input1, ItemStack input2) {
        this.output = output;
        this.input1 = input1;
        this.input2 = input2;
    }

    public BasicTrade(ItemStack output, ItemStack input) {
        this(output, input, ItemStack.EMPTY);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
        recipeList.add(new MerchantRecipe(input1, input2, output));
    }
}
