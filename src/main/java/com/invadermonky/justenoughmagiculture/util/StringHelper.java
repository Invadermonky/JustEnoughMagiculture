package com.invadermonky.justenoughmagiculture.util;

public class StringHelper {
    public static String getDungeonTranslationKey(String modId, String dungeonName) {
        return String.format("dungeon.%s:%s.name", modId, dungeonName);
    }
}
