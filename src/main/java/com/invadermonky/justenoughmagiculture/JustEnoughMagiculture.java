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
    public static final String MOD_VERSION = "1.12.2-1.1.2";
    public static final String MC_VERSION = "[1.12.2]";
    public static final String DEPENDENCIES = (
            "required-after:" + ConstantIds.JUSTENOUGHITEMS + "@[4.7.0,)" +
            ";required-after:" + ConstantIds.JUSTENOUGHRESOURCES + "@[0.9.2.60,)" +
                    ";after:" + ConstantIds.ANIMANIA +
                    ";after:" + ConstantIds.ATUM +
                    ";after:" + ConstantIds.BEAR_WITH_ME +
                    ";after:" + ConstantIds.BEAST_SLAYER +
                    ";after:" + ConstantIds.BETWEENLANDS +
                    ";after:" + ConstantIds.BEWITCHMENT +
                    ";after:" + ConstantIds.BOTANIA +
                    ";after:" + ConstantIds.CHARM +
                    ";after:" + ConstantIds.CHOCOLATE_QUEST +
                    ";after:" + ConstantIds.EB_WIZARDRY +
                    ";after:" + ConstantIds.EB_WIZARDRY_TF +
                    ";after:" + ConstantIds.EREBUS +
                    ";after:" + ConstantIds.ENDER_IO +
                    ";after:" + ConstantIds.FAMILIAR_FAUNA +
                    ";after:" + ConstantIds.FUTURE_MC +
                    ";after:" + ConstantIds.GRIMOIRE_OF_GAIA +
                    ";after:" + ConstantIds.HARVESTCRAFT +
                    ";after:" + ConstantIds.HARVESTERS_NIGHT +
                    ";after:" + ConstantIds.ICE_AND_FIRE +
                    ";after:" + ConstantIds.INDUSTRIAL_FOREGOING +
                    ";after:" + ConstantIds.MOWZIES_MOBS +
                    ";after:" + ConstantIds.MUTANT_BEASTS +
                    ";after:" + ConstantIds.NETHEREX +
                    ";after:" + ConstantIds.OCEANIC_EXPANSE +
                    ";after:" + ConstantIds.PIZZACRAFT +
                    ";after:" + ConstantIds.QUARK +
                    ";after:" + ConstantIds.RATS +
                    ";after:" + ConstantIds.ROGUELIKE_DUNGEONS +
                    ";after:" + ConstantIds.RUSTIC +
                    ";after:" + ConstantIds.RUSTIC_THAUMATURGY +
                    ";after:" + ConstantIds.SPECIAL_MOBS +
                    ";after:" + ConstantIds.THAUMCRAFT +
                    ";after:" + ConstantIds.THAUMIC_AUGMENTATION +
                    ";after:" + ConstantIds.THERMAL_FOUNDATION +
                    ";after:" + ConstantIds.TWILIGHT_FOREST +
                    ";after:" + ConstantIds.WADDLES +

                    //Mods JEM has to load after because they eat glue.
                    ";after:" + ConstantIds.LIVINGENCHANTMENT
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
