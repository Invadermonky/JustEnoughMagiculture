package com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats;

import com.github.alexthe666.rats.client.model.ModelRat;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.items.ItemChefToque;
import com.github.alexthe666.rats.server.items.ItemPiperHat;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.Iterator;

public class JERLayerRatHelmet extends LayerArmorBase<ModelBiped> {
    JERRenderRat renderer;
    private float alpha = 1.0F;
    private float colorR = 1.0F;
    private float colorG = 1.0F;
    private float colorB = 1.0F;

    public JERLayerRatHelmet(JERRenderRat rendererIn) {
        super(rendererIn);
        this.renderer = rendererIn;
    }

    public static void renderEnchantedGlint(RenderLivingBase<?> p_188364_0_, EntityLivingBase p_188364_1_, ModelRenderer model, float p_188364_3_, float p_188364_4_, float p_188364_5_, float p_188364_6_, float p_188364_7_, float p_188364_8_, float p_188364_9_) {
        renderEnchantedGlint(p_188364_0_, p_188364_1_, model, p_188364_3_, p_188364_4_, p_188364_5_, p_188364_6_, p_188364_7_, p_188364_8_, p_188364_9_, 0.38F, 0.19F, 0.608F);
    }

    public static void renderEnchantedGlint(RenderLivingBase<?> p_188364_0_, EntityLivingBase p_188364_1_, ModelRenderer model, float p_188364_3_, float p_188364_4_, float p_188364_5_, float p_188364_6_, float p_188364_7_, float p_188364_8_, float p_188364_9_, float r, float g, float b) {
        float f = (float)p_188364_1_.ticksExisted + p_188364_5_;
        p_188364_0_.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        float f1 = 0.5F;
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);

        for(int i = 0; i < 2; ++i) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
            float f2 = 0.76F;
            GlStateManager.color(r, g, b, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f3 = 0.33333334F;
            GlStateManager.scale(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.rotate(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            model.render(p_188364_9_);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    }

    protected void initArmor() {
        this.modelLeggings = new ModelBiped(0.5F);
        this.modelArmor = new ModelBiped(1.0F);
    }

    public void doRenderLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (this.renderer.getMainModel() instanceof ModelRat) {
            ItemStack itemstack = entityLivingBaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            GlStateManager.pushMatrix();
            Iterator var10;
            ModelRenderer renderer;
            if (entityLivingBaseIn instanceof EntityRat && ((EntityRat)entityLivingBaseIn).hasUpgrade(RatsItemRegistry.RAT_UPGRADE_GOD)) {
                var10 = this.renderer.getMainModel().boxList.iterator();

                while(var10.hasNext()) {
                    renderer = (ModelRenderer)var10.next();
                    GlStateManager.pushMatrix();
                    renderEnchantedGlint(this.renderer, entityLivingBaseIn, renderer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                    GlStateManager.popMatrix();
                }
            }

            if (entityLivingBaseIn instanceof EntityRat && ((EntityRat)entityLivingBaseIn).hasUpgrade(RatsItemRegistry.RAT_UPGRADE_NONBELIEVER)) {
                var10 = this.renderer.getMainModel().boxList.iterator();

                while(var10.hasNext()) {
                    renderer = (ModelRenderer)var10.next();
                    GlStateManager.pushMatrix();
                    renderEnchantedGlint(this.renderer, entityLivingBaseIn, renderer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, 0.0F, 1.0F, 0.0F);
                    GlStateManager.popMatrix();
                }
            }

            if (itemstack.getItem() instanceof ItemArmor) {
                ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
                if (itemarmor.getEquipmentSlot() == EntityEquipmentSlot.HEAD) {
                    ModelBiped t = (ModelBiped)this.getModelFromSlot(EntityEquipmentSlot.HEAD);
                    t = this.getArmorModelHook(entityLivingBaseIn, itemstack, EntityEquipmentSlot.HEAD, t);
                    this.setModelSlotVisible(t, EntityEquipmentSlot.HEAD);
                    ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                    ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
                    ((ModelRat)this.renderer.getMainModel()).neck.postRender(0.0625F);
                    ((ModelRat)this.renderer.getMainModel()).head.postRender(0.0625F);
                    GlStateManager.translate(0.0F, 0.025F, -0.035F);
                    GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scale(0.425F, 0.425F, 0.425F);
                    if (itemstack.getItem() instanceof ItemChefToque) {
                        GlStateManager.rotate(-25.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.2D, 0.03500000014901161D);
                    }

                    if (itemstack.getItem() instanceof ItemPiperHat) {
                        GlStateManager.rotate(-10.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.2D, -0.1D);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.PIRAT_HAT) {
                        GlStateManager.rotate(-5.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.3D, 0.0D);
                        GlStateManager.scale(1.425F, 1.425F, 1.425F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.ARCHEOLOGIST_HAT) {
                        GlStateManager.rotate(-5.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.45D, 0.0D);
                        GlStateManager.scale(1.425F, 1.425F, 1.425F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.FARMER_HAT || itemstack.getItem() == RatsItemRegistry.FISHERMAN_HAT) {
                        GlStateManager.rotate(-5.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.4D, 0.0D);
                        GlStateManager.scale(1.425F, 1.425F, 1.425F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.PLAGUE_DOCTOR_MASK || itemstack.getItem() == RatsItemRegistry.BLACK_DEATH_MASK) {
                        GlStateManager.rotate(-15.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0F, 0.25F, 0.0F);
                        GlStateManager.scale(1.5F, 1.5F, 1.5F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.RAT_FEZ) {
                        GlStateManager.translate(-0.20000000298023224D, 0.46D, -0.10000000149011612D);
                        GlStateManager.scale(1.425F, 1.425F, 1.425F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.TOP_HAT) {
                        GlStateManager.rotate(-5.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.38D, -0.10000000149011612D);
                        GlStateManager.scale(1.425F, 1.425F, 1.425F);
                    }

                    if (itemstack.getItem() == RatsItemRegistry.SANTA_HAT) {
                        GlStateManager.rotate(-5.0F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.translate(0.0D, 0.275D, 0.0D);
                        GlStateManager.scale(1.25F, 1.25F, 1.25F);
                    }

                    this.renderer.bindTexture(this.getArmorResource(entityLivingBaseIn, itemstack, EntityEquipmentSlot.HEAD, (String)null));
                    if (itemarmor.hasOverlay(itemstack)) {
                        int i = itemarmor.getColor(itemstack);
                        float f = (float)(i >> 16 & 255) / 255.0F;
                        float f1 = (float)(i >> 8 & 255) / 255.0F;
                        float f2 = (float)(i & 255) / 255.0F;
                        GlStateManager.color(this.colorR * f, this.colorG * f1, this.colorB * f2, this.alpha);
                        t.render(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                        this.renderer.bindTexture(this.getArmorResource(entityLivingBaseIn, itemstack, EntityEquipmentSlot.HEAD, "overlay"));
                    }

                    GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
                    t.bipedHead.render(scale);
                    if (itemstack.hasEffect()) {
                        renderEnchantedGlint(this.renderer, entityLivingBaseIn, t.bipedHead, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                    }
                }
            } else if (itemstack.getItem() instanceof ItemBanner) {
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
                GlStateManager.translate(0.0F, -0.5F, -0.2F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                if (entityLivingBaseIn instanceof EntityRat) {
                    EntityRat rat = (EntityRat)entityLivingBaseIn;
                    float sitProgress = rat.sitProgress / 20.0F;
                    GlStateManager.rotate(sitProgress * -40.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0D, 0.0D, (double)(-sitProgress) * 0.04D);
                }

                GlStateManager.scale(1.7F, 1.7F, 1.7F);
                Minecraft.getMinecraft().getItemRenderer().renderItem(entityLivingBaseIn, itemstack, ItemCameraTransforms.TransformType.FIXED);
            } else {
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).neck.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).head.postRender(0.0625F);
                GlStateManager.translate(0.0F, 0.025F, -0.2F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(0.8F, 0.8F, 0.8F);
                Minecraft.getMinecraft().getItemRenderer().renderItem(entityLivingBaseIn, itemstack, ItemCameraTransforms.TransformType.FIXED);
            }

            GlStateManager.popMatrix();
        }
    }

    protected void setModelSlotVisible(ModelBiped p_188359_1_, EntityEquipmentSlot slotIn) {
        this.setModelVisible(p_188359_1_);
        switch(slotIn) {
            case HEAD:
                p_188359_1_.bipedHead.showModel = true;
                p_188359_1_.bipedHeadwear.showModel = true;
                break;
            case CHEST:
                p_188359_1_.bipedBody.showModel = true;
                p_188359_1_.bipedRightArm.showModel = true;
                p_188359_1_.bipedLeftArm.showModel = true;
                break;
            case LEGS:
                p_188359_1_.bipedBody.showModel = true;
                p_188359_1_.bipedRightLeg.showModel = true;
                p_188359_1_.bipedLeftLeg.showModel = true;
                break;
            case FEET:
                p_188359_1_.bipedRightLeg.showModel = true;
                p_188359_1_.bipedLeftLeg.showModel = true;
        }

    }

    protected void setModelVisible(ModelBiped model) {
        model.setVisible(false);
    }

    protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
        return ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
    }
}
