package com.invadermonky.justenoughmagiculture.integrations.jei.categories.plant;

import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;

public class CustomPlantEntry extends PlantEntry {
    protected IProperty<?> ageProperty;
    protected IBlockState blockState;

    public CustomPlantEntry(ItemStack stack, @Nullable IPlantable plant, @Nullable IProperty<?> ageProperty, @Nullable IBlockState blockState, PlantDrop... drops) {
        super(stack, plant, drops);
        this.ageProperty = ageProperty;
        this.blockState = blockState;
    }

    public CustomPlantEntry(ItemStack stack, @Nullable IPlantable plant, @Nullable IProperty<?> ageProperty, PlantDrop... drops) {
        this(stack, plant, ageProperty, null, drops);
    }


    public CustomPlantEntry(ItemStack stack, @Nullable IBlockState blockState, PlantDrop... drops) {
        this(stack, null, null, blockState, drops);
    }

    public CustomPlantEntry(ItemStack stack, PlantDrop... drops) {
        this(stack, null, drops);
    }

    @Nullable
    public IProperty<?> getAgeProperty() {
        return ageProperty;
    }

    @Nullable
    public IBlockState getBlockState() {
        return blockState;
    }
}
