package com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.rabbits;

import com.animania.addons.extra.client.render.rabbits.RenderDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana;
import jeresources.util.FakeClientWorld;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class JERRenderDoeHavana extends RenderDoeHavana {
    public static final JERRenderDoeHavana.Factory FACTORY = new JERRenderDoeHavana.Factory();

    public JERRenderDoeHavana(RenderManager rm) {
        super(rm);
    }

    @Override
    protected void preRenderScale(RabbitHavana.EntityRabbitDoeHavana entity, float f) {
        if (entity.getCustomNameTag().equals("Killer")) {
            GlStateManager.scale(0.7D, 0.7D, 0.7D);
        } else {
            GL11.glScalef(0.59F, 0.59F, 0.59F);
        }
        GL11.glTranslatef(0.0F, 0.0F, -0.5F);

        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        BlockPos pos = new BlockPos(x, y, z);
        if (entity.world != null && !(entity.world instanceof FakeClientWorld)) {
            Block blockchk = entity.world.getBlockState(pos).getBlock();
        }

        if (entity.getSleeping()) {
            this.shadowSize = 0.0F;
            GlStateManager.translate(-0.25F, 0.25F, -0.25F);
        } else {
            this.shadowSize = 0.25F;
            entity.setSleeping(false);

        }
    }

    static class Factory<T extends RabbitHavana.EntityRabbitDoeHavana> implements IRenderFactory<T> {
        Factory() {}

        public Render<? super T> createRenderFor(RenderManager manager) {
            return new JERRenderDoeHavana(manager);
        }
    }
}
