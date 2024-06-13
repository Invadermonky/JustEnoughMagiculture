package com.invadermonky.justenoughmagiculture;

import com.invadermonky.justenoughmagiculture.proxy.CommonProxy;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = JustEnoughMagiculture.MOD_ID,
        name = JustEnoughMagiculture.MOD_NAME,
        version = JustEnoughMagiculture.VERSION,
        acceptedMinecraftVersions = JustEnoughMagiculture.MC_VERSIONS,
        dependencies = JustEnoughMagiculture.DEPENDENCIES,
        clientSideOnly = true
)
public class JustEnoughMagiculture {
    public static final String MOD_ID = "justenoughmagiculture";
    public static final String MOD_NAME = "Just Enough Magiculture";
    public static final String VERSION = "1.10.2-1.0.0";
    public static final String MC_VERSIONS = "[1.10.2]";
    public static final String DEPENDENCIES =
            "required-after:JEI" +
            ";required-after:jeresources";

    public static final String ProxyClientClass = "com.invadermonky.justenoughmagiculture.proxy.ClientProxy";
    public static final String ProxyServerClass = "com.invadermonky.justenoughmagiculture.proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static JustEnoughMagiculture instance;

    @SidedProxy(modId = MOD_ID, clientSide = ProxyClientClass, serverSide = ProxyServerClass)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Starting " + MOD_NAME);
        proxy.preInit(event);
        LogHelper.debug("Finished preInit phase.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        LogHelper.debug("Finished init phase.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        LogHelper.debug("Finished postInit phase.");
    }
}
