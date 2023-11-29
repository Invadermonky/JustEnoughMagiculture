package com.invadermonky.justenoughmagiculture.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class JEIHelper {
    public static List<List<ItemStack>> getStacksList(Object[] inputs, int size) {
        List<List<ItemStack>> inputStacks = new ArrayList();

        for(int i = 0; i < inputs.length; i++) {
            Object obj = inputs[i];
            if (inputStacks.size() < size) {
                if (obj instanceof ItemStack) {
                    inputStacks.add(ImmutableList.of((ItemStack)obj));
                } else if (!(obj instanceof List)) {
                    if (obj instanceof String) {
                        inputStacks.add(OreDictionary.getOres((String)obj));
                    }
                } else {
                    List<ItemStack> stackList = new ArrayList();
                    for(Object item : (List)obj) {
                        if(item instanceof ItemStack) {
                            stackList.add((ItemStack) item);
                        }
                    }
                    inputStacks.add(stackList);
                }
            }
        }

        while(inputStacks.size() < size) {
            inputStacks.add(ImmutableList.of(ItemStack.EMPTY));
        }

        return inputStacks;
    }
}
