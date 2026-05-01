package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.entity.EntityLiving;
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

    public static ResourceLocation getLootTable(EntityLiving entityLiving) {
        try {
            String lootMethodName = isObfuscated ? "func_184647_J" : "getLootTable";
            Method getLootMethod;
            if(!methodCache.containsKey(lootMethodName)) {
                try {
                    getLootMethod = entityLiving.getClass().getDeclaredMethod(lootMethodName);
                } catch (Exception ignored) {
                    getLootMethod = entityLiving.getClass().getSuperclass().getDeclaredMethod(lootMethodName);
                }
                methodCache.put(lootMethodName, getLootMethod);
            } else {
                getLootMethod = methodCache.get(lootMethodName);
            }
            if(!getLootMethod.isAccessible()) {
                getLootMethod.setAccessible(true);
            }
            return (ResourceLocation) getLootMethod.invoke(entityLiving);
        } catch (Exception e) {
            LogHelper.error("Failed to retrieve loot table for " + entityLiving.getName());
            LogHelper.error(e.getMessage());
        }
        return LootTableList.EMPTY;
    }
}
