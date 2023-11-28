package com.invadermonky.justenoughmagiculture;

import com.invadermonky.justenoughmagiculture.proxy.CommonProxy;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantIds;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = JustEnoughMagiculture.MOD_ID,
        name = JustEnoughMagiculture.MOD_NAME,
        version = JustEnoughMagiculture.MOD_VERSION,
        acceptedMinecraftVersions = JustEnoughMagiculture.MC_VERSION,
        dependencies = JustEnoughMagiculture.DEPENDENCIES,
        clientSideOnly = true
)
public class JustEnoughMagiculture {
    public static final String MOD_ID = "justenoughmagiculture";
    public static final String MOD_ALIAS = "jem";
    public static final String MOD_NAME = "Just Enough Magiculture";
    public static final String MOD_VERSION = "1.12.2-1.1.0";
    public static final String MC_VERSION = "[1.12.2]";
    public static final String DEPENDENCIES = (
            "required-after:" + ConstantIds.JUSTENOUGHITEMS + "@[4.7.0,)" +
            ";required-after:" + ConstantIds.JUSTENOUGHRESOURCES + "@[0.9.2.60,)" +
                    ";after:" + ConstantIds.ANIMANIA +
                    ";after:" + ConstantIds.ATUM +
                    ";after:" + ConstantIds.BEARWITHME +
                    ";after:" + ConstantIds.BEASTSLAYER +
                    ";after:" + ConstantIds.BEWITCHMENT +
                    ";after:" + ConstantIds.BOTANIA +
                    ";after:" + ConstantIds.CHARM +
                    ";after:" + ConstantIds.EBWIZARDRY +
                    ";after:" + ConstantIds.EBWIZARDRYTF +
                    ";after:" + ConstantIds.ENDERIO +
                    ";after:" + ConstantIds.FAMILIARFAUNA +
                    ";after:" + ConstantIds.FUTUREMC +
                    ";after:" + ConstantIds.GRIMOIREOFGAIA +
                    ";after:" + ConstantIds.HARVESTCRAFT +
                    ";after:" + ConstantIds.HARVESTERSNIGHT +
                    ";after:" + ConstantIds.INDUSTRIALFOREGOING +
                    ";after:" + ConstantIds.MOWZIESMOBS +
                    ";after:" + ConstantIds.NETHEREX +
                    ";after:" + ConstantIds.OCEANICEXPANSE +
                    ";after:" + ConstantIds.PIZZACRAFT +
                    ";after:" + ConstantIds.QUARK +
                    ";after:" + ConstantIds.RATS +
                    ";after:" + ConstantIds.ROGUELIKEDUNGEONS +
                    ";after:" + ConstantIds.RUSTIC +
                    ";after:" + ConstantIds.RUSTICTHAUMATURGY +
                    ";after:" + ConstantIds.THAUMCRAFT +
                    ";after:" + ConstantIds.THAUMICAUGMENTATION +
                    ";after:" + ConstantIds.THERMALFOUNDATION +
                    ";after:" + ConstantIds.TWILIGHTFOREST +
                    ";after:" + ConstantIds.WADDLES
    );

    public static final String ProxyClientClass = "com.invadermonky.justenoughmagiculture.proxy.ClientProxy";
    public static final String ProxyServerClass = "com.invadermonky.justenoughmagiculture.proxy.CommonProxy";

    @Mod.Instance(MOD_ID)
    public static JustEnoughMagiculture INSTANCE;

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
