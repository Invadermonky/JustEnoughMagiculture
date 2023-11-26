package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.conditionals.LightLevel;
import lykrast.harvestersnight.common.EntityHarvester;
import net.minecraft.client.renderer.GlStateManager;

public class JERHarvestersNight extends JERBase implements IJERIntegration {
    public JERHarvestersNight(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(JEMConfig.HARVESTERSNIGHT.JUST_ENOUGH_RESOURCES.enableHarvester) {
            registerMob(new EntityHarvester(world), LightLevel.hostile, EntityHarvester.LOOT);
            registerRenderHook(EntityHarvester.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05, -0.65, 0);
                GlStateManager.scale(1.2, 1.2, 1.2);
                return renderInfo;
            }));
        }
    }
}
