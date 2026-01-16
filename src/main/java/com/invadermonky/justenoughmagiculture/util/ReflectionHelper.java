package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionHelper {
    public static final boolean isObfuscated = !FMLLaunchHandler.isDeobfuscatedEnvironment();
    private static Map<String, Method> methodCache = new HashMap<>();

    public static Object getFieldObject(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static Method getMethod(Object object, String name, Class<?>... parameters) throws NoSuchMethodException {
        Method method = object.getClass().getMethod(name, parameters);
        method.setAccessible(true);
        return method;
    }

    public static ResourceLocation getLootTable(EntityLivingBase entityLiving) {
        try {
            String lootMethodName = isObfuscated ? "func_184647_J" : "getLootTable";
            if(!methodCache.containsKey(lootMethodName)) {
                methodCache.put(lootMethodName, entityLiving.getClass().getDeclaredMethod(lootMethodName));
            }
            Method getLootMethod = methodCache.get(lootMethodName);
            if(!getLootMethod.isAccessible()) {
                getLootMethod.setAccessible(true);
            }
            return (ResourceLocation) getLootMethod.invoke(entityLiving);
        } catch (Exception e) {
            LogHelper.error("Failed to retrieve loot table for " + entityLiving.getName());
            e.printStackTrace(System.err);
        }
        return LootTableList.EMPTY;
    }
}
