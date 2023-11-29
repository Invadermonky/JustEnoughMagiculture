package com.invadermonky.justenoughmagiculture.client.render.entity.mods.erebus;

import erebus.client.render.entity.RenderMagmaCrawler;
import erebus.entity.EntityMagmaCrawler;
import jeresources.util.FakeClientWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class JERRenderMagmaCrawler extends RenderMagmaCrawler {
    public JERRenderMagmaCrawler(RenderManager rendermangerIn) {
        super(rendermangerIn);
    }

    @Override
    protected void preRenderCallback(EntityMagmaCrawler crawler, float partialTickTime) {
        this.scaleCrawler(0.75F);
        boolean onCeiling = false;
        if(crawler.world != null && !(crawler.world instanceof FakeClientWorld)) {
            onCeiling = crawler.getOnCeiling();
        }

        if (onCeiling) {
            this.rotate(180.0F, 180.0F, 0.0F);
            GlStateManager.translate(0.0F, 1.2F, 0.0F);
        } else {
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
        }
    }
}
