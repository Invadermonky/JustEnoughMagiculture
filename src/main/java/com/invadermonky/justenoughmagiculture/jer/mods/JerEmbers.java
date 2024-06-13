package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import net.minecraft.util.ResourceLocation;
import teamroots.embers.entity.EntityAncientGolem;

public class JerEmbers extends AbstractJerIntegration {
    private static JerEmbers instance;

    public static JerEmbers getInstance() {
        return instance == null ? instance = new JerEmbers() : instance;
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.embers)
            return;

        JERIntegration.mobRegistry.register(new EntityAncientGolem(world), new ResourceLocation("embers:entity/ancient_golem"));
    }
}
