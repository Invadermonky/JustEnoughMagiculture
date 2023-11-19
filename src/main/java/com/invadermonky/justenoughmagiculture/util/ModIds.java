package com.invadermonky.justenoughmagiculture.util;

import cofh.thermalfoundation.ThermalFoundation;
import com.animania.Animania;
import com.bewitchment.Bewitchment;
import com.github.voxelfriend.rusticthaumaturgy.core.RusticThaumaturgy;
import com.sirsquidly.oe.Main;
import com.tage.crimson_warfare.CrimsonWarfare;
import com.tiviacz.pizzacraft.PizzaCraft;
import crazypants.enderio.base.EnderIO;
import familiarfauna.core.FamiliarFauna;
import gaia.GaiaReference;
import jeresources.reference.Reference;
import logictechcorp.libraryex.LibraryEx;
import logictechcorp.netherex.NetherEx;
import mezz.jei.config.Constants;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;
import rustic.core.Rustic;
import svenhjol.charm.Charm;
import thaumcraft.Thaumcraft;
import thecodex6824.thaumicaugmentation.api.ThaumicAugmentationAPI;
import thedarkcolour.futuremc.FutureMC;
import twilightforest.TwilightForestMod;
import vazkii.quark.base.lib.LibMisc;

import javax.annotation.Nullable;

public enum ModIds {
    ANIMANIA(ConstantIds.ANIMANIA, ConstantNames.ANIMANIA),
    ANIMANIA_EXTRA(ConstantIds.ANIMANIA_EXTRA, ConstantNames.ANIMANIA_EXTRA),
    ANIMANIA_FARM(ConstantIds.ANIMANIA_FARM, ConstantNames.ANIMANIA_FARM),
    BEARWITHME(ConstantIds.BEARWITHME, ConstantNames.BEARWITHME),
    BEWITCHMENT(ConstantIds.BEWITCHMENT, ConstantNames.BEWITCHMENT),
    BOTANIA(ConstantIds.BOTANIA, ConstantNames.BOTANIA),
    CHARM(ConstantIds.CHARM , ConstantNames.CHARM),
    CRIMSONWARFARE(ConstantIds.CRIMSONWARFARE , ConstantNames.CRIMSONWARFARE),
    ENDERIO(ConstantIds.ENDERIO, ConstantNames.ENDERIO),
    FAMILIARFAUNA(ConstantIds.FAMILIARFAUNA, ConstantNames.FAMILIARFAUNA),
    FUTUREMC(ConstantIds.FUTUREMC, ConstantNames.FUTUREMC),
    GRIMOIREOFGAIA(ConstantIds.GRIMOIREOFGAIA, ConstantNames.GRIMOIREOFGAIA),
    INDUSTRIALFOREGOING(ConstantIds.INDUSTRIALFOREGOING, ConstantNames.INDUSTRIALFOREGOING),
    HARVESTCRAFT(ConstantIds.HARVESTCRAFT, ConstantNames.HARVESTCRAFT),
    JUSTENOUGHITEMS(ConstantIds.JUSTENOUGHITEMS, ConstantNames.JUSTENOUGHITEMS),
    JUSTENOUGHRESOURCES(ConstantIds.JUSTENOUGHRESOURCES, ConstantNames.JUSTENOUGHRESOURCES),
    NETHEREX(ConstantIds.NETHEREX, ConstantNames.NETHEREX),
    OCEANICEXPANSE(ConstantIds.OCEANICEXPANSE, ConstantNames.OCEANICEXPANSE),
    QUARK(ConstantIds.QUARK, ConstantNames.QUARK),
    PIZZACRAFT(ConstantIds.PIZZACRAFT, ConstantNames.PIZZACRAFT),
    ROGUELIKEDUNGEONS(ConstantIds.ROGUELIKEDUNGEONS, ConstantNames.ROGUELIKEDUNGEONS),
    RUSTIC(ConstantIds.RUSTIC, ConstantNames.RUSTIC),
    RUSTICTHAUMATURGY(ConstantIds.RUSTICTHAUMATURGY, ConstantNames.RUSTICTHAUMATURGY),
    THAUMCRAFT(ConstantIds.THAUMCRAFT, ConstantNames.THAUMCRAFT),
    THAUMICAUGMENTATION(ConstantIds.THAUMICAUGMENTATION, ConstantNames.THAUMICAUGMENTATION),
    THERMALFOUNDATION(ConstantIds.THERMALFOUNDATION, ConstantNames.THERMALFOUNDATION),
    TWILIGHTFOREST(ConstantIds.TWILIGHTFOREST, ConstantNames.TWILIGHTFOREST),
    WADDLES(ConstantIds.WADDLES, ConstantNames.WADDLES),
    ;

    public final String MOD_ID;
    public final String MOD_NAME;
    public final boolean isLoaded;

    ModIds(String modId, String modName) {
        this(modId, modName, null);
    }

    ModIds(String modId, String modName, @Nullable String version) {
        this.MOD_ID = modId;
        this.MOD_NAME = modName;
        this.isLoaded = Loader.isModLoaded(modId) && isSpecifiedVersion(version);
    }

    public ModContainer getModContainer() {
        return Loader.instance().getIndexedModList().get(MOD_ID);
    }

    public boolean isSpecifiedVersion(@Nullable String version) {
        if(version == null)
            return true;

        boolean match = true;
        ModContainer container = getModContainer();

        if(container != null) {
            try {
                VersionRange versionRange = VersionParser.parseRange(version);
                match = versionRange.containsVersion(container.getProcessedVersion());
            } catch (LoaderException ignored) {}
        }
        return match;
    }

    @Override
    public String toString() {
        return MOD_ID;
    }


    public static class ConstantIds {
        public static final String ANIMANIA = Animania.MODID;
        public static final String ANIMANIA_EXTRA = "extra";
        public static final String ANIMANIA_FARM = "farm";
        public static final String BEARWITHME = com.mrtrollnugnug.bearwithme.lib.Constants.MOD_ID;
        public static final String BEWITCHMENT = Bewitchment.MODID;
        public static final String BOTANIA = vazkii.botania.common.lib.LibMisc.MOD_ID;
        public static final String CHARM = Charm.MOD_ID;
        public static final String CRIMSONWARFARE = CrimsonWarfare.MODID;
        public static final String ENDERIO = EnderIO.MODID;
        public static final String FAMILIARFAUNA = FamiliarFauna.MOD_ID;
        public static final String FUTUREMC = FutureMC.ID;
        public static final String GRIMOIREOFGAIA = GaiaReference.MOD_ID;
        public static final String HARVESTCRAFT = com.pam.harvestcraft.Reference.MODID;
        public static final String INDUSTRIALFOREGOING = com.buuz135.industrial.utils.Reference.MOD_ID;
        public static final String JUSTENOUGHITEMS = Constants.MOD_ID;
        public static final String JUSTENOUGHRESOURCES = Reference.ID;
        public static final String LIBRARYEX = LibraryEx.MOD_ID;
        public static final String NETHEREX = NetherEx.MOD_ID;
        public static final String OCEANICEXPANSE = Main.MOD_ID;
        public static final String PIZZACRAFT = PizzaCraft.MODID;
        public static final String QUARK = LibMisc.MOD_ID;
        public static final String ROGUELIKEDUNGEONS = "roguelike";
        public static final String RUSTIC = Rustic.MODID;
        public static final String RUSTICTHAUMATURGY = RusticThaumaturgy.MODID;
        public static final String THAUMCRAFT = Thaumcraft.MODID;
        public static final String THAUMICAUGMENTATION = ThaumicAugmentationAPI.MODID;
        public static final String THERMALFOUNDATION = ThermalFoundation.MOD_ID;
        public static final String TWILIGHTFOREST = TwilightForestMod.ID;
        public static final String WADDLES = com.girafi.waddles.utils.Reference.MOD_ID;
    }

    public static class ConstantNames {
        public static final String ANIMANIA = Animania.NAME;
        public static final String ANIMANIA_EXTRA = ANIMANIA + " - Extra";
        public static final String ANIMANIA_FARM = ANIMANIA + " - Farm";
        public static final String BEARWITHME = com.mrtrollnugnug.bearwithme.lib.Constants.MOD_NAME;
        public static final String BEWITCHMENT = Bewitchment.NAME;
        public static final String BOTANIA = vazkii.botania.common.lib.LibMisc.MOD_NAME;
        public static final String CHARM = Charm.MOD_NAME;
        public static final String CRIMSONWARFARE = CrimsonWarfare.NAME;
        public static final String ENDERIO = EnderIO.MOD_NAME;
        public static final String FAMILIARFAUNA = FamiliarFauna.MOD_NAME;
        public static final String FUTUREMC = FutureMC.NAME;
        public static final String GRIMOIREOFGAIA = GaiaReference.MOD_NAME;
        public static final String HARVESTCRAFT = com.pam.harvestcraft.Reference.NAME;
        public static final String INDUSTRIALFOREGOING = com.buuz135.industrial.utils.Reference.NAME;
        public static final String JUSTENOUGHITEMS = Constants.NAME;
        public static final String JUSTENOUGHRESOURCES = Reference.NAME;
        public static final String NETHEREX = NetherEx.NAME;
        public static final String OCEANICEXPANSE = Main.NAME;
        public static final String PIZZACRAFT = PizzaCraft.NAME;
        public static final String QUARK = LibMisc.MOD_NAME;
        public static final String ROGUELIKEDUNGEONS = "Roguelike Dungeons";
        public static final String RUSTIC = Rustic.NAME;
        public static final String RUSTICTHAUMATURGY = RusticThaumaturgy.NAME;
        public static final String THAUMCRAFT = Thaumcraft.MODNAME;
        public static final String THAUMICAUGMENTATION = ThaumicAugmentationAPI.NAME;
        public static final String THERMALFOUNDATION = ThermalFoundation.MOD_NAME;
        public static final String TWILIGHTFOREST = TwilightForestMod.NAME;
        public static final String WADDLES = com.girafi.waddles.utils.Reference.MOD_NAME;

        //Used by config template
        public static final String TEMPLATE = "Template";
    }
}
