package com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats;

import com.github.alexthe666.rats.server.entity.EntityRat;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;

public class JERRenderPirat extends JERRenderRat {
    public JERRenderPirat(RenderManager manager) {
        super(manager);
    }

    protected void preRenderCallback(EntityRat rat, float partialTickTime) {
        super.preRenderCallback(rat, partialTickTime);
        GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
    }
}
