package com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.pigs;

import com.animania.Animania;
import com.animania.addons.farm.client.render.pigs.RenderHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot;
import com.animania.common.handler.BlockHandler;
import jeresources.util.FakeClientWorld;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderHogOldSpotJER extends RenderHogOldSpot {
    public static final RenderHogOldSpotJER.Factory FACTORY = new RenderHogOldSpotJER.Factory();

    public RenderHogOldSpotJER(RenderManager rm) {
        super(rm);
    }

    @Override
    protected void preRenderScale(PigOldSpot.EntityHogOldSpot entity, float f) {
        GL11.glScalef(1.18F, 1.18F, 1.18F);

        if (entity.getSleeping()) {
            this.shadowSize = 0.0F;
            float sleepTimer = entity.getSleepTimer();
            if (entity.getRNG().nextInt(2) < 1 && sleepTimer > -0.55F) {
                sleepTimer -= 0.01F;
            }
            entity.setSleepTimer(sleepTimer);
            GlStateManager.translate(0.0F, entity.height - 1.25F, 0.0F);
            GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
        } else {
            entity.setSleeping(false);
            entity.setSleepTimer(0.0F);
            double x = entity.posX;
            double y = entity.posY;
            double z = entity.posZ;
            BlockPos pos = new BlockPos(x, y, z);

            boolean mudBlock = false;
            if(entity.world != null && !(entity.world instanceof FakeClientWorld)) {
                Block blockchk = entity.world.getBlockState(pos).getBlock();
                Block blockchk2 = entity.world.getBlockState(pos).getBlock();
                if (blockchk == BlockHandler.blockMud || blockchk.getTranslationKey().contains("tile.mud") || blockchk2.getTranslationKey().contains("tile.mud")) {
                    mudBlock = true;
                }
            }

            if (mudBlock && !entity.getMuddy()) {
                this.shadowSize = 0.0F;
                GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
                GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
                entity.setMuddy(true);
                entity.setMudTimer(1.0F);
                entity.setSplashTimer(1.0F);
            } else if (entity.isWet() && entity.getMuddy() && !mudBlock) {
                this.shadowSize = 0.5F;
                entity.setMuddy(false);
                entity.setMudTimer(0.0F);
                entity.setSplashTimer(0.0F);
            } else if (mudBlock) {
                Float splashTimer = entity.getSplashTimer();
                GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
                GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
                splashTimer = splashTimer - 0.045F;
                entity.setSplashTimer(splashTimer);
                if (splashTimer <= 0.0F) {
                    entity.setMuddy(true);
                    entity.setMudTimer(1.0F);
                }
            } else if (entity.getMudTimer() > 0.0F) {
                this.shadowSize = 0.5F;
                entity.setMuddy(false);
                float mudTimer = entity.getMudTimer();
                if (Animania.RANDOM.nextInt(3) < 1) {
                    mudTimer -= 0.0025F;
                    entity.setMudTimer(mudTimer);
                }
            }
        }
    }

    static class Factory<T extends PigOldSpot.EntityHogOldSpot> implements IRenderFactory<T> {
        Factory() {}

        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderHogOldSpotJER(manager);
        }
    }
}
