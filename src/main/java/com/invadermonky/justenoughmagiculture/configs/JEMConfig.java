package com.invadermonky.justenoughmagiculture.configs;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.mods.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@LangKey("config." + JustEnoughMagiculture.MOD_ALIAS + ":" + JustEnoughMagiculture.MOD_ALIAS)
@Config(
        modid = JustEnoughMagiculture.MOD_ID,
        name = JustEnoughMagiculture.MOD_ID
)
public class JEMConfig {
    private static final String LANG_KEY = "config." + JustEnoughMagiculture.MOD_ALIAS + ":";

    @RequiresMcRestart
    @LangKey(LANG_KEY + "jeimodule")
    @Comment("JEM Just Enough Items general settings.")
    public static ModuleJEI MODULE_JEI = new ModuleJEI();
    public static class ModuleJEI {
        public boolean enableLootBagCategory = true;
    }

    @RequiresMcRestart
    @LangKey(LANG_KEY + "jermodule")
    @Comment("JEM Just Enough Resources general settings.")
    public static ModuleJER MODULE_JER = new ModuleJER();
    public static class ModuleJER {
        @Comment("Enables the JER spawn biome integration. Setting this to false will cause all newly registered JER mobs to display \"Any\" for spawn biomes.")
        public boolean enableJERSpawnBiomes = true;
        @Comment("Enables entity render overrides. Disabling this may cause certain entities to no longer render in JER.")
        public boolean enableRenderOverrides = true;
    }


    @Comment("Bear With Me JER integration configuration.")
    public static final JEMConfigAnimaniaExtra ANIMANIA_EXTRA = new JEMConfigAnimaniaExtra();

    @Comment("Bear With Me JER integration configuration.")
    public static final JEMConfigAnimaniaFarm ANIMANIA_FARM = new JEMConfigAnimaniaFarm();

    @Comment("Bear With Me JER integration configuration.")
    public static final JEMConfigBearWithMe BEAR_WITH_ME = new JEMConfigBearWithMe();

    @Comment("Bewitchment JER integration configuration.")
    public static final JEMConfigBewitchment BEWITCHMENT = new JEMConfigBewitchment();

    @Comment("Botania JER integration configuration.")
    public static final JEMConfigBotania BOTANIA = new JEMConfigBotania();

    @Comment("Charm JER integration configuration.")
    public static final JEMConfigCharm CHARM = new JEMConfigCharm();

    @Comment("Ender IO JER integration configuration.")
    public static final JEMConfigEnderIO ENDER_IO = new JEMConfigEnderIO();

    @Comment("Familiar Fauna JER integration configuration.")
    public static final JEMConfigFamiliarFauna FAMILIAR_FAUNA = new JEMConfigFamiliarFauna();

    @Comment("Future MC JER integration configuration.")
    public static final JEMConfigFutureMC FUTURE_MC = new JEMConfigFutureMC();

    @Comment("Grimoire of Gaia JER integration configuration.")
    public static final JEMConfigGrimoireOfGaia GRIMOIRE_OF_GAIA = new JEMConfigGrimoireOfGaia();

    @Comment("Industrial Foregoing JER integration configuration.")
    public static final JEMConfigHarvestcraft HARVESTCRAFT = new JEMConfigHarvestcraft();

    @Comment("Industrial Foregoing JER integration configuration.")
    public static final JEMConfigIndustrialForegoing INDUSTRIAL_FOREGOING = new JEMConfigIndustrialForegoing();

    @Comment("Minecraft JER integration configuration.")
    public static final JEMConfigMinecraft MINECRAFT = new JEMConfigMinecraft();

    @Comment("NetherEx JER integration configuration.")
    public static final JEMConfigNetherEx NETHEREX = new JEMConfigNetherEx();

    @Comment("Oceanic Expanse JER integration configuration.")
    public static final JEMConfigOceanicExpanse OCEANIC_EXPANSE = new JEMConfigOceanicExpanse();

    @Comment("Pizzacraft JER integration configuration.")
    public static final JEMConfigPizzacraft PIZZACRAFT = new JEMConfigPizzacraft();

    @Comment("Quark JER integration configuration.")
    public static final JEMConfigQuark QUARK = new JEMConfigQuark();

    @Comment("Roguelike Dungeons JER integration configuration.")
    public static final JEMConfigRoguelikeDungeons ROGUELIKE_DUNGEONS = new JEMConfigRoguelikeDungeons();

    @Comment("Rustic JER integration configuration.")
    public static final JEMConfigRustic RUSTIC = new JEMConfigRustic();

    @Comment("Rustic Thaumaturgy JER integration configuration.")
    public static final JEMConfigRusticThaumaturgy RUSTIC_THAUMATURGY = new JEMConfigRusticThaumaturgy();

    @Comment("Thaumcraft JER integration configuration.")
    public static final JEMConfigThaumcraft THAUMCRAFT = new JEMConfigThaumcraft();

    @Comment("Thaumic Augmentation JER integration configuration.")
    public static final JEMConfigThaumicAugmentation THAUMIC_AUGMENTATION = new JEMConfigThaumicAugmentation();

    @Comment("Thermal Foundation JER integration configuration.")
    public static final JEMConfigThermalFoundation THERMAL_FOUNDATION = new JEMConfigThermalFoundation();

    @Comment("Twilight Forest JER integration configuration.")
    public static final JEMConfigTwilightForest TWILIGHT_FOREST = new JEMConfigTwilightForest();

    @Comment("Waddles JER integration configuration.")
    public static final JEMConfigWaddles WADDLES = new JEMConfigWaddles();




    @Mod.EventBusSubscriber(modid = JustEnoughMagiculture.MOD_ID)
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(JustEnoughMagiculture.MOD_ID))
                ConfigManager.sync(JustEnoughMagiculture.MOD_ID, Type.INSTANCE);
        }
    }
}
