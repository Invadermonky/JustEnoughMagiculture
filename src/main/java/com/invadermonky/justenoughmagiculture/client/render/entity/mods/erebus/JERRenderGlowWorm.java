package com.invadermonky.justenoughmagiculture.client.render.entity.mods.erebus;

import erebus.client.render.entity.RenderGlowWorm;
import erebus.entity.EntityGlowWorm;
import jeresources.util.FakeClientWorld;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class JERRenderGlowWorm extends RenderGlowWorm {
    private static final ResourceLocation TEXTURE_ON = new ResourceLocation("erebus:textures/entity/glow_worm_glow.png");
    private static final ResourceLocation TEXTURE_OFF = new ResourceLocation("erebus:textures/entity/glow_worm.png");

    public JERRenderGlowWorm(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGlowWorm glowworm) {
        boolean isGlowing = false;
        if(glowworm.world != null && !(glowworm.world instanceof FakeClientWorld)) {
            isGlowing = glowworm.isGlowing();
        }
        return isGlowing ? TEXTURE_ON : TEXTURE_OFF;
    }
}
