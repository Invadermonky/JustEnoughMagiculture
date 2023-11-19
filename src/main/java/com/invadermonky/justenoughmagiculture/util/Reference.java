package com.invadermonky.justenoughmagiculture.util;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import net.minecraft.util.ResourceLocation;

public class Reference {
    public static class FakeTables {
        public static final ResourceLocation CHARM_CRATE_FAKE_TABLE = new ResourceLocation(JustEnoughMagiculture.MOD_ID, "treasure/charm_crate");
        public static final ResourceLocation WITHER_FAKE_TABLE = new ResourceLocation(JustEnoughMagiculture.MOD_ID, "entities/wither_fake");
    }

    public static class JEI {
        public static final BackgroundDrawable LOOT_BAG = new BackgroundDrawable("textures/gui/loot_bag.png", 163, 120);
        public static final ResourceLocation TABS = new ResourceLocation(JustEnoughMagiculture.MOD_ID, "textures/gui/tabs.png");
    }
}
