package com.invadermonky.justenoughmagiculture.init;

import com.animania.common.handler.AddonHandler;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jer.mods.*;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import jeresources.api.*;
import jeresources.util.LootTableHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.invadermonky.justenoughmagiculture.configs.JEMConfig.*;

@SideOnly(Side.CLIENT)
public class InitIntegration {
    @JERPlugin
    public static IJERAPI jerapi;
    public static IDungeonRegistry dungeonRegistry;
    public static IMobRegistry mobRegistry;
    public static IPlantRegistry plantRegistry;
    public static World world;
    public static LootTableManager manager;

    public static void init() {
        dungeonRegistry = jerapi.getDungeonRegistry();
        mobRegistry = jerapi.getMobRegistry();
        plantRegistry = jerapi.getPlantRegistry();
        world = Minecraft.getMinecraft().world != null ? Minecraft.getMinecraft().world : jerapi.getWorld();
        manager = LootTableHelper.getManager(world);

        initJERMods();
    }

    public static void jeiInit() {
        registerLootBags();
    }

    private static void initJERMods() {
        /* Zee Minecraft is special boi */ new JERMinecraft(MINECRAFT.enableJERMobs, MINECRAFT.enableJERPlants);

        if(ModIds.ANIMANIA.isLoaded) {
            if(AddonHandler.isAddonLoaded(ModIds.ConstantIds.ANIMANIA_EXTRA)) new JERAnimaniaExtra(ANIMANIA_EXTRA.enableJERMobs);
            if(AddonHandler.isAddonLoaded(ModIds.ConstantIds.ANIMANIA_FARM)) new JERAnimaniaFarm(ANIMANIA_FARM.enableJERMobs);
        }
        if(ModIds.BEARWITHME.isLoaded) new JERBearWithMe(BEAR_WITH_ME.enableJERMobs);
        if(ModIds.BEWITCHMENT.isLoaded) new JERBewitchment(BEWITCHMENT.enableJERMobs, BEWITCHMENT.enableJERPlants, BEWITCHMENT.enableJERVillagers);
        if(ModIds.BOTANIA.isLoaded) new JERBotania(BOTANIA.enableJERMobs);
        if(ModIds.CHARM.isLoaded) new JERCharm(CHARM.enableJERDungeons, CHARM.enableJERMobs);
        if(ModIds.EBWIZARDRY.isLoaded) new JEREBWizardry(EB_WIZARDRY.enableJERDungeons, EB_WIZARDRY.enableJERMobs);
        if(ModIds.EBWIZARDRYTF.isLoaded) new JEREBWizardryTF(EB_WIZARDRY_TF.enableJERMobs);
        if(ModIds.FAMILIARFAUNA.isLoaded) new JERFamiliarFauna(FAMILIAR_FAUNA.enableJERMobs);
        if(ModIds.FUTUREMC.isLoaded) new JERFutureMC(FUTURE_MC.enableJERMobs, FUTURE_MC.enableJERPlants);
        if(ModIds.GRIMOIREOFGAIA.isLoaded) new JERGrimoireOfGaia(GRIMOIRE_OF_GAIA.enableJERMobs);
        if(ModIds.HARVESTCRAFT.isLoaded) new JERHarvestcraft(HARVESTCRAFT.enableJERPlants);
        if(ModIds.HARVESTERSNIGHT.isLoaded) new JERHarvestersNight(HARVESTERSNIGHT.enableJERMobs);
        if(ModIds.INDUSTRIALFOREGOING.isLoaded) new JERIndustrialForegoing(INDUSTRIAL_FOREGOING.enableJERMobs);
        if(ModIds.NETHEREX.isLoaded) new JERNetherEx(NETHEREX.enableJERDungeons, NETHEREX.enableJERMobs, NETHEREX.enableJERVillagers);
        if(ModIds.OCEANICEXPANSE.isLoaded) new JEROceanicExpanse(OCEANIC_EXPANSE.enableJERDungeons, OCEANIC_EXPANSE.enableJERMobs);
        if(ModIds.PIZZACRAFT.isLoaded) new JERPizzacraft(PIZZACRAFT.enableJERPlants);
        if(ModIds.QUARK.isLoaded) new JERQuark(QUARK.enableJERDungeons, QUARK.enableJERMobs, QUARK.enableJERVillagers);
        if(ModIds.RATS.isLoaded) new JERRats(RATS.enableJERDungeons, RATS.enableJERMobs);
        /* TODO: This is a massive amount of work and I'm not sure I want to go through with it. */ if(ModIds.ROGUELIKEDUNGEONS.isLoaded) new JERRoguelikeDungeons(ROGUELIKE_DUNGEONS.enableJERDungeons);
        if(ModIds.RUSTIC.isLoaded) new JERRustic(RUSTIC.enableJERPlants);
        if(ModIds.RUSTICTHAUMATURGY.isLoaded) new JERRusticThaumaturgy(RUSTIC_THAUMATURGY.enableJERPlants);
        if(ModIds.THAUMCRAFT.isLoaded) new JERThaumcraft(THAUMCRAFT.enableJERDungeons, THAUMCRAFT.enableJERMobs);
        if(ModIds.THAUMICAUGMENTATION.isLoaded) new JERThaumicAugmentation(THAUMIC_AUGMENTATION.enableJERMobs);
        if(ModIds.THERMALFOUNDATION.isLoaded) new JERThermalFoundation(THERMAL_FOUNDATION.enableJERMobs);
        if(ModIds.TWILIGHTFOREST.isLoaded) new JERTwilightForest(TWILIGHT_FOREST.enableJERDungeons, TWILIGHT_FOREST.enableJERMobs);
        if(ModIds.WADDLES.isLoaded) new JERWaddles(WADDLES.enableJERMobs);
    }

    private static void registerLootBags() {
        if(ModIds.THAUMCRAFT.isLoaded) JERThaumcraft.getInstance().registerLootBagEntries();
        if(ModIds.THAUMICAUGMENTATION.isLoaded) JERThaumicAugmentation.getInstance().registerLootBagEntries();
    }

    /**
     * This is used to inject LootDrops into mobs added by JER later in the load process.
     */
    public static void lateInit() {
        if(ModIds.CHARM.isLoaded) JERCharm.getInstance().injectLoot();
        if(ModIds.ENDERIO.isLoaded) JEREnderIO.getInstance().injectLoot();
        if(ModIds.HARVESTCRAFT.isLoaded) JERHarvestcraft.getInstance().injectLoot();
        if(ModIds.QUARK.isLoaded) JERQuark.getInstance().injectLoot();
    }

    public static void registerRenderOverrides() {
        if(!JEMConfig.MODULE_JER.enableRenderOverrides)
            return;

        if(ModIds.ANIMANIA.isLoaded) {
            if(AddonHandler.isAddonLoaded(ModIds.ConstantIds.ANIMANIA_EXTRA)) {
                JERAnimaniaExtra.getInstance().registerRenderOverrides();
            }
            if(AddonHandler.isAddonLoaded(ModIds.ConstantIds.ANIMANIA_FARM)) {
                JERAnimaniaFarm.getInstance().registerRenderOverrides();
            }
        }
    }
}
