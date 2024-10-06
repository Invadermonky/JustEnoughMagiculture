package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.util.ResourceLocation;

public class StringHelper {
    public static String getTranslationKey(String unloc, String ownerMod, String type, String... params) {
        StringBuilder builder = new StringBuilder(String.format("%s.%s:%s", type, ownerMod, unloc));
        for(String s : params) {
            builder.append(".").append(s);
        }
        return builder.toString();
    }

    public static String getDungeonTranslationKey(String modId, String dungeonName) {
        return getTranslationKey(dungeonName, modId, "dungeon", "name");
    }

    public static String getDungeonTranslationKey(ResourceLocation location) {
        return String.format("dungeon.%s.name", location.toString());
    }

}
