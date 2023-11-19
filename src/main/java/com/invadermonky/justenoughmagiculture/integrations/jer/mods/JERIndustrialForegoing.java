package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.buuz135.industrial.entity.EntityPinkSlime;
import com.buuz135.industrial.proxy.CommonProxy;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigIndustrialForegoing;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.conditionals.LightLevel;

public class JERIndustrialForegoing extends JERBase implements IJERIntegration {
    private final JEMConfigIndustrialForegoing.JER jerConfig = JEMConfig.INDUSTRIAL_FOREGOING.JUST_ENOUGH_RESOURCES;

    public JERIndustrialForegoing(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enablePinkSlime) {
            registerMob(new EntityPinkSlime(world), LightLevel.any, new String[] {"None"}, CommonProxy.PINK_SLIME_LOOT);
        }
    }
}
