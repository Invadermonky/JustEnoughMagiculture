package com.invadermonky.justenoughmagiculture.util;

import am2.ArsMagica2;
import com.bafomdad.uniquecrops.UniqueCrops;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.dmonsters.main.MainMod;
import com.pam.harvestcraft.Reference;
import com.superdextor.adinferos.AdInferosReference;
import crazypants.enderzoo.EnderZoo;
import hellfirepvp.astralsorcery.AstralSorcery;
import teamroots.embers.Embers;
import thaumcraft.Thaumcraft;

import javax.annotation.Nullable;

public enum ModIds {
    ad_inferos(ConstIds.ad_inferos, ConstVersions.ad_inferos),
    ars_magica(ConstIds.ars_magica, ConstVersions.ars_magica),
    astral_sorcery(ConstIds.astral_sorcery),
    deadly_monsters(ConstIds.deadly_monsters, ConstVersions.deadly_monsters),
    embers(ConstIds.embers),
    ender_zoo(ConstIds.ender_zoo, ConstVersions.ender_zoo),
    harvestcraft(ConstIds.harvestcraft, ConstVersions.harvestcraft),
    mystical_agriculture(ConstIds.mystical_agriculture),
    thaumcraft(ConstIds.thaumcraft, ConstVersions.thaumcraft),
    unique_crops(ConstIds.unique_crops, ConstVersions.unique_crops),
    ;

    public final String modId;
    public final String version;
    public final boolean isLoaded;

    ModIds(String modId) {
        this(modId, null);
    }

    ModIds(String modId, @Nullable String version) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version);
    }
    
    ModIds(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version, isMinVersion, isMaxVersion);
    }
    
    public static class ConstIds {
        public static final String ad_inferos = AdInferosReference.MOD_ID;
        public static final String ars_magica = ArsMagica2.MODID;
        public static final String astral_sorcery = AstralSorcery.MODID;
        public static final String deadly_monsters = MainMod.MODID;
        public static final String embers = Embers.MODID;
        public static final String ender_zoo = EnderZoo.MODID;
        public static final String harvestcraft = Reference.MODID;
        public static final String mystical_agriculture = MysticalAgriculture.MOD_ID;
        public static final String thaumcraft = Thaumcraft.MODID;
        public static final String unique_crops = UniqueCrops.MOD_ID;
    }

    public static class ConstVersions {
        public static final String ad_inferos = AdInferosReference.VERSION;
        public static final String ars_magica = ArsMagica2.VERSION;
        public static final String deadly_monsters = MainMod.MODVERSION;
        public static final String ender_zoo = EnderZoo.VERSION;
        public static final String harvestcraft = Reference.VERSION;
        public static final String thaumcraft = Thaumcraft.VERSION;
        public static final String unique_crops = UniqueCrops.VERSION;
    }
}
