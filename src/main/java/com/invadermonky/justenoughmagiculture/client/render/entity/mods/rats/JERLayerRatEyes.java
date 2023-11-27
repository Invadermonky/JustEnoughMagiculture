package com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats;

import com.github.alexthe666.rats.client.model.ModelRat;
import com.github.alexthe666.rats.client.render.entity.LayerRatEyes;
import com.github.alexthe666.rats.client.render.entity.RenderRat;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import jeresources.util.FakeClientWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class JERLayerRatEyes extends LayerRatEyes {
    private static final ResourceLocation TEXTURE = new ResourceLocation("rats:textures/entity/rat/rat_eye_glow.png");
    private static final ResourceLocation TEXTURE_PLAGUE = new ResourceLocation("rats:textures/entity/rat/rat_eye_plague.png");
    private static final ResourceLocation TEXTURE_ENDER = new ResourceLocation("rats:textures/entity/rat/rat_eye_ender_upgrade.png");
    private static final ResourceLocation TEXTURE_RATINATOR = new ResourceLocation("rats:textures/entity/rat/rat_eye_ratinator_upgrade.png");
    private static final ResourceLocation TEXTURE_NONBELIEVER = new ResourceLocation("rats:textures/entity/rat/rat_eye_nonbeliever_upgrade.png");
    private static final ResourceLocation TEXTURE_DRAGON = new ResourceLocation("rats:textures/entity/rat/rat_eye_dragon_upgrade.png");
    private final RenderRat ratRenderer;

    public JERLayerRatEyes(RenderRat ratRendererIn) {
        super(ratRendererIn);
        this.ratRenderer = ratRendererIn;
    }

    public void doRenderLayer(EntityRat rat, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (this.ratRenderer.getMainModel() instanceof ModelRat) {
            long roundedTime = rat.world.getWorldTime() % 24000L;
            boolean night = roundedTime >= 13000L && roundedTime <= 22000L;
            int i = 12;
            int j = 12;
            if(rat.world != null && !(rat.world instanceof FakeClientWorld)) {
                BlockPos ratPos = rat.getLightPosition();
                i = ratPos != null ? rat.world.getLightFor(EnumSkyBlock.SKY, ratPos) : 12;
                j = ratPos != null ? rat.world.getLightFor(EnumSkyBlock.BLOCK, ratPos) : 12;
            }
            int brightness;
            if (night) {
                brightness = j;
            } else {
                brightness = Math.max(i, j);
            }

            if (brightness < 7 || rat.shouldEyesGlow()) {
                if (rat.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_DRAGON)) {
                    this.ratRenderer.bindTexture(TEXTURE_DRAGON);
                } else if (rat.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_NONBELIEVER)) {
                    this.ratRenderer.bindTexture(TEXTURE_NONBELIEVER);
                } else if (rat.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_ENDER)) {
                    this.ratRenderer.bindTexture(TEXTURE_ENDER);
                } else if (rat.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_RATINATOR)) {
                    this.ratRenderer.bindTexture(TEXTURE_RATINATOR);
                } else if (rat.hasPlague()) {
                    this.ratRenderer.bindTexture(TEXTURE_PLAGUE);
                } else {
                    this.ratRenderer.bindTexture(TEXTURE);
                }

                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
                GlStateManager.disableLighting();
                GlStateManager.depthFunc(514);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 0.0F);
                GlStateManager.enableLighting();
                Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
                this.ratRenderer.getMainModel().render(rat, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
                this.ratRenderer.setLightmap(rat);
                GlStateManager.disableBlend();
                GlStateManager.depthFunc(515);
            }

        }
    }
}
