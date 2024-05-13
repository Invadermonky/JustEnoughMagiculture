package com.invadermonky.justenoughmagiculture.proxy;

import com.invadermonky.justenoughmagiculture.init.InitIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        InitIntegration.registerRenderOverrides();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        InitIntegration.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
