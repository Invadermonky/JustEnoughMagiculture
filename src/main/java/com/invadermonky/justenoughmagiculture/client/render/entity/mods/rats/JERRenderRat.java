package com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats;

import com.github.alexthe666.rats.RatsMod;
import com.github.alexthe666.rats.client.model.ModelPinkie;
import com.github.alexthe666.rats.client.model.ModelRat;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Map;

public class JERRenderRat extends RenderLiving<EntityRat> {
    private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.newHashMap();
    private static final ModelRat RAT_MODEL = new ModelRat(0.0F);
    private static final ModelPinkie PINKIE_MODEL = new ModelPinkie();
    private static final ResourceLocation PINKIE_TEXTURE = new ResourceLocation("rats:textures/entity/rat/baby.png");
    private static final ResourceLocation ENDER_UPGRADE_TEXTURE = new ResourceLocation("rats:textures/entity/rat/rat_ender_upgrade.png");
    private static final ResourceLocation AQUATIC_UPGRADE_TEXTURE = new ResourceLocation("rats:textures/entity/rat/rat_aquatic_upgrade.png");
    private static final ResourceLocation DRAGON_UPGRADE_TEXTURE = new ResourceLocation("rats:textures/entity/rat/rat_dragon_upgrade.png");
    private static final ResourceLocation JULIAN = new ResourceLocation("rats:textures/entity/rat/patreon/rat_julian.png");
    private static final ResourceLocation SHIZUKA = new ResourceLocation("rats:textures/entity/rat/patreon/rat_shizuka.png");
    private static final ResourceLocation SHARVA = new ResourceLocation("rats:textures/entity/rat/patreon/rat_sharva.png");
    private static final ResourceLocation DINO = new ResourceLocation("rats:textures/entity/rat/patreon/rat_dino.png");
    private static final ResourceLocation RATALA = new ResourceLocation("rats:textures/entity/rat/patreon/rat_ratala.png");
    private static final ResourceLocation FRIAR = new ResourceLocation("rats:textures/entity/rat/patreon/rat_friar.png");
    private static final ResourceLocation RIDDLER = new ResourceLocation("rats:textures/entity/rat/patreon/rat_riddler.png");
    private static final ResourceLocation JOKER = new ResourceLocation("rats:textures/entity/rat/patreon/rat_joker.png");

    public JERRenderRat(RenderManager renderManager) {
        super(renderManager, RAT_MODEL, 0.15F);
        this.addLayer(new JERLayerRatPlague(this));
        this.addLayer(new JERLayerRatEyes(this));
        this.addLayer(new JERLayerRatHelmet(this));
        this.addLayer(new JERLayerRatHeldItem(this));
    }

    protected boolean canRenderName(EntityRat entity) {
        return RatsMod.PROXY.shouldRenderNameplates() && super.canRenderName(entity);
    }

    protected void renderModel(EntityRat rat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag = this.isVisible(rat);
        boolean flag1 = !flag && !rat.isInvisibleToPlayer(Minecraft.getMinecraft().player);
        if (flag || flag1) {
            if (!this.bindEntityTexture(rat)) {
                return;
            }

            if (flag1) {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            if (rat.isChild()) {
                this.mainModel = PINKIE_MODEL;
            } else {
                this.mainModel = RAT_MODEL;
            }

            this.mainModel.render(rat, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            if (flag1) {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }

    }

    public boolean shouldRender(EntityRat rat, ICamera camera, double camX, double camY, double camZ) {
        return rat.isRiding() && rat.getRidingEntity() != null && rat.getRidingEntity().getPassengers().size() >= 1 && rat.getRidingEntity().getPassengers().get(0) == rat && rat.getRidingEntity() instanceof EntityLivingBase && ((EntityLivingBase)rat.getRidingEntity()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == RatsItemRegistry.CHEF_TOQUE ? false : super.shouldRender(rat, camera, camX, camY, camZ);
    }

    protected void preRenderCallback(EntityRat rat, float partialTickTime) {
        GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
        if (rat.isRiding() && rat.getRidingEntity() != null && rat.getRidingEntity().getPassengers().size() >= 1 && rat.getRidingEntity() instanceof EntityPlayer) {
            Entity riding = rat.getRidingEntity();
            if (riding.getPassengers().get(0) != null && riding.getPassengers().get(0) == rat) {
                Render playerRender = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(riding);
                if (playerRender instanceof RenderLivingBase && ((RenderLivingBase)playerRender).getMainModel() instanceof ModelBiped) {
                    ((ModelBiped)((RenderLivingBase)playerRender).getMainModel()).bipedHead.postRender(0.0625F);
                    GlStateManager.translate(0.0D, -0.699999988079071D, 0.25D);
                }
            }
        } else {
            float var10000 = rat.prevFlyingPitch + (rat.flyingPitch - rat.prevFlyingPitch) * partialTickTime;
            GL11.glRotatef(rat.flyingPitch, 1.0F, 0.0F, 0.0F);
        }

    }

    protected ResourceLocation getEntityTexture(EntityRat entity) {
        String s = entity.getRatTexture();
        if (entity.isChild()) {
            return PINKIE_TEXTURE;
        } else if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_DRAGON)) {
            return DRAGON_UPGRADE_TEXTURE;
        } else if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_ENDER)) {
            return ENDER_UPGRADE_TEXTURE;
        } else if (entity.hasUpgrade(RatsItemRegistry.RAT_UPGRADE_AQUATIC)) {
            return AQUATIC_UPGRADE_TEXTURE;
        } else {
            if (!entity.getCustomNameTag().isEmpty()) {
                label98: {
                    String str = entity.getCustomNameTag();
                    if (!str.contains("julian") && !str.contains("Julian")) {
                        if (!str.contains("shizuka") && !str.contains("Shizuka")) {
                            if (str.contains("sharva") || str.contains("Sharva")) {
                                return SHARVA;
                            }

                            if (str.contains("dino") || str.contains("Dino")) {
                                return DINO;
                            }

                            if (str.contains("ratala") || str.contains("Ratala")) {
                                return RATALA;
                            }

                            if (!str.contains("friar") && !str.contains("Friar")) {
                                if (!str.contains("riddler") && !str.contains("Riddler")) {
                                    if (str.contains("joker") || str.contains("Joker")) {
                                        return JOKER;
                                    }
                                    break label98;
                                }

                                return RIDDLER;
                            }

                            return FRIAR;
                        }

                        return SHIZUKA;
                    }

                    return JULIAN;
                }
            }

            ResourceLocation resourcelocation = (ResourceLocation)LAYERED_LOCATION_CACHE.get(s);
            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s);
                LAYERED_LOCATION_CACHE.put(s, resourcelocation);
            }

            return resourcelocation;
        }
    }

    protected float getDeathMaxRotation(EntityRat rat) {
        return rat.isDeadInTrap ? 0.0F : 90.0F;
    }
}
