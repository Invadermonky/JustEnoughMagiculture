package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.util.ResourceLocation;

public class StringHelper {
    public static String getDungeonTranslationKey(String modId, String dungeonName) {
        return String.format("dungeon.%s:%s.name", modId, dungeonName);
    }

    public static String getDungeonTranslationKey(ResourceLocation location) {
        return String.format("dungeon.%s.name", location.toString());
    }

}
