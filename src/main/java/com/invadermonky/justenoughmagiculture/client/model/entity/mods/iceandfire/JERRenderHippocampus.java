package com.invadermonky.justenoughmagiculture.client.model.entity.mods.iceandfire;

import com.github.alexthe666.iceandfire.client.render.entity.RenderHippocampus;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.iceandfire.JERModelHippocampus;
import net.minecraft.client.renderer.entity.RenderManager;

public class JERRenderHippocampus extends RenderHippocampus {
    public JERRenderHippocampus(RenderManager renderManager) {
        super(renderManager);
        this.mainModel = new JERModelHippocampus();
    }
}
