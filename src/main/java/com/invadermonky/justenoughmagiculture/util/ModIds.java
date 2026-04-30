package com.invadermonky.justenoughmagiculture.util;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;

import javax.annotation.Nullable;

public enum ModIds {
    ANIMANIA(ConstantIds.ANIMANIA, ConstantNames.ANIMANIA),
    ASTRAL_SORCERY(ConstantIds.ASTRAL_SORCERY, ConstantNames.ASTRAL_SORCERY),
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
    LOOT_TWEAKER(ConstantIds.LOOTTWEAKER, ConstantNames.LOOTTWEAKER),
    MORETWEAKER(ConstantIds.MORETWEAKER, ConstantNames.MORETWEAKER),
    MOWZIES_MOBS(ConstantIds.MOWZIES_MOBS, ConstantNames.MOWZIES_MOBS),
    MUTANT_BEASTS(ConstantIds.MUTANT_BEASTS, ConstantNames.MUTANT_BEASTS),
    NETHEREX(ConstantIds.NETHEREX, ConstantNames.NETHEREX),
    OCEANIC_EXPANSE(ConstantIds.OCEANIC_EXPANSE, ConstantNames.OCEANIC_EXPANSE, ConstantVersions.OCEANIC_EXPANSE),
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
        public static final String ANIMANIA = "animania";
        public static final String ANIMANIA_EXTRA = "extra";
        public static final String ANIMANIA_FARM = "farm";
        public static final String ASTRAL_SORCERY = "astralsorcery";
        public static final String ATUM = "atum";
        public static final String BEAR_WITH_ME = "bearwithme";
        public static final String BEAST_SLAYER = "ancientbeasts";
        public static final String BETWEENLANDS = "thebetweenlands";
        public static final String BEWITCHMENT = "bewitchment";
        public static final String BOTANIA = "botania";
        public static final String CHARM = "charm";
        public static final String CHOCOLATE_QUEST = "cqrepoured";
        public static final String CRIMSON_WARFARE = "crimsonwarfare";
        public static final String ENDER_IO = "enderio";
        public static final String EREBUS = "erebus";
        public static final String EB_WIZARDRY = "ebwizardry";
        public static final String EB_WIZARDRY_TF = "tfspellpack";
        public static final String FAMILIAR_FAUNA = "familiarfauna";
        public static final String FUTURE_MC = "futuremc";
        public static final String GRIMOIRE_OF_GAIA = "grimoireofgaia";
        public static final String HARVESTCRAFT = "harvestcraft";
        public static final String HARVESTERS_NIGHT = "harvestersnight";
        public static final String ICE_AND_FIRE = "iceandfire";
        public static final String INDUSTRIAL_FOREGOING = "industrialforegoing";
        public static final String JUSTENOUGHITEMS = "jei";
        public static final String JUSTENOUGHRESOURCES = "jeresources";
        public static final String MORETWEAKER = "moretweaker";
        public static final String MOWZIES_MOBS = "mowziesmobs";
        public static final String MUTANT_BEASTS = "mutantbeasts";
        public static final String NETHEREX = "netherex";
        public static final String OCEANIC_EXPANSE = "oe";
        public static final String PIZZACRAFT = "pizzacraft";
        public static final String QUARK = "quark";
        public static final String RATS = "rats";
        public static final String ROGUELIKE_DUNGEONS = "roguelike";
        public static final String RUSTIC = "rustic";
        public static final String RUSTIC_THAUMATURGY = "rusticthaumaturgy";
        public static final String SERIOUS_LOOT_CHESTS = "lootchests";
        public static final String SPECIAL_MOBS = "specialmobs";
        public static final String THAUMCRAFT = "thaumcraft";
        public static final String THAUMIC_AUGMENTATION = "thaumicaugmentation";
        public static final String THERMAL_FOUNDATION = "thermalfoundation";
        public static final String TWILIGHT_FOREST = "twilightforest";
        public static final String WADDLES = "waddles";

        //Non-JER Mods
        public static final String ELEMENTAL_ITEMS = "elementalitems";
        public static final String LIVINGENCHANTMENT = "livingenchantment";
        public static final String LOOTTABLETWEAKER = "lttweaker";
        public static final String LOOTTWEAKER = "loottweaker";
        public static final String MYSTCRAFT = "mystcraft";
    }

    public static class ConstantNames {
        public static final String ANIMANIA = "Animania";
        public static final String ANIMANIA_EXTRA = ANIMANIA + " - Extra";
        public static final String ANIMANIA_FARM = ANIMANIA + " - Farm";
        public static final String ASTRAL_SORCERY = "Astral Sorcery";
        public static final String ATUM = "atum";
        public static final String BEAR_WITH_ME = "Bear With Me";
        public static final String BEAST_SLAYER = "Beast Slayer";
        public static final String BETWEENLANDS = "The Betweenlands";
        public static final String BEWITCHMENT = "Bewitchment";
        public static final String BOTANIA = "Botania";
        public static final String CHARM = "Charm";
        public static final String CHOCOLATE_QUEST = "Chocolate Quest: Repoured";
        public static final String CRIMSON_WARFARE = "Crimson Warfare";
        public static final String EB_WIZARDRY = "Electroblob's Wizardry";
        public static final String EB_WIZARDRY_TF = "Electroblob's Wizardry: Twilight Forest Spell Pack";
        public static final String ENDER_IO = "Ender IO";
        public static final String EREBUS = "Erebus";
        public static final String FAMILIAR_FAUNA = "FamiliarFauna";
        public static final String FUTURE_MC = "Future MC";
        public static final String GRIMOIRE_OF_GAIA = "Grimoire of Gaia 3";
        public static final String HARVESTERS_NIGHT = "Harvester's Night";
        public static final String HARVESTCRAFT = "Pam's HarvestCraft";
        public static final String ICE_AND_FIRE = "Ice And Fire";
        public static final String INDUSTRIAL_FOREGOING = "Industrial Foregoing";
        public static final String MORETWEAKER = "MoreTweaker";
        public static final String MOWZIES_MOBS = "Mowzie's Mobs";
        public static final String MUTANT_BEASTS = "Mutant Beasts";
        public static final String NETHEREX = "NetherEx";
        public static final String OCEANIC_EXPANSE = "Oceanic Expanse";
        public static final String PIZZACRAFT = "PizzaCraft";
        public static final String QUARK = "Quark: RotN Edition";
        public static final String RATS = "Rats: Rebirth of the Plague";
        public static final String ROGUELIKE_DUNGEONS = "Roguelike Dungeons";
        public static final String RUSTIC = "Rustic";
        public static final String RUSTIC_THAUMATURGY = "Rustic Thaumaturgy";
        public static final String SERIOUS_LOOT_CHESTS = "SeriousCreepers Loot Chests";
        public static final String SPECIAL_MOBS = "Special Mobs";
        public static final String THAUMCRAFT = "Thaumcraft";
        public static final String THAUMIC_AUGMENTATION = "Thaumic Augmentation";
        public static final String THERMAL_FOUNDATION = "Thermal Foundation";
        public static final String TWILIGHT_FOREST = "The Twilight Forest";
        public static final String WADDLES = "Waddles";

        //Non-JER Mods
        public static final String LIVINGENCHANTMENT = "Living Enchantment";
        public static final String LOOTTWEAKER = "LootTweaker";

        //Used by config template
        public static final String TEMPLATE = "Template";
    }

    public static class ConstantVersions {
        public static final String OCEANIC_EXPANSE = "[1.1.0,)";
    }
}
