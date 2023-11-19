package com.invadermonky.justenoughmagiculture.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemHelper {
    private static final List<PotionType> POTION_TYPES = getPotions();

    public static ItemStack getItemStack(JsonObject jsonObject) {
        ItemStack stack = ItemStack.EMPTY;

        try {
            if(jsonObject.has("item")) {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(jsonObject.get("item").getAsString()));
                if (item != null && item != Items.AIR) {
                    int meta = jsonObject.has("meta") ? jsonObject.get("meta").getAsInt() : 0;
                    stack = new ItemStack(item, 1, meta);
                }
            } else if(jsonObject.has("itemBlock")) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonObject.get("itemBlock").getAsString()));
                if(block != null && block != Blocks.AIR) {
                    IBlockState state = block.getDefaultState();
                    if(jsonObject.has("properties")) {
                        JsonObject properties = jsonObject.getAsJsonObject("properties");
                        for(Map.Entry<String,JsonElement> prop : properties.entrySet()) {
                            String propStr = "";
                            IProperty property = BlockStateHelper.getProperty(state, prop.getKey());
                            if(property != null) {
                                Comparable propertyValue = BlockStateHelper.getPropertyValue(property, prop.getValue().getAsString());
                                if(propertyValue != null) {
                                    state = state.withProperty(property, propertyValue);
                                }
                            }
                        }
                    }
                    stack = new ItemStack(block, 1, block.getMetaFromState(state));
                }
            }

            if(!stack.isEmpty()) {
                int count = 0;
                if(!jsonObject.has("minCount") && !jsonObject.has("maxCount")) {
                    if(jsonObject.has("count")) {
                        count = jsonObject.get("count").getAsInt();
                    }
                } else {
                    count = (jsonObject.get("minCount").getAsInt() + jsonObject.get("maxCount").getAsInt()) / 2;
                }
                stack.setCount(count);

                if(jsonObject.has("displayName")) {
                    stack.setStackDisplayName(jsonObject.get("displayName").getAsString());
                }

                List<String> loreList = new ArrayList<>();
                if(jsonObject.has("lore")) {
                    JsonArray loreArray = jsonObject.getAsJsonArray("lore");
                    for (JsonElement loreElement : loreArray) {
                        loreList.add(loreElement.getAsString());
                    }
                }
                if(loreList != null && loreList.size() > 0) {
                    ensureTagExists(stack);
                    NBTTagList loreTagList = new NBTTagList();
                    for(String lore : loreList) {
                        loreTagList.appendTag(new NBTTagString(lore));
                    }
                    NBTTagCompound displayCompound = new NBTTagCompound();
                    displayCompound.setTag("Lore", loreTagList);
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setTag("display", displayCompound);
                    setTagIfNotExistent(stack, compound);
                }

                JsonArray enchantments = jsonObject.has("enchantments") ? jsonObject.getAsJsonArray("enchantments") : null;
                if(enchantments != null) {
                    for(JsonElement enchant : enchantments) {
                        Enchantment enchantment = Enchantment.getEnchantmentByLocation(enchant.getAsJsonObject().get("enchantment").getAsString());

                        if(enchantment == null) {
                            continue;
                        }
                        int level = 0;
                        if(!enchant.getAsJsonObject().has("minEnchantmentLevel") && !enchant.getAsJsonObject().has("maxEnchantmentLevel")) {
                            if(enchant.getAsJsonObject().has("enchantmentLevel")) {
                                level = enchant.getAsJsonObject().get("enchantmentLevel").getAsInt();
                            }
                        } else {
                            level = enchant.getAsJsonObject().get("maxEnchantmentLevel").getAsInt();
                        }
                        if(stack.getItem() instanceof ItemEnchantedBook) {
                            ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, level));
                        } else {
                            stack.addEnchantment(enchantment, level);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
            return ItemStack.EMPTY;
        }

        return stack;
    }

    public static NBTTagCompound ensureTagExists(ItemStack stack) {
        return setTagIfNotExistent(stack, new NBTTagCompound());
    }

    public static NBTTagCompound setTagIfNotExistent(ItemStack stack, NBTTagCompound compound) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(compound);
        } else if (!compound.isEmpty()) {
            stack.getTagCompound().merge(compound);
        }

        return stack.getTagCompound();
    }

    public static ItemStack getRandomlyEnchantedBook(int level) {
        Random rand = new Random();
        return EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(Items.BOOK), rand.nextInt(10) + 5 + level, true);
    }

    public static ItemStack getRandomPotion() {
        Random rand = new Random();
        return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), POTION_TYPES.get(rand.nextInt(POTION_TYPES.size())));
    }

    private static List<PotionType> getPotions() {
        List<PotionType> potionTypes = new ArrayList(ForgeRegistries.POTION_TYPES.getValuesCollection());
        potionTypes.remove(PotionTypes.EMPTY);
        potionTypes.remove(PotionTypes.WATER);
        potionTypes.remove(PotionTypes.MUNDANE);
        potionTypes.remove(PotionTypes.THICK);
        potionTypes.remove(PotionTypes.AWKWARD);
        return potionTypes;
    }
}
