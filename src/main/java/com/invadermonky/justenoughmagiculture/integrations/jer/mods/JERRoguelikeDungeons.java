package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import greymerk.roguelike.treasure.loot.ILoot;
import greymerk.roguelike.treasure.loot.Loot;
import greymerk.roguelike.treasure.loot.LootSettings;
import greymerk.roguelike.treasure.loot.provider.ItemBase;
import greymerk.roguelike.util.IWeighted;
import greymerk.roguelike.util.WeightedRandomizer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraft.world.storage.loot.functions.SetNBT;

import java.lang.reflect.Field;
import java.util.*;

public class JERRoguelikeDungeons extends JERBase implements IJERIntegration {
    private Random rand = new Random();

    public JERRoguelikeDungeons(boolean enableJERDungeons) {
        if(enableJERDungeons) registerModDungeons();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerModDungeons() {
        try {
            TreeMap<Integer,ArrayList<LootPool>> floorLoot = new TreeMap<>();

            ILoot allLoot = Loot.getLoot();
            Field allLootLootField = allLoot.getClass().getDeclaredField("loot");
            allLootLootField.setAccessible(true);
            HashMap<Integer, LootSettings> allLootLoot = (HashMap<Integer, LootSettings>) allLootLootField.get(allLoot);

            for(int i = 0; i < allLootLoot.size(); i++) {
                floorLoot.put(i, new ArrayList<>());
            }

            allLootLoot.forEach((floor, lootSetting) -> {
                try {
                    Field lootField = lootSetting.getClass().getDeclaredField("loot");
                    lootField.setAccessible(true);
                    HashMap<Loot, ItemBase> loot = (HashMap<Loot, ItemBase>) lootField.get(lootSetting);

                    for(Loot type : loot.keySet()) {
                        try {
                            ArrayList<LootEntry> lootEntries = new ArrayList<>();
                            switch (type) {
                                case ARMOUR:
                                    //Armour will pull stuff from the quality type.
                                    break;
                                case BLOCK:
                                    lootEntries.addAll(weightedRandomizer(loot, type));
                                    break;
                                case BREWING:
                                    break;
                                case ENCHANTBOOK:
                                    /*
                                    for(int i = 0; i < 3; i++) {
                                        ItemStack stack = new ItemEnchBook().getLootItem(rand, floor);
                                        lootEntries.add(new LootEntryItem(
                                                stack.getItem(),
                                                0,
                                                0,
                                                new LootFunction[] {
                                                        new SetNBT(new LootCondition[0], stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound())
                                                },
                                                new LootCondition[0],
                                                "ench_book_" + i));
                                    }
                                     */
                                    break;
                                case ENCHANTBONUS:
                                    break;
                                case FOOD:
                                    lootEntries.addAll(hashMapWeightedRandomizer(loot, type, floor));
                                    break;
                                case JUNK:
                                    break;
                                case MUSIC:
                                    break;
                                case ORE:
                                    lootEntries.addAll(hashMapWeightedRandomizer(loot, type, floor));
                                    break;
                                case POTION:
                                    break;
                                case REWARD:
                                    break;
                                case SMITHY:
                                    break;
                                case SPECIAL:
                                    break;
                                case SUPPLY:
                                    break;
                                case TOOL:
                                    //Tool will pull stuff from the quality type.
                                    break;
                                case WEAPON:
                                    //Weapon will pull stuff from the quality type.
                                    break;
                            }

                            if(!lootEntries.isEmpty()) {
                                (floorLoot.get(floor)).add(new LootPool(
                                        lootEntries.toArray(new LootEntry[0]),
                                        new LootCondition[0],
                                        new RandomValueRange(1.0f, 1.0f),
                                        new RandomValueRange(0, 0),
                                        type.name()));
                            }
                        } catch(Exception ignored) {}
                    }
                } catch(Exception e) {
                    LogHelper.warn("Failed to access Roguelike Dungeons Weighted loot pools.");
                }
            });

            floorLoot.forEach((floor, lootPools) -> {
                registerDungeonLoot(
                        "roguelike:floor_" + floor,
                        StringHelper.getDungeonTranslationKey(ModIds.ROGUELIKE_DUNGEONS.MOD_ID, "floor_" + floor),
                        new LootTable(lootPools.toArray(new LootPool[0]))
                );
            });
        } catch (Exception e) {
            LogHelper.warn("Failed to retrieve Roguelike Dungeons treasure loot.");
        }
    }





    @SuppressWarnings("unchecked")
    private ArrayList<LootEntry> hashMapWeightedRandomizer(HashMap<Loot, ItemBase> loot, Loot type, int floor) throws NoSuchFieldException, IllegalAccessException {
        ItemBase itemBase = loot.get(type);
        Field itemBaseLootField = itemBase.getClass().getDeclaredField("loot");
        itemBaseLootField.setAccessible(true);
        HashMap<Integer, WeightedRandomizer<ItemStack>> itemBaseLoot = (HashMap<Integer, WeightedRandomizer<ItemStack>>) itemBaseLootField.get(itemBase);

        WeightedRandomizer<ItemStack> weightedRandomizer = itemBaseLoot.get(floor);

        return getLootEntries(weightedRandomizer, rand);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<LootEntry>  weightedRandomizer(HashMap<Loot, ItemBase> loot, Loot type) throws NoSuchFieldException, IllegalAccessException {
        ItemBase itemBase = loot.get(type);
        Field itemBaseLootField = itemBase.getClass().getDeclaredField("loot");
        itemBaseLootField.setAccessible(true);
        WeightedRandomizer<ItemStack> weightedRandomizer = (WeightedRandomizer<ItemStack>) itemBaseLootField.get(itemBase);

        return getLootEntries(weightedRandomizer, rand);
    }





    @SuppressWarnings("unchecked")
    private ArrayList<LootEntry> getLootEntries(WeightedRandomizer<ItemStack> weightedRandomizer, Random rand) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<LootEntry> lootEntries = new ArrayList<>();

        Field itemsField = weightedRandomizer.getClass().getDeclaredField("items");
        itemsField.setAccessible(true);
        List<IWeighted<ItemStack>> items = (List<IWeighted<ItemStack>>) itemsField.get(weightedRandomizer);

        for(IWeighted<ItemStack> item : items) {
            ItemStack stack;
            int min;
            int max;
            int weight;

            try {
                Field minField = item.getClass().getDeclaredField("min");
                Field maxField = item.getClass().getDeclaredField("max");
                minField.setAccessible(true);
                maxField.setAccessible(true);
                min = (int) minField.get(item);
                max = (int) maxField.get(item);
            } catch(Exception e) {
                min = 1;
                max = 1;
            }

            stack = item.get(rand);
            stack.setCount(1);
            weight = item.getWeight();

            if(stack.hasTagCompound()) {
                lootEntries.add(new LootEntryItem(stack.getItem(),
                        weight,
                        0,
                        new LootFunction[] {
                                new SetMetadata(new LootCondition[0], new RandomValueRange((float) stack.getItemDamage(), (float) stack.getItemDamage())),
                                new SetCount(new LootCondition[0], new RandomValueRange((float) min, (float) max)),
                                new SetNBT(new LootCondition[0], stack.getTagCompound())
                        },
                        new LootCondition[0],
                        stack.getItem().getRegistryName().toString()
                ));
            } else {
                lootEntries.add(new LootEntryItem(stack.getItem(),
                        weight,
                        0,
                        new LootFunction[] {
                                new SetMetadata(new LootCondition[0], new RandomValueRange((float) stack.getItemDamage(), (float) stack.getItemDamage())),
                                new SetCount(new LootCondition[0], new RandomValueRange((float) min, (float) max))
                        },
                        new LootCondition[0],
                        stack.getItem().getRegistryName().toString()
                ));
            }
        }
        return lootEntries;
    }
}
