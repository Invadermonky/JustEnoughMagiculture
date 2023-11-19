package com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.chickens;

import com.animania.addons.farm.client.render.chickens.RenderHenBase;
import com.animania.addons.farm.common.entity.chickens.EntityHenBase;
import com.animania.common.handler.BlockHandler;
import jeresources.util.FakeClientWorld;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderHenBaseJER extends RenderHenBase {
    public static final RenderHenBaseJER.Factory FACTORY = new RenderHenBaseJER.Factory();

    public RenderHenBaseJER(RenderManager rm) {
        super(rm);
    }

    @Override
    protected void preRenderScale(EntityHenBase entity, float f) {
        GL11.glScalef(0.9F, 0.9F, 0.9F);

        boolean isNest = false;
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        BlockPos pos = new BlockPos(x, y, z);
        if(entity.world != null && !(entity.world instanceof FakeClientWorld)) {
            Block blockchk = entity.world.getBlockState(pos).getBlock();
            isNest = blockchk != BlockHandler.blockNest;
        }

        if (isNest && !entity.getSleeping()) {
            this.shadowSize = 0.3F;
            entity.setSleeping(false);
        } else {
            GlStateManager.translate(-0.25F, 0.35F, -0.25F);
            this.shadowSize = 0.0F;
        }
    }

    static class Factory<T extends RenderHenBaseJER> implements IRenderFactory<EntityHenBase> {
        Factory() {}

        public Render<? super EntityHenBase> createRenderFor(RenderManager manager) {
            return new RenderHenBaseJER(manager);
        }
    }

}
