package com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.plant;

import jeresources.entry.PlantEntry;
import jeresources.jei.plant.PlantWrapper;
import jeresources.util.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CustomPlantWrapper extends PlantWrapper {
    private final PlantEntry entry;
    private IBlockState state;
    private IProperty<?> ageProperty;
    private long timer = -1L;
    private static final int TICKS = 500;

    public CustomPlantWrapper(PlantEntry entry) {
        super(entry);
        this.entry = entry;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        RenderHelper.renderBlock(getFarmland(), 26.0f, 50.0f, -10.0f, 20.0f, 0.4f);
        RenderHelper.renderBlock(getBlockState(), 26.0f, 32.0f, 10.0f, 20.0f, 0.4f);
    }

    private IBlockState getBlockState() {
        if(state == null) {
            if(entry instanceof CustomPlantEntry) {
                CustomPlantEntry plant = (CustomPlantEntry) entry;
                if(plant.getBlockState() != null)
                    state = plant.getBlockState();
                if(plant.getAgeProperty() != null)
                    ageProperty = plant.getAgeProperty();
            }

            if(state == null) {
                if(entry.getPlant() != null)
                    state = entry.getPlant().getPlant(null, null);
                else
                    state = Block.getBlockFromItem(entry.getPlantItemStack().getItem()).getDefaultState();
            }

            if(ageProperty == null) {
                Optional<IProperty<?>> ageProperty = state.getPropertyKeys().stream().filter(property -> property.getName().equals("age")).findAny();
                ageProperty.ifPresent(property -> this.ageProperty = property);
            }

            if(ageProperty != null) {
                timer = System.currentTimeMillis() + TICKS;
            }
        }

        if(timer != -1L && System.currentTimeMillis() > timer) {
            state = state.cycleProperty(ageProperty);
            timer = System.currentTimeMillis() + TICKS;
        }

        return state;
    }

    private IBlockState getFarmland() {
        return entry.getSoil() != null ? entry.getSoil() : Blocks.FARMLAND.getDefaultState();
    }
}
