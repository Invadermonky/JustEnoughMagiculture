package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigBearWithMe;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.mrtrollnugnug.bearwithme.common.entity.EntityBlackBear;
import com.mrtrollnugnug.bearwithme.common.entity.EntityGrizzlyBear;
import com.mrtrollnugnug.bearwithme.common.entity.EntityPandaBear;
import com.mrtrollnugnug.bearwithme.handler.ConfigurationHandler;
import com.mrtrollnugnug.bearwithme.handler.ContentHandler;
import jeresources.api.conditionals.LightLevel;
import net.minecraftforge.common.BiomeDictionary.Type;

public class JERBearWithMe extends JERBase implements IJERIntegration {
    private final JEMConfigBearWithMe.JER jerConfig = JEMConfig.BEAR_WITH_ME.JUST_ENOUGH_RESOURCES;

    public JERBearWithMe(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableBlackBear && ConfigurationHandler.getSpawnRateBlack() > 0) {
            registerMob(new EntityBlackBear(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.FOREST, Type.SPOOKY), ContentHandler.ENTITIES_BLACK_BEAR);
        }

        if(jerConfig.enableGrizzlyBear && ConfigurationHandler.getSpawnRateGrizzly() > 0) {
            registerMob(new EntityGrizzlyBear(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.PLAINS, Type.HILLS), ContentHandler.ENTITIES_GRIZZLY_BEAR);
        }

        if(jerConfig.enablePandaBear && ConfigurationHandler.getSpawnRatePanda() > 0) {
            registerMob(new EntityPandaBear(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.JUNGLE, Type.MAGICAL), ContentHandler.ENTITIES_PANDA_BEAR);
        }
    }
}
