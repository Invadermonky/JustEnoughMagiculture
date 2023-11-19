package com.invadermonky.justenoughmagiculture.util;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class BackgroundDrawable implements IDrawable {
    private final int width;
    private final int height;
    private final ResourceLocation resource;
    private static final int PADDING = 5;

    public BackgroundDrawable(String resource, int width, int height) {
        this.resource = new ResourceLocation(JustEnoughMagiculture.MOD_ID, resource);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width + 10;
    }

    @Override
    public int getHeight() {
        return height + 10;
    }

    @Override
    public void draw(Minecraft minecraft) {
        this.draw(minecraft, 0, 0);
    }

    @Override
    public void draw(Minecraft minecraft, int xOffset, int yOffset) {
        GlStateManager.resetColor();
        minecraft.getTextureManager().bindTexture(this.resource);
        GuiUtils.drawTexturedModalRect(xOffset + PADDING, yOffset + PADDING, 0, 0, this.width, this.height, 0.0f);
    }

    public ResourceLocation getResource() {
        return this.resource;
    }
}
