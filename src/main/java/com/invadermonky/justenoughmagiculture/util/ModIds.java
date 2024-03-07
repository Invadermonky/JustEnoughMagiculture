package com.invadermonky.justenoughmagiculture.util;

import chumbanotz.mutantbeasts.MutantBeasts;
import cofh.thermalfoundation.ThermalFoundation;
import com.animania.Animania;
import com.bewitchment.Bewitchment;
import com.bobmowzie.mowziesmobs.MowziesMobs;
import com.clownvin.livingenchantment.LivingEnchantment;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.rats.RatsMod;
import com.github.voxelfriend.rusticthaumaturgy.core.RusticThaumaturgy;
import com.seriouscreeper.lootchests.LootChests;
import com.sirsquidly.oe.Main;
import com.tage.crimson_warfare.CrimsonWarfare;
import com.tiviacz.pizzacraft.PizzaCraft;
import com.unoriginal.ancientbeasts.AncientBeasts;
import crazypants.enderio.base.EnderIO;
import electroblob.tfspellpack.TFSpellPack;
import electroblob.wizardry.Wizardry;
import familiarfauna.core.FamiliarFauna;
import fathertoast.specialmobs.SpecialMobsMod;
import gaia.GaiaReference;
import jeresources.reference.Reference;
import logictechcorp.netherex.NetherEx;
import lykrast.harvestersnight.common.HarvestersNight;
import mezz.jei.config.Constants;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;
import rustic.core.Rustic;
import svenhjol.charm.Charm;
import team.cqr.cqrepoured.CQRMain;
import thaumcraft.Thaumcraft;
import thebetweenlands.common.lib.ModInfo;
import thecodex6824.thaumicaugmentation.api.ThaumicAugmentationAPI;
import thedarkcolour.futuremc.FutureMC;
import twilightforest.TwilightForestMod;
import vazkii.quark.base.lib.LibMisc;

import javax.annotation.Nullable;

public enum ModIds {
    ANIMANIA(ConstantIds.ANIMANIA, ConstantNames.ANIMANIA),
    ATUM(ConstantIds.ATUM, ConstantNames.ATUM),
    BEAR_WITH_ME(ConstantIds.BEAR_WITH_ME, ConstantNames.BEAR_WITH_ME),
    BEAST_SLAYER(ConstantIds.BEAST_SLAYER, ConstantNames.BEAST_SLAYER),
    BETWEENLANDS(ConstantIds.BETWEENLANDS, ConstantNames.BETWEENLANDS),
    BEWITCHMENT(ConstantIds.BEWITCHMENT, ConstantNames.BEWITCHMENT),
    BOTANIA(ConstantIds.BOTANIA, ConstantNames.BOTANIA),
    CHARM(ConstantIds.CHARM , ConstantNames.CHARM),
    CHOCOLATE_QUEST(ConstantIds.CHOCOLATE_QUEST, ConstantNames.CHOCOLATE_QUEST),
    CRIMSON_WARFARE(ConstantIds.CRIMSON_WARFARE, ConstantNames.CRIMSON_WARFARE),
    EB_WIZARDRY(ConstantIds.EB_WIZARDRY, ConstantNames.EB_WIZARDRY),
    EB_WIZARDRY_TF(ConstantIds.EB_WIZARDRY_TF, ConstantNames.EB_WIZARDRY_TF),
    ENDER_IO(ConstantIds.ENDER_IO, ConstantNames.ENDER_IO),
    EREBUS(ConstantIds.EREBUS, ConstantNames.EREBUS),
    FAMILIAR_FAUNA(ConstantIds.FAMILIAR_FAUNA, ConstantNames.FAMILIAR_FAUNA),
    FUTURE_MC(ConstantIds.FUTURE_MC, ConstantNames.FUTURE_MC),
    GRIMOIRE_OF_GAIA(ConstantIds.GRIMOIRE_OF_GAIA, ConstantNames.GRIMOIRE_OF_GAIA),
    HARVESTCRAFT(ConstantIds.HARVESTCRAFT, ConstantNames.HARVESTCRAFT),
    HARVESTERS_NIGHT(ConstantIds.HARVESTERS_NIGHT, ConstantNames.HARVESTERS_NIGHT),
    ICE_AND_FIRE(ConstantIds.ICE_AND_FIRE, ConstantNames.ICE_AND_FIRE),
    INDUSTRIAL_FOREGOING(ConstantIds.INDUSTRIAL_FOREGOING, ConstantNames.INDUSTRIAL_FOREGOING),
    MORETWEAKER(ConstantIds.MORETWEAKER, ConstantNames.MORETWEAKER),
    MOWZIES_MOBS(ConstantIds.MOWZIES_MOBS, ConstantNames.MOWZIES_MOBS),
    MUTANT_BEASTS(ConstantIds.MUTANT_BEASTS, ConstantNames.MUTANT_BEASTS),
    NETHEREX(ConstantIds.NETHEREX, ConstantNames.NETHEREX),
    OCEANIC_EXPANSE(ConstantIds.OCEANIC_EXPANSE, ConstantNames.OCEANIC_EXPANSE),
    QUARK(ConstantIds.QUARK, ConstantNames.QUARK),
    PIZZACRAFT(ConstantIds.PIZZACRAFT, ConstantNames.PIZZACRAFT),
    RATS(ConstantIds.RATS, ConstantNames.RATS),
    ROGUELIKE_DUNGEONS(ConstantIds.ROGUELIKE_DUNGEONS, ConstantNames.ROGUELIKE_DUNGEONS),
    RUSTIC(ConstantIds.RUSTIC, ConstantNames.RUSTIC),
    RUSTIC_THAUMATURGY(ConstantIds.RUSTIC_THAUMATURGY, ConstantNames.RUSTIC_THAUMATURGY),
    SERIOUS_LOOT_CHESTS(ConstantIds.SERIOUS_LOOT_CHESTS, ConstantNames.SERIOUS_LOOT_CHESTS),
    SPECIAL_MOBS(ConstantIds.SPECIAL_MOBS, ConstantNames.SPECIAL_MOBS),
    THAUMCRAFT(ConstantIds.THAUMCRAFT, ConstantNames.THAUMCRAFT),
    THAUMIC_AUGMENTATION(ConstantIds.THAUMIC_AUGMENTATION, ConstantNames.THAUMIC_AUGMENTATION),
    THERMAL_FOUNDATION(ConstantIds.THERMAL_FOUNDATION, ConstantNames.THERMAL_FOUNDATION),
    TWILIGHT_FOREST(ConstantIds.TWILIGHT_FOREST, ConstantNames.TWILIGHT_FOREST),
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
        public static final String ATUM = com.teammetallurgy.atum.utils.Constants.MOD_ID;
        public static final String BEAR_WITH_ME = com.mrtrollnugnug.bearwithme.lib.Constants.MOD_ID;
        public static final String BEAST_SLAYER = AncientBeasts.MODID;
        public static final String BETWEENLANDS = ModInfo.ID;
        public static final String BEWITCHMENT = Bewitchment.MODID;
        public static final String BOTANIA = vazkii.botania.common.lib.LibMisc.MOD_ID;
        public static final String CHARM = Charm.MOD_ID;
        public static final String CHOCOLATE_QUEST = CQRMain.MODID;
        public static final String CRIMSON_WARFARE = CrimsonWarfare.MODID;
        public static final String ENDER_IO = EnderIO.MODID;
        public static final String EREBUS = erebus.lib.Reference.MOD_ID;
        public static final String EB_WIZARDRY = Wizardry.MODID;
        public static final String EB_WIZARDRY_TF = TFSpellPack.MODID;
        public static final String FAMILIAR_FAUNA = FamiliarFauna.MOD_ID;
        public static final String FUTURE_MC = FutureMC.ID;
        public static final String GRIMOIRE_OF_GAIA = GaiaReference.MOD_ID;
        public static final String HARVESTCRAFT = com.pam.harvestcraft.Reference.MODID;
        public static final String HARVESTERS_NIGHT = HarvestersNight.MODID;
        public static final String ICE_AND_FIRE = IceAndFire.MODID;
        public static final String INDUSTRIAL_FOREGOING = com.buuz135.industrial.utils.Reference.MOD_ID;
        public static final String JUSTENOUGHITEMS = Constants.MOD_ID;
        public static final String JUSTENOUGHRESOURCES = Reference.ID;
        public static final String MORETWEAKER = "moretweaker";
        public static final String MOWZIES_MOBS = MowziesMobs.MODID;
        public static final String MUTANT_BEASTS = MutantBeasts.MOD_ID;
        public static final String NETHEREX = NetherEx.MOD_ID;
        public static final String OCEANIC_EXPANSE = Main.MOD_ID;
        public static final String PIZZACRAFT = PizzaCraft.MODID;
        public static final String QUARK = LibMisc.MOD_ID;
        public static final String RATS = RatsMod.MODID;
        public static final String ROGUELIKE_DUNGEONS = "roguelike";
        public static final String RUSTIC = Rustic.MODID;
        public static final String RUSTIC_THAUMATURGY = RusticThaumaturgy.MODID;
        public static final String SERIOUS_LOOT_CHESTS = LootChests.MODID;
        public static final String SPECIAL_MOBS = SpecialMobsMod.MOD_ID;
        public static final String THAUMCRAFT = Thaumcraft.MODID;
        public static final String THAUMIC_AUGMENTATION = ThaumicAugmentationAPI.MODID;
        public static final String THERMAL_FOUNDATION = ThermalFoundation.MOD_ID;
        public static final String TWILIGHT_FOREST = TwilightForestMod.ID;
        public static final String WADDLES = com.girafi.waddles.utils.Reference.MOD_ID;

        //Non-JER Mods
        public static final String LIVINGENCHANTMENT = LivingEnchantment.MODID;
    }

    public static class ConstantNames {
        public static final String ANIMANIA = Animania.NAME;
        public static final String ANIMANIA_EXTRA = ANIMANIA + " - Extra";
        public static final String ANIMANIA_FARM = ANIMANIA + " - Farm";
        public static final String ATUM = com.teammetallurgy.atum.utils.Constants.MOD_NAME;
        public static final String BEAR_WITH_ME = com.mrtrollnugnug.bearwithme.lib.Constants.MOD_NAME;
        public static final String BEAST_SLAYER = AncientBeasts.NAME;
        public static final String BETWEENLANDS = ModInfo.NAME;
        public static final String BEWITCHMENT = Bewitchment.NAME;
        public static final String BOTANIA = vazkii.botania.common.lib.LibMisc.MOD_NAME;
        public static final String CHARM = Charm.MOD_NAME;
        public static final String CHOCOLATE_QUEST = "Chocolate Quest: Repoured";
        public static final String CRIMSON_WARFARE = CrimsonWarfare.NAME;
        public static final String EB_WIZARDRY = Wizardry.NAME;
        public static final String EB_WIZARDRY_TF = TFSpellPack.NAME;
        public static final String ENDER_IO = EnderIO.MOD_NAME;
        public static final String EREBUS = erebus.lib.Reference.MOD_NAME;
        public static final String FAMILIAR_FAUNA = FamiliarFauna.MOD_NAME;
        public static final String FUTURE_MC = FutureMC.NAME;
        public static final String GRIMOIRE_OF_GAIA = GaiaReference.MOD_NAME;
        public static final String HARVESTERS_NIGHT = HarvestersNight.NAME;
        public static final String HARVESTCRAFT = com.pam.harvestcraft.Reference.NAME;
        public static final String ICE_AND_FIRE = IceAndFire.NAME;
        public static final String INDUSTRIAL_FOREGOING = com.buuz135.industrial.utils.Reference.NAME;
        public static final String MORETWEAKER = "MoreTweaker";
        public static final String MOWZIES_MOBS = MowziesMobs.NAME;
        public static final String MUTANT_BEASTS = "Mutant Beasts";
        public static final String NETHEREX = NetherEx.NAME;
        public static final String OCEANIC_EXPANSE = Main.NAME;
        public static final String PIZZACRAFT = PizzaCraft.NAME;
        public static final String QUARK = LibMisc.MOD_NAME;
        public static final String RATS = RatsMod.NAME;
        public static final String ROGUELIKE_DUNGEONS = "Roguelike Dungeons";
        public static final String RUSTIC = Rustic.NAME;
        public static final String RUSTIC_THAUMATURGY = RusticThaumaturgy.NAME;
        public static final String SERIOUS_LOOT_CHESTS = "SeriousCreepers Loot Chests";
        public static final String SPECIAL_MOBS = SpecialMobsMod.NAME;
        public static final String THAUMCRAFT = Thaumcraft.MODNAME;
        public static final String THAUMIC_AUGMENTATION = ThaumicAugmentationAPI.NAME;
        public static final String THERMAL_FOUNDATION = ThermalFoundation.MOD_NAME;
        public static final String TWILIGHT_FOREST = TwilightForestMod.NAME;
        public static final String WADDLES = com.girafi.waddles.utils.Reference.MOD_NAME;

        //Non-JER Mods
        public static final String LIVINGENCHANTMENT = LivingEnchantment.NAME;

        //Used by config template
        public static final String TEMPLATE = "Template";
    }
}
