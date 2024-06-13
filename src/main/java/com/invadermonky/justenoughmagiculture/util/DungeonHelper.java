package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

import static com.invadermonky.justenoughmagiculture.init.JERIntegration.dungeonRegistry;

public class DungeonHelper {
    public static void registerDungeon(String category, String localization, ResourceLocation lootTable) {
        dungeonRegistry.registerCategory(category, localization);
        dungeonRegistry.registerChest(category, lootTable);
        logDungeon(category, localization);
    }

    public static void registerDungeon(String category, String localization, LootTable lootTable) {
        dungeonRegistry.registerCategory(category, localization);
        dungeonRegistry.registerChest(category, lootTable);
        logDungeon(category, localization);
    }

    private static void logDungeon(String category, String localization) {
        LogHelper.debug("Registered dungeon " + category + " with name " + localization);
    }

    public static String getDungeonLocalKey(String modid, String dungeonName) {
        return "dungeon." + modid + ":" + dungeonName + ".name";
    }
}
