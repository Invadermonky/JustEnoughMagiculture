package com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats;

import com.github.alexthe666.rats.client.model.ModelPiratCannon;
import com.github.alexthe666.rats.client.model.ModelRat;
import com.github.alexthe666.rats.server.blocks.RatsBlockRegistry;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.items.ItemRatUpgradeBucket;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class JERLayerRatHeldItem implements LayerRenderer<EntityRat> {
    protected static final ModelChest MODEL_CHEST = new ModelChest();
    protected static final ModelPiratCannon MODEL_PIRAT_CANNON = new ModelPiratCannon();
    protected static final ResourceLocation TEXTURE_PIRATE_CANNON = new ResourceLocation("rats:textures/entity/ratlantis/pirat_cannon.png");
    protected static final ResourceLocation TEXTURE_PIRATE_CANNON_FIRE = new ResourceLocation("rats:textures/entity/ratlantis/pirat_cannon_fire.png");
    private static final ResourceLocation TEXTURE_CHRISTMAS_CHEST = new ResourceLocation("textures/entity/chest/christmas.png");
    private static ItemStack PLATTER_STACK;
    private static ItemStack AXE_STACK;
    private static ItemStack PICKAXE_STACK;
    private static ItemStack IRON_AXE_STACK;
    private static ItemStack IRON_HOE_STACK;
    private static ItemStack SHEARS_STACK;
    private static ItemStack TNT_STACK;
    private static ItemStack FISHING_ROD_STACK;
    private static ItemStack WING_STACK;
    private static ItemStack DRAGON_WING_STACK;
    private static ItemStack BRAIN_STACK;
    JERRenderRat renderer;

    public JERLayerRatHeldItem(JERRenderRat renderer) {
        this.renderer = renderer;
    }

    public void doRenderLayer(EntityRat entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (this.renderer.getMainModel() instanceof ModelRat) {
            ItemStack itemstack = entity.getHeldItem(EnumHand.MAIN_HAND);
            float f;
            Minecraft minecraft;
            if (!itemstack.isEmpty()) {
                GlStateManager.color(1.0F, 1.0F, 1.0F);
                GlStateManager.pushMatrix();
                if (this.renderer.getMainModel().isChild) {
                    GlStateManager.translate(0.0F, 0.625F, 0.0F);
                    GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                    f = 0.5F;
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);
                }

                minecraft = Minecraft.getMinecraft();
                if (entity.shouldNotIdleAnimation()) {
                    this.translateToHead();
                    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0D, 0.25D, 0.05000000074505806D);
                } else {
                    this.translateToHand(true);
                    GlStateManager.rotate(190.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(-0.1550000011920929D, -0.025D, 0.125D);
                    if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_PLATTER)) {
                        GlStateManager.translate(0.0F, 0.25F, 0.0F);
                        if (itemstack.getItem() instanceof ItemBlock) {
                            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                        } else {
                            GlStateManager.translate(0.0F, -0.1F, -0.075F);
                        }
                    }

                    if (entity.holdsItemInHandUpgrade()) {
                        GlStateManager.translate(0.15F, -0.075F, 0.0F);
                    }
                }

                minecraft.getItemRenderer().renderItem(entity, itemstack, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_BUCCANEER)) {
                GlStateManager.pushMatrix();
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                GlStateManager.pushMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.925F, 0.2F);
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
                this.renderer.bindTexture(TEXTURE_PIRATE_CANNON);
                MODEL_PIRAT_CANNON.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.popMatrix();
                if (entity.getVisualFlag()) {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(0.0F, -0.925F, 0.2F);
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);
                    GlStateManager.disableLighting();
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 0.0F);
                    this.renderer.bindTexture(TEXTURE_PIRATE_CANNON_FIRE);
                    MODEL_PIRAT_CANNON.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                }

                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_CHRISTMAS)) {
                GlStateManager.pushMatrix();
                if (this.renderer.getMainModel().isChild) {
                    GlStateManager.translate(0.0F, 0.625F, 0.0F);
                    GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                    f = 0.5F;
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);
                }

                minecraft = Minecraft.getMinecraft();
                this.translateToHand(false);
                GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(10.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.15000000596046448D, -0.4D, -0.5D);
                GlStateManager.pushMatrix();
                GlStateManager.translate(-0.175F, 0.25F, 0.2F);
                GlStateManager.scale(0.35F, 0.35F, 0.35F);
                this.renderer.bindTexture(TEXTURE_CHRISTMAS_CHEST);
                MODEL_CHEST.renderAll();
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_PLATTER)) {
                GlStateManager.pushMatrix();
                if (this.renderer.getMainModel().isChild) {
                    GlStateManager.translate(0.0F, 0.625F, 0.0F);
                    GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                    f = 0.5F;
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);
                }

                minecraft = Minecraft.getMinecraft();
                this.translateToHand(true);
                GlStateManager.rotate(190.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-70.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(-0.1550000011920929D, -0.225D, 0.20000000298023224D);
                GlStateManager.scale(2.0F, 2.0F, 2.0F);
                minecraft.getItemRenderer().renderItem(entity, PLATTER_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_BUCKET) || entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_BIG_BUCKET) || entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_MILKER)) {
                GlStateManager.pushMatrix();
                if (this.renderer.getMainModel().isChild) {
                    GlStateManager.translate(0.0F, 0.625F, 0.0F);
                    GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
                    f = 0.5F;
                    GlStateManager.scale(0.5F, 0.5F, 0.5F);
                }

                minecraft = Minecraft.getMinecraft();
                this.translateToHand(true);
                GlStateManager.rotate(190.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-40.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(-0.1550000011920929D, -0.225D, 0.10000000149011612D);
                GlStateManager.scale(1.75F, 1.75F, 1.75F);
                minecraft.getItemRenderer().renderItem(entity, ItemRatUpgradeBucket.getBucketFromFluid(entity.transportingFluid), ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_CRAFTING)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(true);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, AXE_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, PICKAXE_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_LUMBERJACK)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-15.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, IRON_AXE_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_MINER)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, new ItemStack(Items.DIAMOND_PICKAXE), ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_FARMER)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-15.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, IRON_HOE_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_SHEARS)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(15.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.translate(0.10000000149011612D, 0.0D, 0.0D);
                minecraft.getItemRenderer().renderItem(entity, SHEARS_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_FISHERMAN)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHand(false);
                GlStateManager.rotate(-180.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.2F, 0.0F, 0.0F);
                minecraft.getItemRenderer().renderItem(entity, FISHING_ROD_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            float wingAngle;
            float wingFold;
            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_FLIGHT)) {
                GlStateManager.pushMatrix();
                minecraft = Minecraft.getMinecraft();
                wingAngle = entity.onGround ? 0.0F : MathHelper.sin(ageInTicks) * 30.0F;
                wingFold = entity.onGround ? -45.0F : 0.0F;
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.1F, 0.0F);
                GlStateManager.rotate(wingAngle, 0.0F, 0.0F, -1.0F);
                GlStateManager.rotate(wingFold, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.55F, 0.0F, 0.2F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(2.0F, 2.0F, 1.0F);
                minecraft.getItemRenderer().renderItem(entity, WING_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.1F, 0.0F);
                GlStateManager.rotate(wingAngle, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(wingFold, 0.0F, -1.0F, 0.0F);
                GlStateManager.translate(-0.55F, 0.0F, 0.2F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(2.0F, 2.0F, 1.0F);
                minecraft.getItemRenderer().renderItem(entity, WING_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_DRAGON)) {
                GlStateManager.pushMatrix();
                minecraft = Minecraft.getMinecraft();
                wingAngle = entity.onGround ? 0.0F : MathHelper.sin(ageInTicks) * 30.0F;
                wingFold = entity.onGround ? -45.0F : 0.0F;
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.1F, 0.0F);
                GlStateManager.rotate(wingAngle, 0.0F, 0.0F, -1.0F);
                GlStateManager.rotate(wingFold, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(0.55F, 0.0F, 0.2F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(2.0F, 2.0F, 1.0F);
                minecraft.getItemRenderer().renderItem(entity, DRAGON_WING_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.1F, 0.0F);
                GlStateManager.rotate(wingAngle, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(wingFold, 0.0F, -1.0F, 0.0F);
                GlStateManager.translate(-0.55F, 0.0F, 0.2F);
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(2.0F, 2.0F, 1.0F);
                minecraft.getItemRenderer().renderItem(entity, DRAGON_WING_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_TNT) || entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_TNT_SURVIVOR)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
                GlStateManager.translate(0.0F, 0.1F, 0.1F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(2.0F, 2.0F, 2.0F);
                minecraft.getItemRenderer().renderItem(entity, TNT_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

            if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_PSYCHIC)) {
                minecraft = Minecraft.getMinecraft();
                GlStateManager.pushMatrix();
                this.translateToHead();
                GlStateManager.translate(0.0F, 0.1F, 0.035F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(0.9F, 0.9F, 0.9F);
                minecraft.getItemRenderer().renderItem(entity, BRAIN_STACK, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            }

        }
    }

    protected void translateToHead() {
        ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
        ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
        ((ModelRat)this.renderer.getMainModel()).neck.postRender(0.0625F);
        ((ModelRat)this.renderer.getMainModel()).head.postRender(0.0625F);
    }

    protected void translateToHand(boolean left) {
        ((ModelRat)this.renderer.getMainModel()).body1.postRender(0.0625F);
        ((ModelRat)this.renderer.getMainModel()).body2.postRender(0.0625F);
        if (left) {
            ((ModelRat)this.renderer.getMainModel()).leftArm.postRender(0.0625F);
            ((ModelRat)this.renderer.getMainModel()).leftHand.postRender(0.0625F);
        } else {
            ((ModelRat)this.renderer.getMainModel()).rightArm.postRender(0.0625F);
            ((ModelRat)this.renderer.getMainModel()).rightHand.postRender(0.0625F);
        }

    }

    public boolean shouldCombineTextures() {
        return false;
    }

    static {
        PLATTER_STACK = new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
        AXE_STACK = new ItemStack(Items.STONE_AXE);
        PICKAXE_STACK = new ItemStack(Items.STONE_PICKAXE);
        IRON_AXE_STACK = new ItemStack(Items.IRON_AXE);
        IRON_HOE_STACK = new ItemStack(Items.IRON_HOE);
        SHEARS_STACK = new ItemStack(Items.SHEARS);
        TNT_STACK = new ItemStack(Blocks.TNT);
        FISHING_ROD_STACK = new ItemStack(Items.FISHING_ROD);
        WING_STACK = new ItemStack(RatsItemRegistry.FEATHERY_WING);
        DRAGON_WING_STACK = new ItemStack(RatsItemRegistry.DRAGON_WING);
        BRAIN_STACK = new ItemStack(RatsBlockRegistry.BRAIN_BLOCK);
    }
}
