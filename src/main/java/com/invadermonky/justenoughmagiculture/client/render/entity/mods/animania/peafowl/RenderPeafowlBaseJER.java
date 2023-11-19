package com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.peafowl;

import com.animania.addons.extra.client.render.peafowl.RenderPeafowlBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.common.handler.BlockHandler;
import jeresources.util.FakeClientWorld;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderPeafowlBaseJER extends RenderPeafowlBase {
    public static final RenderPeafowlBaseJER.Factory FACTORY = new RenderPeafowlBaseJER.Factory();

    public RenderPeafowlBaseJER(RenderManager rm) {
        super(rm);
    }

    @Override
    protected void preRenderScale(EntityPeafowlBase entity, float f) {
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
            GlStateManager.translate(-0.25F, 0.45F, -0.45F);
            this.shadowSize = 0.0F;
        }
    }

    static class Factory<T extends EntityPeafowlBase> implements IRenderFactory<T> {
        Factory() {}
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderPeafowlBaseJER(manager);
        }
    }
}
