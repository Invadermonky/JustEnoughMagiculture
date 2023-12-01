package com.invadermonky.justenoughmagiculture.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHelper {
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
}
