package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.LootBagRegistry;
import com.seriouscreeper.lootchests.ModItems;
import com.seriouscreeper.lootchests.loot.ModLootTables;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;

public class JERSeriousLootChests extends JERBase implements IJERIntegration {
    private static JERSeriousLootChests instance;

    private JERSeriousLootChests() {}

    public static JERSeriousLootChests getInstance() {
        return instance != null ? instance : (instance = new JERSeriousLootChests());
    }

    public void registerLootBagEntries() {
        if(JEMConfig.SERIOUS_LOOT_CHESTS.JUST_ENOUGH_ITEMS.enableJEILootBags) {
            LootBagRegistry registry = LootBagRegistry.getInstance();
            registry.registerLootBag(getLootTable(new ItemStack(ModItems.itemLootCrate, 1, 0)));  //Common
            registry.registerLootBag(getLootTable(new ItemStack(ModItems.itemLootCrate, 1, 1)));  //Uncommon
            registry.registerLootBag(getLootTable(new ItemStack(ModItems.itemLootCrate, 1, 2)));  //Rare
            registry.registerLootBag(getLootTable(new ItemStack(ModItems.itemLootCrate, 1, 3)));  //Mythic
        }
    }

    private LootBagEntry getLootTable(ItemStack lootCrate) {
        LootTable allLoot = manager.getLootTableFromLocation(ModLootTables.SeriousLoot);
        LootTable crateLoot;

        switch(lootCrate.getMetadata()) {
            case 0:
                crateLoot = new LootTable(new LootPool[] { allLoot.getPool("common") });
                break;
            case 1:
                crateLoot = new LootTable(new LootPool[] { allLoot.getPool("uncommon") });
                break;
            case 2:
                crateLoot = new LootTable(new LootPool[] { allLoot.getPool("rare") });
                break;
            case 3:
                crateLoot = new LootTable(new LootPool[] { allLoot.getPool("mythic") });
                break;
            default:
                crateLoot = new LootTable(new LootPool[] {});
        }
        return new LootBagEntry(lootCrate, crateLoot);
    }
}
