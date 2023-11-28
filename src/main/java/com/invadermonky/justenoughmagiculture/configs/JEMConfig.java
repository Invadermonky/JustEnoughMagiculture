package com.invadermonky.justenoughmagiculture.configs;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import com.invadermonky.justenoughmagiculture.configs.mods.*;
import com.invadermonky.justenoughmagiculture.util.ModIds.ConstantNames;
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


    @Comment(ConstantNames.ANIMANIA_EXTRA + " JER integration configuration.")
    public static final JEMConfigAnimaniaExtra ANIMANIA_EXTRA = new JEMConfigAnimaniaExtra();

    @Comment(ConstantNames.ANIMANIA_FARM + " JER integration configuration.")
    public static final JEMConfigAnimaniaFarm ANIMANIA_FARM = new JEMConfigAnimaniaFarm();

    @Comment(ConstantNames.ATUM + " JER integration configuration.")
    public static final JEMConfigAtum ATUM = new JEMConfigAtum();

    @Comment(ConstantNames.BEARWITHME + " JER integration configuration.")
    public static final JEMConfigBearWithMe BEAR_WITH_ME = new JEMConfigBearWithMe();

    @Comment(ConstantNames.BEASTSLAYER + " JER integration configuration.")
    public static final JEMConfigBeastSlayer BEAST_SLAYER = new JEMConfigBeastSlayer();

    @Comment(ConstantNames.BEWITCHMENT + " JER integration configuration.")
    public static final JEMConfigBewitchment BEWITCHMENT = new JEMConfigBewitchment();

    @Comment(ConstantNames.BOTANIA + " JER integration configuration.")
    public static final JEMConfigBotania BOTANIA = new JEMConfigBotania();

    @Comment(ConstantNames.CHARM + " JER integration configuration.")
    public static final JEMConfigCharm CHARM = new JEMConfigCharm();

    @Comment(ConstantNames.EBWIZARDRY + " JER integration configuration.")
    public static final JEMConfigEBWizardry EB_WIZARDRY = new JEMConfigEBWizardry();

    @Comment(ConstantNames.EBWIZARDRYTF + " JER integration configuration.")
    public static final JEMConfigEBWizardryTF EB_WIZARDRY_TF = new JEMConfigEBWizardryTF();

    @Comment(ConstantNames.ENDERIO + " JER integration configuration.")
    public static final JEMConfigEnderIO ENDER_IO = new JEMConfigEnderIO();

    @Comment(ConstantNames.FAMILIARFAUNA + " JER integration configuration.")
    public static final JEMConfigFamiliarFauna FAMILIAR_FAUNA = new JEMConfigFamiliarFauna();

    @Comment(ConstantNames.FUTUREMC + " JER integration configuration.")
    public static final JEMConfigFutureMC FUTURE_MC = new JEMConfigFutureMC();

    @Comment(ConstantNames.GRIMOIREOFGAIA + " JER integration configuration.")
    public static final JEMConfigGrimoireOfGaia GRIMOIRE_OF_GAIA = new JEMConfigGrimoireOfGaia();

    @Comment(ConstantNames.HARVESTCRAFT + " JER integration configuration.")
    public static final JEMConfigHarvestcraft HARVESTCRAFT = new JEMConfigHarvestcraft();

    @Comment(ConstantNames.HARVESTERSNIGHT + " JER integration configuration.")
    public static final JEMConfigHarvestersNight HARVESTERSNIGHT = new JEMConfigHarvestersNight();

    @Comment(ConstantNames.INDUSTRIALFOREGOING + " JER integration configuration.")
    public static final JEMConfigIndustrialForegoing INDUSTRIAL_FOREGOING = new JEMConfigIndustrialForegoing();

    @Comment("Minecraft JER integration configuration.")
    public static final JEMConfigMinecraft MINECRAFT = new JEMConfigMinecraft();

    @Comment(ConstantNames.MOWZIESMOBS + " JER integration configuration.")
    public static final JEMConfigMowziesMobs MOWZIES_MOBS = new JEMConfigMowziesMobs();

    @Comment(ConstantNames.NETHEREX + " JER integration configuration.")
    public static final JEMConfigNetherEx NETHEREX = new JEMConfigNetherEx();

    @Comment(ConstantNames.OCEANICEXPANSE + " JER integration configuration.")
    public static final JEMConfigOceanicExpanse OCEANIC_EXPANSE = new JEMConfigOceanicExpanse();

    @Comment(ConstantNames.PIZZACRAFT + " JER integration configuration.")
    public static final JEMConfigPizzacraft PIZZACRAFT = new JEMConfigPizzacraft();

    @Comment(ConstantNames.QUARK + " JER integration configuration.")
    public static final JEMConfigQuark QUARK = new JEMConfigQuark();

    @Comment(ConstantNames.RATS + " JER integration configuration.")
    public static final JEMConfigRats RATS = new JEMConfigRats();

    @Comment(ConstantNames.ROGUELIKEDUNGEONS + " JER integration configuration.")
    public static final JEMConfigRoguelikeDungeons ROGUELIKE_DUNGEONS = new JEMConfigRoguelikeDungeons();

    @Comment(ConstantNames.RUSTIC + " JER integration configuration.")
    public static final JEMConfigRustic RUSTIC = new JEMConfigRustic();

    @Comment(ConstantNames.RUSTICTHAUMATURGY + " JER integration configuration.")
    public static final JEMConfigRusticThaumaturgy RUSTIC_THAUMATURGY = new JEMConfigRusticThaumaturgy();

    @Comment(ConstantNames.THAUMCRAFT + " JER integration configuration.")
    public static final JEMConfigThaumcraft THAUMCRAFT = new JEMConfigThaumcraft();

    @Comment(ConstantNames.SPECIALMOBS + " JER integration configuration.")
    public static final JEMConfigSpecialMobs SPECIAL_MOBS = new JEMConfigSpecialMobs();

    @Comment(ConstantNames.THAUMICAUGMENTATION + " JER integration configuration.")
    public static final JEMConfigThaumicAugmentation THAUMIC_AUGMENTATION = new JEMConfigThaumicAugmentation();

    @Comment(ConstantNames.THERMALFOUNDATION + " JER integration configuration.")
    public static final JEMConfigThermalFoundation THERMAL_FOUNDATION = new JEMConfigThermalFoundation();

    @Comment(ConstantNames.TWILIGHTFOREST + " JER integration configuration.")
    public static final JEMConfigTwilightForest TWILIGHT_FOREST = new JEMConfigTwilightForest();

    @Comment(ConstantNames.WADDLES + " JER integration configuration.")
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
